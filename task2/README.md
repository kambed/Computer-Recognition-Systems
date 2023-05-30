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
    package domains
    package model
    package repository
    package sets
    package operators
    package linguistics {
        package factory
        package predefined
        package quantifier
        package summary
        predefined ..> factory: <<import>>
        factory ..> quantifier: <<import>>
        summary ..> quantifier: <<import>>
    }
    
    functions ..> domains: <<import>> 
    repository ..> model: <<import>>
    operators ..> sets: <<import>>
    operators ..> functions: <<import>>
    sets ..> functions: <<import>>
    
    linguistics ..> functions: <<import>>
    linguistics ..> sets: <<import>>
    linguistics ..> model
}
package frontend {
}
frontend ..> backend: <<import>>
```

## Data model

```plantuml
package model {
    class Stats {
        - id: int
        - driver: String
        - team: String
        - track: String
        - year: String
        - finishPosition: int
        - startPosition: int
        - numberOfLaps: int
        - numberOfPoints: double
        - driverAge: double
        - percentageOfPointsGotForATeam: double
        - fastestLap: double
        - fastestLapSpeed: double
        - fastestPitStop: double
        - fastestQualificationLap: double
        - raceDate: int
        - raceTime: double
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
```

## Functions and domains

```plantuml
package functions {
    class BaseFunction {
        - function: Function
        - domain: Domain
        + getValue(value: double): double
    }
    class GaussianFunction extends BaseFunction {
        - average: double
        - standardDeviation: double
    }
    class TrapezoidalFunction extends BaseFunction {
        - minSupp: double
        - minHeight: double
        - maxHeight: double
        - maxSupp: double
    }
    class TriangularFunction extends TrapezoidalFunction
    class RectangularFunction extends TrapezoidalFunction
    
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
}
package domains {
    interface Domain {
        + isMember(value: double): boolean
        + getMin(): double
        + getMax(): double
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
FunctionFactory ..> BaseFunction
BaseFunction *----> Domain
```

## Sets

```plantuml
package sets {
    package factory {
        class SetFactory {
            + {static} createFuzzySet(function: BaseFunction): FuzzySet
            + {static} createCrispSet(function: BaseFunction): CrispSet
        }
    }
    class CrispSet {
        - function: BaseFunction
    }
    class FuzzySet extends CrispSet {
        + getSupport(): CrispSet
        + getAlphaCut(alfa: double): CrispSet
        + getHeight(): double
        + isNormal(): boolean
        + isEmpty(): boolean
        + isConvex(): boolean
        + getDegreeOfFuzziness(): double
        + getCardinality(): double
    }
    SetFactory ..> FuzzySet
    SetFactory ..> CrispSet
}
```

## Operators

```plantuml
package operators {
    package facade {
        class SetOperationFacade {
            - {static} complement: Complement
            - {static} multiply: Intersection
            - {static} sum: Union
            + {static} complement(crispSet: CrispSet): CrispSet
            + {static} multiply(crispSet: CrispSet, crispSet: CrispSet): CrispSet
            + {static} sum(crispSet: CrispSet, crispSet: CrispSet): CrispSet
        }
    }
    abstract class AbstractOperator {
        + execute(crispSet: CrispSet[1..2]): double
        # {abstract} operation(crispSet: CrispSet[1..2]): BaseFunction
    }
    class Complement extends AbstractOperator {
        # operation(crispSet: CrispSet[1..2]): BaseFunction
    }
    class Intersection extends AbstractOperator {
        # operation(crispSet: CrispSet[1..2]): BaseFunction
    }
    class Union extends AbstractOperator {
        # operation(crispSet: CrispSet[1..2]): BaseFunction
    }
    SetOperationFacade *--> Complement: has
    SetOperationFacade *--> Intersection: has
    SetOperationFacade *--> Union: has
}
```

## Linguistic

### Factory, predefined, quantifier

```plantuml
package linguistic {
    package factory {
        class LinguisticFactory {
            + {static} createLabel(label: String, function: BaseFunction): LabeledFuzzySet
            + {static} createVariable(name: String, labels: LabeledFuzzySet[]): Variable
            + {static} createAbsoluteQuantifier(label: String, function: BaseFunction): AbsoluteQuantifier
            + {static} createRelativeQuantifier(label: String, function: BaseFunction): RelativeQuantifier
        }
    }
    package predefined {
        class PredefinedQuantifiers {
            + {static} getPredefinedRelativeQuantifiers(): RelativeQuantifier[]
            + {static} getPredefinedAbsoluteQuantifiers(): AbsoluteQuantifier[]
        }
        class PredefinedVariables {
            + {static} getPredefinedVariables(): Variable[]
        }
    }
    package quantifier {
        abstract class AbstractQuantifier
        class RelativeQuantifier extends AbstractQuantifier
        class AbsoluteQuantifier extends AbstractQuantifier
    }
    class LabeledFuzzySet {
        # label: String
    }
    class Variable {
        - name: String
        - labels: LabeledFuzzySet[]
    }
    LabeledFuzzySet <|-- AbstractQuantifier
    Variable "1" *--> "0..*" LabeledFuzzySet: has
    
    LinguisticFactory ..> LabeledFuzzySet
    LinguisticFactory ..> Variable
    LinguisticFactory ..> AbstractQuantifier
}
package sets {
    class FuzzySet
}
PredefinedQuantifiers ..> AbstractQuantifier
PredefinedVariables ..> Variable
FuzzySet <|-- LabeledFuzzySet
```
### Summary, LabeledFuzzySet, Variable and Subject

```plantuml
package linguistic {
    package quantifier {
        abstract class AbstractQuantifier
        class Quantifier extends AbstractQuantifier
        class AbsoluteQuantifier extends AbstractQuantifier
    }
    package summary {
        abstract class Summary {
            # summaryString: String
            # finalDegreeOfTruth: double
            # t1: double
            # t2: double
            # t3: double
            # t4: double
            # t5: double
            # t6: double
            # t7: double
            # t8: double
            # t9: double
            # t10: double
            # t11: double
        }
        abstract class SingleSubjectSummary extends Summary {
            # quantifier: AbstractQuantifier
            # subject: Subject
            # summarizers: LabeledFuzzySet[]
            # summarizerVariableNames: String[]
            # evaluateFinalDegreeOfTruth(): double
            # {abstract} evaluateT1(): double
            # {abstract} evaluateT2(): double
            # {abstract} evaluateT3(): double
            # {abstract} evaluateT4(): double
            # {abstract} evaluateT5(): double
            # {abstract} evaluateT6(): double
            # {abstract} evaluateT7(): double
            # {abstract} evaluateT8(): double
            # {abstract} evaluateT9(): double
            # {abstract} evaluateT10(): double
            # {abstract} evaluateT11(): double
        }
        abstract class MultiSubjectSummary extends Summary {
            # subjects: Subject[]
            # summarizers: LabeledFuzzySet[]
            # summarizerVariableNames: String[]
            # evaluateFinalDegreeOfTruth(): double
            # {abstract} evaluateT1(): double
        }
        class SingleType1Summary extends SingleSubjectSummary {
            # evaluateT1(): double
            # evaluateT2(): double
            # evaluateT3(): double
            # evaluateT4(): double
            # evaluateT5(): double
            # evaluateT6(): double
            # evaluateT7(): double
            # evaluateT8(): double
            # evaluateT9(): double
            # evaluateT10(): double
            # evaluateT11(): double
        }
        class SingleType2Summary extends SingleSubjectSummary {
            - qualifiers: LabeledFuzzySet[]
            - qualifierVariableNames: String[]
            # evaluateT1(): double
            # evaluateT2(): double
            # evaluateT3(): double
            # evaluateT4(): double
            # evaluateT5(): double
            # evaluateT6(): double
            # evaluateT7(): double
            # evaluateT8(): double
            # evaluateT9(): double
            # evaluateT10(): double
            # evaluateT11(): double
        }
        class MultiType1Summary extends MultiSubjectSummary {
            - quantifier: LabeledFuzzySet
            # evaluateT1(): double
        }
        class MultiType2Summary extends MultiSubjectSummary {
            - quantifier: LabeledFuzzySet
            - qualifiers: LabeledFuzzySet[]
            - qualifierVariableNames: String[]
            # evaluateT1(): double
        }
        class MultiType3Summary extends MultiSubjectSummary {
            - quantifier: LabeledFuzzySet
            - qualifiers: LabeledFuzzySet[]
            - qualifierVariableNames: String[]
            # evaluateT1(): double
        }
        class MultiType4Summary extends MultiSubjectSummary {
            # evaluateT1(): double
        }
    }
    class LabeledFuzzySet {
        # label: String
    }
    class Variable {
        - name: String
        - labels: LabeledFuzzySet[]
    }
    class Subject {
        - name: String
        - elements: Stats[]
        + getSumOfAll(): double[]
        + getElementsCardinality(fuzzySets: LabeledFuzzySet[], summarizersNames: String[]): double
        + getElementsSupportCardinality(uzzySets: LabeledFuzzySet[], summarizersNames: String[]): double
    }
    LabeledFuzzySet <|-- AbstractQuantifier
    Variable "1" *--> "0..*" LabeledFuzzySet: has
}
package model {
    class Stats
}
package sets {
    class FuzzySet
}
FuzzySet <|-- LabeledFuzzySet
Subject "1" *--> "0..*" Stats: has
```

## Backend

```plantuml
package backend {
    class Rounder {
        - {static} DECIMAL_PLACES_DIVISION: double
        - {static} NUMBER_OF_STEPS: double
        + {static} round(value: double): double
        + {static} floor(value: double): double
        + {static} getStep(min: double, max: double): double
    }
    class LinguisticSummarizationsExecutor {
        - {static} getSublistsOfList(n: int): int[][]
        + {static} getSummaries(quantifiers: AbstractQuantifier[], fuzzySets: LabeledFuzzySet[], summarizersNames: String[]): Summary[]
    }
    package linguistic {
        package summary {
            abstract class Summary
        }
        class Subject
    }
    package repository {
        class Repository
    }
    LinguisticSummarizationsExecutor ..> Summary
    LinguisticSummarizationsExecutor ..> Subject
    LinguisticSummarizationsExecutor ..> Repository
}
```

## Frontend

```plantuml
package frontend {
    class MainApplication
    class MainController {
        - variables: Variable[]
        - quantifiers: AbstractQuantifier[]
    }
    
    class LinguisticVariablesEditorController
    class LabeledFuzzySetListController
    class LabeledFuzzySetEditorController
    
    class FunctionController
    class FunctionDomainController
    
    class SummariesGeneratorController
    class SummaryController
    
    MainApplication ..> MainController
    
    MainController ..> LinguisticVariablesEditorController
    MainController ..> LabeledFuzzySetListController
    MainController ..> SummariesGeneratorController
    MainController ..> SummaryController
    
    LinguisticVariablesEditorController ..> LabeledFuzzySetListController
    
    LabeledFuzzySetListController ..> LabeledFuzzySetEditorController
    
    LabeledFuzzySetEditorController ..> FunctionController
    
    FunctionController ..> FunctionDomainController
    
    package model {
        class SummaryDto {
            - selected: boolean
            + toHtmlTableRow(): String
        }
    }
    package utils {
        class AlertBox {
            + show(title: String, message: String): void
        }
        class FileChooser {
            + {static} chooseFile(): String
        }
    }
    SummaryController ..> SummaryDto
    SummaryController ..> FileChooser
    SummaryController ..> AlertBox
    SummariesGeneratorController ..> AlertBox
}
package backend {
    package linguistic {
        abstract class Summary {
            # summaryString: String
            # finalDegreeOfTruth: double
            # t1: double
            # t2: double
            # t3: double
            # t4: double
            # t5: double
            # t6: double
            # t7: double
            # t8: double
            # t9: double
            # t10: double
            # t11: double
        }
    }
    class LinguisticSummarizationsExecutor
}
Summary <|----- SummaryDto
SummariesGeneratorController ......> LinguisticSummarizationsExecutor
```