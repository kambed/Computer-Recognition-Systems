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
        + {static} main(args: String[])
    }
    class MainController {
        + loadFiles()
        + process()
    }
    class FileChooser {
        + {static} choose(windowTitle: String): String[]
    }
    MainController ..> FileChooser
    MainApplication --> MainController
}
package backend {
    enum FileType {
        SGM
        - fileReader: FileReader
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
        - extractor: Extractor
        + getExtractor(): Extractor
    }
    enum MetricType {
        EUCLIDEAN
        MANHATTAN
        CHEBYSHEV
        CUSTOM
        - metric: Metric
        + getMetric(): Metric
    }
    enum MeasureType {
        GENERALIZED_NGRAM_WITH_LIMITATIONS
    }
    class KnnFacade {
        + process(fileType: FileType, paths: String[], extractorTypes: ExtractorType[], metricType: MetricType, measure: Measure, k: int, teachPart: double): String[][]
        + createGeneralizedNgramMeasureWithLimitations(shortestGram: int, longestGram: int): Measure
    }
}
MainController ---> KnnFacade
MainController ..> FileType
MainController ..> MetricType
MainController ..> MeasureType
MainController ..> ExtractorType
```
## Backend
### reader package and dependencies
```plantuml
package reader {
    interface FileReader {
        + read(path: String): Root
    }
    class SgmReader implements FileReader {
        + read(path: String): Root
    }
    class CsvReader {
        + {static} readDictionary(path: String): String[][]
    }
    class ReaderFactory {
        + {static} createFileReader(fileType: FileType): FileReader
    }
    ReaderFactory ..> FileReader
    ReaderFactory ..> FileType
    enum FileType {
        SGM
        - fileReader: FileReader
        + getFileReader(): FileReader
    }
}
package model {
    class Root {
        - articles: Article[]
    }
    class Article {
        - date: String
        - topics: String[]
        - places: String[]
        - people: String[]
        - orgs: String[]
        - exchanges: String[]
        - companies: String[]
        - text: TextContent
    }
    class TextContent {
        - title: String
        - dateline: String
        - text: String
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
        - date: String
        - topics: String[]
        - places: String[]
        - people: String[]
        - orgs: String[]
        - exchanges: String[]
        - companies: String[]
        - text: TextContent
    }
}
package extractor {
    abstract class Extractor {
        - domainMin: double
        - domainMax: double
        + {abstract} extract(article: Article)
        + extractAndNormalize(article: Article): double
    }
    class ArticleLengthExtractor {
        + extract(article: Article): int
    }
    class CityFromDatelineExtractor {
        + extract(article: Article): String
    }
    class DaysFromCreationDateExtractor {
        + extract(article: Article): int
    }
    class MostUsedCityNameMappedToCountryExtractor {
        + extract(article: Article): String
        - getCitiesInCountries(): String[][]
    }
    class MostUsedCountryNameExtractor {
        + extract(article: Article): String
        - getCountriesSynonyms(): String[][]
    }
    class MostUsedCountryNameInTitle {
        + extract(article: Article): String
        - getCountriesSynonyms(): String[][]
    }
    class MostUsedCurrencyExtractor {
        + extract(article: Article): String
        - getCurrenciesSynonyms(): String[][]
    }
    class MostUsedWordAtTheBeginningExtractor {
        + extract(article: Article): String
        - getStopWords(): String[][]
    }
    class MostUsedWordStartingInCapitalLetterExtractor {
        + extract(article: Article): String
    }
    class MostUsedYearExtractor {
        + extract(article: Article): long
    }
    class PeopleCountryExtractor {
        + extract(article: Article): String
        - getPeopleCountries(): String[][]
    }
    class SentenceAverageLengthExtractor {
        + extract(article: Article): double
    }
    
    Extractor <|.. ArticleLengthExtractor
    Extractor <|.. CityFromDatelineExtractor
    Extractor <|.. DaysFromCreationDateExtractor
    Extractor <|.... MostUsedWordStartingInCapitalLetterExtractor
    Extractor <|.... MostUsedYearExtractor
    Extractor <|.... SentenceAverageLengthExtractor
    Extractor <|...... MostUsedCountryNameExtractor
    Extractor <|...... MostUsedCountryNameInTitle
    Extractor <|...... MostUsedCurrencyExtractor
    Extractor <|...... MostUsedCityNameMappedToCountryExtractor
    Extractor <|...... MostUsedWordAtTheBeginningExtractor
    Extractor <|...... PeopleCountryExtractor
    
    class ExtractorFactory {
        + {static} createExtractor(extractorType: ExtractorType): Extractor
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
        - extractor: Extractor
        + getExtractor(): Extractor
    }
    ExtractorFactory ...> Extractor
    ExtractorFactory ..> ExtractorType
}
package reader {
    class CsvReader {
        + {static} readDictionary(path: String): String[][]
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
        + calculateDistance(vector1: double[], vector2: double[]): double
    }
    class EuclideanMetric implements Metric {
        + calculateDistance(vector1: double[], vector2: double[]): double
    }
    class ManhattanMetric implements Metric {
        + calculateDistance(vector1: double[], vector2: double[]): double
    }
    class ChebyshevMetric implements Metric {
        + calculateDistance(vector1: double[], vector2: double[]): double
    }
    class CustomMetric implements Metric {
        + calculateDistance(vector1: double[], vector2: double[]): double
    }
    
    class MetricFactory {
        + {static} createMetric(metricType: MetricType): Metric
    }
    MetricFactory ..> Metric
    MetricFactory ..> MetricType
    enum MetricType {
        EUCLIDEAN
        MANHATTAN
        CHEBYSHEV
        CUSTOM
        - metric: Metric
        + getMetric(): Metric
    }
}
```
### measure package and dependencies
```plantuml
package measure {
    interface Measure {
        + calculateMeasure(text1: String, text2: String): double
    }
    class GeneralizedNgramMeasureWithLimitations implements Measure {
        - shortestGram: int
        - longestGram: int
        + GeneralizedNgramMeasureWithLimitations(shortestGram: int, longestGram: int)
        + calculateMetric(text1: String, text2: String): double
        + calculateMeasure(text1: String, text2: String): double
        - countCommonNgrams(text1: String, text2: String): int
    }
    class MeasureFactory {
        + {static} createGeneralizedNgramMeasureWithLimitations(shortestGram: int, longestGram: int): Measure
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
        - k: int
        - trainData: String[][][]
        - metric: Metric
        - measures: Measure
        + Knn(k: int, trainData: String[][][], metric: Metric, measure: Measure)
        + calculateKnn(vector: Object[]): String
    }
    class KnnFactory {
        + {static} createKnn(k: int, trainData: String[][][], metric: Metric, measure: Measure): Knn
    }
   KnnFactory ..> Knn
   
    package metric {
        interface Metric
        Knn ..> Metric
    }
    package measure {
        interface Measure
        Knn ..> Measure
    }
}
```
### statistics package and dependencies
```plantuml
package statistics {
    class Statistics {
        - total: int
        - confusionMatrix: int[][]
        + Statistics(expectedToReceivedValues: String[][])
        + getAccuracy(): double
        + getPrecision(): double[]
        + getRecall(): double[]
        + getF1Score(): double[]
    }
    class StatisticsFactory {
        + {static} createStatistics(expectedToReceivedValues: String[][]): Statistics
    }
    StatisticsFactory ..> Statistics
}
```
### process package and dependencies
```plantuml
package process {
    class Process {
        - fileReader: FileReader
        - countriesOfInterest: String[]
        - extractors: Extractor[]
        - metric: Metric
        - measure: Measure
        - knn: Knn
        - k: double
        + Process(extractorTypes: ExtractorType[], fileType: FileType, metricType: MetricType, measure: Measure, k: int)
        + process(paths: String[], teachPart: double): String[][]
    }
    class ProcessFactory {
        + {static} createProcess(extractorTypes: ExtractorType[], fileType: FileType, metricType: MetricType, measure: Measure, k:int): Process
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
Process ...> KnnFactory
Process ...> StatisticsFactory
Process --> FileReader
Process --> Metric
Process --> Measure
Process --> Statistics
Process --> Knn
Process ..> Root
ProcessFactory ..> Measure
ProcessFactory ..> Process
ProcessFactory ..> FileType
ProcessFactory ..> MetricType
ReaderFactory ..> ExtractorType
```
### backend package and dependencies
```plantuml
top to bottom direction
package backend {
    class KnnFacade {
        + process(extractorTypes: ExtractorType[], fileType: FileType, paths: String[], metricType: MetricType, measure: Measure, k: int, teachPart: double): double[]
        + createGeneralizedNgramMeasureWithLimitations(shortestGram: int, longestGram: int): Measure
    }
    package process {
        class Process
        class ProcessFactory
    }
    KnnFacade ..> ProcessFactory
    KnnFacade ..> Process
}
```