package backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
public class Stats implements Serializable {
    private int id;
    private String driver;
    private String team;
    private String track;
    private String year;
    private Integer finishPosition;
    private Integer startPosition;
    private Integer numberOfLaps;
    private Double numberOfPoints;
    private Double driverAge;
    private Double percentageOfPointsGotForATeam;
    private Double fastestLap;
    private Double fastestLapSpeed;
    private Double fastestPitStop;
    private Double fastestQualiLap;
    private Integer raceData;
    private Double raceTime;
    private Double trackLatitude;
    private Double trackAltitude;
}
