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
            - year: String
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
            - {static} stats: Stats[]
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
            + isMember(value: double): boolean
            + getMin(): double
            - getMax(): double
        }
        class ContinuousDomain implements Domain {
            + isMember(value: double): boolean
            - from: double
            - to: double
        }
        class DiscreteDomain implements Domain {
            + isMember(value: double): boolean
            - values: double[]
        }
    }
    class BaseFunction {
        - function: Function
        - domain: Domain
        + getValue(value: double): double
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
        + {static} createGaussianFunction(average: double, standardDeviation: double, domainFrom: double, domainTo: double): GaussianFunction
        + {static} createTrapezoidalFunction(minSupp: double, minHeight: double, maxHeight: double, maxSupp: double, discreteDomain: double[]): TrapezoidalFunction
        + {static} createTrapezoidalFunction(minSupp: double, minHeight: double, maxHeight: double, maxSupp: double, domainFrom: double, domainTo: double): TrapezoidalFunction
        + {static} createTriangularFunction(minSupp: double,  max: double, discreteDomain: double[]): TriangularFunction
        + {static} createTriangularFunction(minSupp: double,  max: double, domainFrom: double, domainTo: double): TriangularFunction
        + {static} createRectangularFunction(minSupp: double, maxSupp: double, discreteDomain: double[]): RectangularFunction
        + {static} createRectangularFunction(minSupp: double, maxSupp: double, domainFrom: double, domainTo: double): RectangularFunction
    }
    FunctionFactory ..> BaseFunction
}
```

## Sets

```plantuml
package sets {
    class CrispSet {
        # function: BaseFunction
    }
    class FuzzySet extends CrispSet {
        + getSupport(): CrispSet
        + getAlphaCut(double): CrispSet
        + getHeight(): double
        + isNormal(): boolean
        + isEmpty(): boolean
        + isConvex(): boolean
    }
    class SetFactory {
        + {static} createFuzzySet(function: BaseFunction: FuzzySet
        + {static} createCrispSet(function: BaseFunction: CrispSet
    }
    SetFactory ..> FuzzySet
    SetFactory ..> CrispSet
}
```

## Operators

```plantuml
package operations {
    abstract class AbstractOperator {
        + execute(crispSet: CrispSet[1..2]): double
        # {abstract} operation(crispSet: CrispSet[1..2]): BaseFunction
    }
    class Complement extends AbstractOperator {
        # operation(crispSet: CrispSet[1..2]): BaseFunction
    }
    class Multiply extends AbstractOperator {
        # operation(crispSet: CrispSet[1..2]): BaseFunction
    }
    class Sum extends AbstractOperator {
        # operation(crispSet: CrispSet[1..2]): BaseFunction
    }
    
    class SetOperationFacade {
        - {static} complement: Complement
        - {static} multiply: Multiply
        - {static} sum: Sum
        + {static} complement(crispSet: CrispSet): CrispSet
        + {static} multiply(crispSet: CrispSet, crispSet: CrispSet): CrispSet
        + {static} sum(crispSet: CrispSet, crispSet: CrispSet): CrispSet
    }
    SetOperationFacade *--> Complement: has
    SetOperationFacade *--> Multiply: has
    SetOperationFacade *--> Sum: has
}
```

## Linguistic

```plantuml
package linguistic {
    class LinguisticSummary {
        - quantifiers: AbstractQuantifier[]
        - qualifiers: Label[]
        - summarizers: Label[]
        - stats: Stats[]
    }
    LinguisticSummary "1" *--> "0..*" AbstractQuantifier: has
    LinguisticSummary "1" *--> "0..*" Label: has
    
    class Label {
        - name: String
    }
    class Variable {
        - name: String
        - labels: Label[]
    }
    Variable "1" *--> "0..*" Label: has
    
    abstract class AbstractQuantifier {
        # name: String
        # function: BaseFunction
    }
    class Quantifier extends AbstractQuantifier
    class AbsoluteQuantifier extends AbstractQuantifier
}
package model {
    class Stats
}
package sets {
    class FuzzySet
}
LinguisticSummary "1" *--> "0..*" Stats: has
FuzzySet <|-- Label
```

## Utils

```plantuml
package utils {
    class Rounder {
        - {static} DECIMAL_PLACES_DIVISION: double
        - {static} NUMBER_OF_STEPS: double
        + {static} round(value: double): double
        + {static} floor(value: double): double
        + {static} getStep(min: double, max: double): double
    }
}
```