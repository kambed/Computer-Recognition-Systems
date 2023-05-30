SET sql_mode = (SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY', ''));

UPDATE qualifying SET q1 = null where q1='';
UPDATE qualifying SET q2 = null where q2='';
UPDATE qualifying SET q3 = null where q3='';

CREATE TABLE stats
(
    id                                  int primary key,
    driver                              varchar(255),
    team                                varchar(255),
    track                               varchar(255),
    year                                int,
    finish_position                     int,
    start_position                      int,
    number_of_laps                      float,
    number_of_points                    float,
    age_of_the_driver                   float,
    percentage_of_points_got_for_a_team decimal(5, 2) null,
    fastest_lap                         float         null,
    fastest_lap_speed                   float         null,
    fastest_pitstop                     float         null,
    fastest_quali_lap                   float         null,
    race_date                           date          null,
    race_time                           time          null,
    track_latitude                      float         null,
    track_altitude                      float         null
);

INSERT INTO stats
SELECT r.resultId,
       CONCAT(d.surname, ' ', d.forename)                  as 'driver',
       c2.name                                             as 'team',
       c.name                                              as 'track',
       r2.year                                             as 'year',
       r.positionOrder                                     as 'finish_position',
       IF(r.grid <> 0, r.grid, 34)                         as 'start_position',
       r.laps                                              as 'number_of_laps',
       r.points                                            as 'number_of_points',
       TIMESTAMPDIFF(day, d.dob, r2.date) / 365.25         as 'age_of_the_driver',
       IF(cR.points = 0, 50, (r.points / cR.points) * 100) as 'percentage_of_points_got_for_a_team',
       TIME_TO_SEC(SUBSTR(fastestLapTime, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(fastestLapTime, 3, 2)) + SUBSTR(fastestLapTime, 6) / 1000 as 'fastest_lap_in_seconds',
       r.fastestLapSpeed                                   as 'fastest_lap_speed',
       MIN(pS.milliseconds) / 1000                         as 'fastest_pitstop',
       LEAST(
               COALESCE(TIME_TO_SEC(SUBSTR(q.q1, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(q.q1, 3, 2)) + SUBSTR(q.q1, 6) / 1000,
                        TIME_TO_SEC(SUBSTR(q.q2, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(q.q2, 3, 2)) + SUBSTR(q.q2, 6) / 1000,
                        TIME_TO_SEC(SUBSTR(q.q3, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(q.q3, 3, 2)) +
                        SUBSTR(q.q3, 6) / 1000),
               COALESCE(TIME_TO_SEC(SUBSTR(q.q2, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(q.q2, 3, 2)) + SUBSTR(q.q2, 6) / 1000,
                        TIME_TO_SEC(SUBSTR(q.q3, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(q.q3, 3, 2)) + SUBSTR(q.q3, 6) / 1000,
                        TIME_TO_SEC(SUBSTR(q.q1, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(q.q1, 3, 2)) +
                        SUBSTR(q.q1, 6) / 1000),
               COALESCE(TIME_TO_SEC(SUBSTR(q.q3, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(q.q3, 3, 2)) + SUBSTR(q.q3, 6) / 1000,
                        TIME_TO_SEC(SUBSTR(q.q1, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(q.q1, 3, 2)) + SUBSTR(q.q1, 6) / 1000,
                        TIME_TO_SEC(SUBSTR(q.q2, 1, 1)) * 60 + TIME_TO_SEC(SUBSTR(q.q2, 3, 2)) + SUBSTR(q.q2, 6) / 1000)
       ) as 'qualification_lap',
       r2.date                                             as 'race_date',
       r2.time                                             as 'race_time',
       c.lat                                               as 'track_latitude',
       c.alt                                               as 'track_altitude'
FROM drivers d
         JOIN results r on d.driverId = r.driverId
         JOIN constructors c2 on r.constructorId = c2.constructorId
         JOIN races r2 on r.raceId = r2.raceId
         JOIN circuits c on r2.circuitId = c.circuitId
         JOIN constructorResults cR on r.raceId = cR.raceId AND r.constructorId = cR.constructorId
         LEFT JOIN qualifying q on r.raceId = q.raceId AND d.driverId = q.driverId
         LEFT JOIN pitStops pS on r2.raceId = pS.raceId AND pS.driverId = r.driverId
WHERE r2.year > 1994
GROUP BY r.resultId;