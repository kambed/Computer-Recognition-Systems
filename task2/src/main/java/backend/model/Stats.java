package backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Builder
public class Stats {
    private int id;
    private String driver;
    private String team;
    private String track;
    private String year;
    private int finishPosition;
    private int startPosition;
    private int numberOfLaps;
    private double numberOfPoints;
    private double driverAge;
    private double percentageOfPointsGotForATeam;
    private double fastestLap;
    private double fastestLapSpeed;
    private double fastestPitStop;
    private double fastestQualiLap;
    private LocalDate raceData;
    private LocalTime raceTime;
    private double trackLatitude;
    private double trackAltitude;
}
