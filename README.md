# Computer Recognition Systems
## Packages diagram
```plantuml
package frontend
package backend {
    package reader
    package extractor
    package model
    package process
    package statistics
    package knn {
        package metric
        package measure
    }
}
frontend ..> backend: <<import>>

process ..> reader: <<import>>
process ..> extractor: <<import>>
process ..> model: <<import>>
process ..> statistics: <<import>>
process ..> knn: <<import>>

extractor ..> model: <<import>>
extractor ..reader: <<import>>

```
## Frontend package and dependencies
```plantuml
package frontend {
    class MainApplication {
        + start()
        + {static} main(string[] args)
    }
    class MainController {
        + loadFiles()
        + process()
    }
    class FileChooser {
        + {static} choose(string windowTitle): string[]
    }
    MainController ..> FileChooser
    MainApplication --> MainController
}
package backend {
    enum FileType {
        SGM
        - FileReader fileReader
        + getFileReader(): FileReader
    }
    enum ExtractorType {
        ARTICLE_LENGTH
        CITY_FROM_DATELINE
        DAYS_FROM_CREATION_DATE
        MOST_USED_CITY_NAME_MAPPED_TO_COUNTRY
        MOST_USED_COUNTRY_NAME
        MOST_USED_COUNTRY_NAME_IN_TITLE
        MOST_USED_CURRENCY
        MOST_USED_WORD_AT_THE_BEGINNING
        MOST_USED_WORD_STARTING_IN_CAPITAL_LETTER
        MOST_USED_YEAR
        PEOPLE_COUNTRY
        SENTENCE_AVERAGE_LENGTH
        - Extractor extractor
        + getExtractor(): Extractor
    }
    enum MetricType {
        EUCLIDEAN
        MANHATTAN
        CHEBYSHEV
        CUSTOM
        - Metric metric
        + getMetric(): Metric
    }
    enum MeasureType {
        GENERALIZED_NGRAM_WITH_LIMITATIONS
    }
    class MeasureFactory {
        + {static} createGeneralizedNgramWithLimitationsMeasure(int n, int k): Measure
    }
    class KnnFacade {
        + process(FileType fileType, string[] paths, ExtractorType[] extractorTypes, MetricType metricType, Measure measure, int k, double teachPart): string[][]
    }
}
MainController ---> KnnFacade
MainController ..> FileType
MainController ..> MetricType
MainController ..> MeasureType
MainController ..> MeasureFactory
MainController ..> ExtractorType
```
## Backend
### reader package and dependencies
```plantuml
package reader {
    interface FileReader {
        + read(string path): Root
    }
    class SgmReader implements FileReader {
        + read(string path): Root
    }
    class CsvReader {
        + {static} readDictionary(string path): string[][]
    }
    class ReaderFactory {
        + {static} createFileReader(FileType fileType): FileReader
    }
    ReaderFactory ..> FileReader
    ReaderFactory ..> FileType
    enum FileType {
        SGM
        - FileReader fileReader
        + getFileReader(): FileReader
    }
}
package model {
    class Root {
        - articles: Article[]
    }
    class Article {
        - date: string
        - topics: string[]
        - places: string[]
        - people: string[]
        - orgs: string[]
        - exchanges: string[]
        - companies: string[]
        - text: TextContent
    }
    class TextContent {
        - title: string
        - dateline: string
        - text: string
    }
    Root o-- Article
    Article o-- TextContent
}
FileReader .> Root
```
### extractor package and dependencies
```plantuml
package model {
    class Article {
        - date: string
        - topics: string[]
        - places: string[]
        - people: string[]
        - orgs: string[]
        - exchanges: string[]
        - companies: string[]
        - text: TextContent
    }
}
package extractor {
    abstract class Extractor {
        - domainMin: double
        - domainMax: double
        + {abstract} extract(Article article)
        + extractAndNormalize(Article article): double
    }
    class ArticleLengthExtractor {
        + extract(Article article): int
    }
    class CityFromDatelineExtractor {
        + extract(Article article): string
    }
    class DaysFromCreationDateExtractor {
        + extract(Article article): int
    }
    class MostUsedCityNameMappedToCountryExtractor {
        + extract(Article article): string
        - getCitiesInCountries(): string[][]
    }
    class MostUsedCountryNameExtractor {
        + extract(Article article): string
        - getCountriesSynonyms(): string[][]
    }
    class MostUsedCountryNameInTitle {
        + extract(Article article): string
        - getCountriesSynonyms(): string[][]
    }
    class MostUsedCurrencyExtractor {
        + extract(Article article): string
        - getCurrenciesSynonyms(): string[][]
    }
    class MostUsedWordAtTheBeginningExtractor {
        + extract(Article article): string
        - getStopWords(): string[][]
    }
    class MostUsedWordStartingInCapitalLetterExtractor {
        + extract(Article article): string
    }
    class MostUsedYearExtractor {
        + extract(Article article): long
    }
    class PeopleCountryExtractor {
        + extract(Article article): string
        - getPeopleCountries(): string[][]
    }
    class SentenceAverageLengthExtractor {
        + extract(Article article): double
    }
    
    Extractor <|.. ArticleLengthExtractor
    Extractor <|.. CityFromDatelineExtractor
    Extractor <|... DaysFromCreationDateExtractor
    Extractor <|... MostUsedCityNameMappedToCountryExtractor
    Extractor <|.... MostUsedCountryNameExtractor
    Extractor <|.... MostUsedCountryNameInTitle
    Extractor <|..... MostUsedCurrencyExtractor
    Extractor <|..... MostUsedWordAtTheBeginningExtractor
    Extractor <|...... MostUsedWordStartingInCapitalLetterExtractor
    Extractor <|...... MostUsedYearExtractor
    Extractor <|....... PeopleCountryExtractor
    Extractor <|....... SentenceAverageLengthExtractor
    
    class ExtractorFactory {
        + {static} createExtractor(ExtractorType extractorType): Extractor
    }
    enum ExtractorType {
        ARTICLE_LENGTH
        CITY_FROM_DATELINE
        DAYS_FROM_CREATION_DATE
        MOST_USED_CITY_NAME_MAPPED_TO_COUNTRY
        MOST_USED_COUNTRY_NAME
        MOST_USED_COUNTRY_NAME_IN_TITLE
        MOST_USED_CURRENCY
        MOST_USED_WORD_AT_THE_BEGINNING
        MOST_USED_WORD_STARTING_IN_CAPITAL_LETTER
        MOST_USED_YEAR
        PEOPLE_COUNTRY
        SENTENCE_AVERAGE_LENGTH
        - Extractor extractor
        + getExtractor(): Extractor
    }
    ExtractorFactory ..> Extractor
    ExtractorFactory ..> ExtractorType
}
package reader {
    class CsvReader {
        + {static} readDictionary(string path): string[][]
    }
}
Extractor ..> Article

MostUsedCityNameMappedToCountryExtractor ..> CsvReader
MostUsedCountryNameExtractor ..> CsvReader
MostUsedCountryNameInTitle ..> CsvReader
MostUsedCurrencyExtractor ..> CsvReader
MostUsedWordAtTheBeginningExtractor ..> CsvReader
PeopleCountryExtractor ..> CsvReader
```
### metric package and dependencies
```plantuml
package metric {
    interface Metric {
        + calculateDistance(double[] vector1, double[] vector2): double
    }
    class EuclideanMetric implements Metric {
        + calculateDistance(double[] vector1, double[] vector2): double
    }
    class ManhattanMetric implements Metric {
        + calculateDistance(double[] vector1, double[] vector2): double
    }
    class ChebyshevMetric implements Metric {
        + calculateDistance(double[] vector1, double[] vector2): double
    }
    class CustomMetric implements Metric {
        + calculateDistance(double[] vector1, double[] vector2): double
    }
    
    class MetricFactory {
        + {static} createMetric(MetricType metricType): Metric
    }
    MetricFactory ..> Metric
    MetricFactory ..> MetricType
    enum MetricType {
        EUCLIDEAN
        MANHATTAN
        CHEBYSHEV
        CUSTOM
        - Metric metric
        + getMetric(): Metric
    }
}
```
### measure package and dependencies
```plantuml
package measure {
    interface Measure {
        + calculateMeasure(string text1, string text2): double
    }
    class GeneralizedNgramMeasureWithLimitations implements Measure {
        + GeneralizedNgramMeasureWithLimitations(int shortestGram, int longestGram)
        + calculateMeasure(string text1, string text2): double
    }
    class MeasureFactory {
        + {static} createGeneralizedNgramMeasureWithLimitations(int shortestGram, int longestGram): Measure
    }
    MeasureFactory ..> Measure
    enum MeasureType {
        GENERALIZED_NGRAM_WITH_LIMITATIONS
    }
}
```
### knn package and dependencies
```plantuml
package knn {
    class Knn {
        - int k
        - string[][][] trainData
        + Knn(int k, string[][] trainData, Metric metric, Measure measure)
        + calculateKnn(Object[] vector): string
    }
    class KnnFactory {
        + {static} createKnn(int k, string[][] trainData, Metric metric, Measure measure): Knn
    }
   KnnFactory ..> Knn
}
package metric {
    Metric ..> Knn
}
package measure {
    Measure ..> Knn
}
```
### statistics package and dependencies
```plantuml
package statistics {
    class Statistics {
        - total: int
        - confusionMatrix: int[][]
        + Statistics(string[][] expectedToReceivedValues)
        + getAccuracy(): double
        + getPrecision(): double[]
        + getRecall(): double[]
        + getF1Score(): double[]
    }
    class StatisticsFactory {
        + {static} createStatistics(string[][] expectedToReceivedValues): Statistics
    }
    StatisticsFactory ..> Statistics
}
```
### process package and dependencies
```plantuml
package process {
    class Process {
        - FileReader fileReader
        - string[] countriesOfInterest
        - Extractor[] extractors
        - Metric metric
        - Measure measure
        - Knn knn
        + Process(ExtractorType[] extractorTypes, FileType fileType, MetricType metricType, Measure measure, int k, double teachPart)
        + process(string[] paths)
    }
    class ProcessFactory {
        + {static} createProcess(ExtractorType[] extractorTypes, FileType fileType, MetricType metricType, Measure measure, int k): Process
    }
}
package reader {
    class FileReader
    class ReaderFactory
    enum FileType
}
package extractor {
    class Extractor
    class ExtractorFactory
    enum ExtractorType
}
package knn {
    class Knn
    class KnnFactory
    package metric {
        class Metric
        class MetricFactory
        enum MetricType
    }
    package measure {
        class Measure
    }
}
package model {
    class Root
}
package statistics {
    class Statistics
    class StatisticsFactory
}
Process ...> ReaderFactory
Process ...> ExtractorFactory
Process ...> MetricFactory
Process ...> MeasureFactory
Process ...> KnnFactory
Process ...> StatisticsFactory
Process --> FileReader
Process --> Metric
Process --> Measure
Process --> Statistics
Process --> Knn
Process ..> Root
ProcessFactory ..> Process
ProcessFactory ..> FileType
ProcessFactory ..> MetricType
ProcessFactory ..> MeasureType
ReaderFactory ..> ExtractorType
```
### backend package and dependencies
```plantuml
top to bottom direction
package backend {
    class KnnFacade {
        + process(FileType fileType, MetricType metricType, Measure measure, int k, string path, double teachPart, string[][] features): double[]
    }
    package process {
        class Process
        class ProcessFactory
    }
    KnnFacade ..> ProcessFactory
    KnnFacade ..> Process
}
```