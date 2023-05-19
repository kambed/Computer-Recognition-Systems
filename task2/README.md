# Linguistic summaries of relational databases
```plantuml
package backend {
    package functions
    package data
}
package frontend {
}
frontend ..> backend: <<import>>
```
## Data model
```plantuml
package data {
    package model {
        class Stats {
            - id: int
            - driver: String
            - team: String
            - track: String
            - year: int
            - finishPosition: int
            - startPosition: int
            - numberOfLaps: double
            - numberOfPoints: double
            - driverAge: double
            - percentageOfPointsGotForATeam: double
            - fastestLap: double
            - fastestLapSpeed: double
            - fastestPitStop: double
            - fastestQualificationLap: double
            - raceDate: Date
            - raceTime: Time
            - trackLatitude: double
            - trackLongitude: double
        }
    }
    package repository {
        class StatsRepository {
            - stats: Stats[]
            + {static} getStats(): Stats[]
        }
        StatsRepository *--> Stats: has
    }

    note left of StatsRepository::stats
        StatsRepository is a singleton 
        which set stats while first call 
        to getStats(), next calls will
        return data.
    end note
}
```
## Functions

```plantuml
package functions {
    package domain {
        interface Domain {
            + isMember(double): boolean
        }
        class ContinuousDomain interface Domain {
            - from: double
            - to: double
        }
        class DiscreteDomain interface Domain {
            - values: double[]
        }
    }
    abstract class AbstractFunction {
        - function: Function
        - domain: Domain
        + getValue(double): double
        + isDiscrete(): boolean
        + isContinuous(): boolean
    }
    class GaussianFunction extends AbstractFunction
    class TrapezoidalFunction extends AbstractFunction
    class TriangularFunction extends TrapezoidalFunction
    class RectangularFunction extends TrapezoidalFunction
}
```