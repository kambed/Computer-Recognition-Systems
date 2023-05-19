To start database on docker use command:
> docker-compose -f docker-compose.yml up db

Alternatively, for JetBrains Intellij users they can use green button on the left in opened docker-compose file in db section.

After this you have to init database with data - run scripts from resources folder in order:
> 1. init.sql
> 2. f1stats.sql


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
            - driver: String
            - team: String
            - track: String
            - year: double
            - finishPosition: double
            - startPosition: double
            - numberOfLaps: double
            - numberOfPoints: double
            - driverAge: double
            - percentageOfPointsGotForATeam: double
            - fastestLap: double
            - fastestLapSpeed: double
            - fastestPitStop: double
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
    abstract class AbstractFunction {
        - function: Function
        + getValue(double): double
    }
    class GaussianFunction extends AbstractFunction
    class TrapezoidalFunction extends AbstractFunction
    class TriangularFunction extends TrapezoidalFunction
    class RectangularFunction extends TrapezoidalFunction
}
```