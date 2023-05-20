# Linguistic summaries of relational databases

```plantuml
package backend {
    package functions {
        package domains
    }
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
            - {static} instance: StatsRepository
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
            + isMember(double): boolean
            - from: double
            - to: double
        }
        class DiscreteDomain implements Domain {
            + isMember(double): boolean
            - values: double[]
        }
    }
    class BaseFunction {
        - function: Function
        - domain: Domain
        + getValue(double): double
        + isDiscrete(): boolean
        + isContinuous(): boolean
    }
    class GaussianFunction extends BaseFunction
    class TrapezoidalFunction extends BaseFunction
    class TriangularFunction extends TrapezoidalFunction
    class RectangularFunction extends TrapezoidalFunction
    
    BaseFunction ..> Domain
    
    class FunctionFactory {
        + {static} createFunction(function: Function, discreteDomain: double[]): BaseFunction
        + {static} createFunction(function: Function, domainFrom: double, domainTo: double): BaseFunction
        + {static} createGaussianFunction(average: double, standardDeviation: double, discreteDomain: double[]): GaussianFunction
        + {static} createGaussianFunction(average: double, standardDeviation: double, from: double, to: double): GaussianFunction
        + {static} createTrapezoidalFunction(minSupp: double, minHeight: double, maxHeight: double, maxSupp: double, discreteDomain: double[]): TrapezoidalFunction
        + {static} createTrapezoidalFunction(minSupp: double, minHeight: double, maxHeight: double, maxSupp: double, from: double, to: double): TrapezoidalFunction
        + {static} createTriangularFunction(minSupp: double,  max: double, discreteDomain: double[]): TriangularFunction
        + {static} createTriangularFunction(minSupp: double,  max: double, from: double, to: double): TriangularFunction
        + {static} createRectangularFunction(minSupp: double, maxSupp: double, discreteDomain: double[]): RectangularFunction
        + {static} createRectangularFunction(minSupp: double, maxSupp: double, from: double, to: double): RectangularFunction
    }
    FunctionFactory ..> BaseFunction
}
```

## Sets
```plantuml
package sets {
    class CrispSet {
        # function: DefaultFunction
    }
    class FuzzySet extends CrispSet {
        + getSupport(): CrispSet
        + getAlphaCut(double): CrispSet
        + getHeight(): double
        + isNormal(): boolean
    }
    class SetFactory {
        + {static} createFuzzySet(DefaultFunction): FuzzySet
        + {static} createCrispSet(DefaultFunction): CrispSet
    }
    SetFactory ..> FuzzySet
    SetFactory ..> CrispSet
}
```

## Operators

```plantuml
package operations {
    abstract class AbstractOperator {
        + execute(CrispSet, [CrispSet]): double
        # {abstract} operation(CrispSet, [CrispSet]): BaseFunction
    }
    class Complement extends AbstractOperator {
        # operation(CrispSet, [CrispSet]): BaseFunction
    }
    class Multiply extends AbstractOperator {
        # operation(CrispSet, CrispSet): BaseFunction
    }
    class Sum extends AbstractOperator {
        # operation(CrispSet, CrispSet): BaseFunction
    }
    
    class SetOperationFacade {
        - complement: Complement
        - multiply: Multiply
        - sum: Sum
        - instance: SetOperationFacade
        + {static} complement(CrispSet): FuzzySet
        + {static} multiply(CrispSet, CrispSet): FuzzySet
        + {static} sum(CrispSet, CrispSet): FuzzySet
    }
    SetOperationFacade *--> Complement: has
    SetOperationFacade *--> Multiply: has
    SetOperationFacade *--> Sum: has
}
```


## Utils
```plantuml
package utils {
    class Rounder {
        - {static} DECIMAL_PLACES_DIVISION: double
        + {static} round(double): double
        + {static} floor(double): double
    }
}
```