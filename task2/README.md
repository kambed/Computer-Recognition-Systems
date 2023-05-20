# Linguistic summaries of relational databases

```plantuml
package backend {
    package functions
    package data {
        package model
        package repository
        
        repository ..> model: <<import>>
    }
    package sets
    package operators
    package utils
    
    operators ..> sets: <<import>>
    sets ..> functions: <<import>>
    sets ..> utils: <<import>>
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
    package domains {
        interface Domain {
            + isMember(double): boolean
            - getMin(): double
            - getMax(): double
        }
        class ContinuousDomain implements Domain {
            - from: double
            - to: double
        }
        class DiscreteDomain implements Domain {
            - values: double[]
        }
    }
    abstract class DefaultFunction {
        - function: Function
        - domain: Domain
        + getValue(double): double
        + isDiscrete(): boolean
        + isContinuous(): boolean
    }
    class GaussianFunction extends DefaultFunction
    class TrapezoidalFunction extends DefaultFunction
    class TriangularFunction extends TrapezoidalFunction
    class RectangularFunction extends TrapezoidalFunction
}
```

## Sets
```plantuml
package sets {
    class CrispSet {
        # function: DefaultFunction
    }
    class FuzzySet {
        + getSupport(): CrispSet
        + getAlphaCut(double): CrispSet
        + getHeight(): double
        + isNormal(): boolean
    }
}
```

## Utils
```plantuml
package utils {
    class Rounder {
        - {static} DECIMAL_PLACES_DIVISION
        + round(double): double
        + floor(double): double
    }
}
```