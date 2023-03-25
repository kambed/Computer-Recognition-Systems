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
        + {static} main(String[] args)
    }
    class MainController {
        + loadFiles()
        + process()
    }
    class FileChooser {
        + {static} choose(String windowTitle): String[]
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
    }
    enum MeasureType {
        GENERALIZED_NGRAM
        GENERALIZED_NGRAM_WITH_LIMITATIONS
    }
    class KnnFacade {
        + process(FileType fileType, String[] paths, ExtractorType[] extractorTypes, MetricType metricType, MeasureType measureType, int k, Double teachPart): String[][]
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
### Reader package and dependencies
```plantuml
package reader {
    interface FileReader {
        + read(String path): Root
    }
    class SgmReader implements FileReader {
        + read(String path): Root
    }
    class CsvReader {
        + {static} readDictionary(String path): String[][]
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
```
### extractor package and dependencies
```plantuml
package extractor {
    abstract class Extractor {
        - domainMin: double
        - domainMax: double
        + extract(Article article)
        + extractAndNormalize(Article article): double
    }
    class ArticleLengthExtractor {
        + extract(Article article): int
    }
    class CityFromDatelineExtractor {
        + extract(Article article): String
    }
    class DaysFromCreationDateExtractor {
        + extract(Article article): int
    }
    class MostUsedCityNameMappedToCountryExtractor {
        + extract(Article article): String
    }
    class MostUsedCountryNameExtractor {
        + extract(Article article): String
    }
    class MostUsedCountryNameInTitle {
        + extract(Article article): String
    }
    class MostUsedCurrencyExtractor {
        + extract(Article article): String
    }
    class MostUsedWordAtTheBeginningExtractor {
        + extract(Article article): String
    }
    class MostUsedWordStartingInCapitalLetterExtractor {
        + extract(Article article): String
    }
    class MostUsedYearExtractor {
        + extract(Article article): long
    }
    class PeopleCountryExtractor {
        + extract(Article article): String
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
```
### metric package and dependencies
```plantuml
package metric {
    interface Metric {
        + calculateDistance(Object[] vector1, Object[] vector2): Double
    }
    class EuclideanMetric implements Metric {
        + calculateDistance(Object[] vector1, Object[] vector2): Double
    }
    class ManhattanMetric implements Metric {
        + calculateDistance(Object[] vector1, Object[] vector2): Double
    }
    class ChebyshevMetric implements Metric {
        + calculateDistance(Object[] vector1, Object[] vector2): Double
    }
    class CustomMetric implements Metric {
        + calculateDistance(Object[] vector1, Object[] vector2): Double
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
        + calculateMeasure(Object[] vector1, Object[] vector2): Double
    }
    class GeneralizedNgramMeasure implements Measure {
        + calculateMeasure(Object[] vector1, Object[] vector2): Double
    }
    class GeneralizedNgramMeasureWithLimitations implements Measure {
        + calculateMeasure(Object[] vector1, Object[] vector2): Double
    }
    
    class MeasureFactory {
        + {static} createMeasure(): Measure
    }
    MeasureFactory ..> Measure
    MeasureFactory ..> MeasureType
    enum MeasureType {
        GENERALIZED_NGRAM
        GENERALIZED_NGRAM_WITH_LIMITATIONS
        - Measure measure
        + getMeasure()
    }
}
```
### knn package and dependencies
```plantuml
package knn {
    class Knn {
        - int k
        + calculateKnn(String[] texts, String[] expected, String text)
    }
    class KnnFactory {
        + {static} createKnn(int k): Knn
    }
   KnnFactory ..> Knn
}
```
### process package and dependencies
```plantuml
package process {
        class Process {
            - FileReader fileReader
            - String[] countriesOfInterest
            - Extractor[] extractors
            - Metric metric
            - Measure measure
            - Knn knn
            + Process(ExtractorType[] extractorTypes, FileType fileType, MetricType metricType, MeasureType measureType, int k, Double teachPart)
            + process(String[] paths)
        }
        Process ...> ReaderFactory
        Process ...> ExtractorFactory
        Process ...> MetricFactory
        Process ...> MeasureFactory
        Process ...> KnnFactory
        Process --> FileReader
        Process --> Metric
        Process --> Measure
        Process --> Knn
        FileReader ..> Root
        Extractor ..> Root
        Process ..> Root
        class ProcessFactory {
            + {static} createProcess(ExtractorType[] extractorTypes, FileType fileType, MetricType metricType, MeasureType measureType, int k): Process
        }
        ProcessFactory ..> Process
        ProcessFactory ..> FileType
        ProcessFactory ..> MetricType
        ProcessFactory ..> MeasureType
    }
```
### statistics package and dependencies
```plantuml
package statistics {
    class Statistics {
        - total: int
        - confusionMatrix: int[][]
        + Statistics(String[][] expectedToReceivedValues)
        + getAccuracy(): double
        + getPrecision(): double[]
        + getRecall(): double[]
        + getF1Score(): double[]
    }
    class StatisticsFactory {
        + {static} createStatistics(String[][] expectedToReceivedValues): Statistics
    }
    StatisticsFactory ..> Statistics
}
```
### backend package and dependencies
```plantuml
top to bottom direction
package backend {
    class KnnFacade {
        + process(FileType fileType, MetricType metricType, MeasureType measureType, int k, String path, Double teachPart, String[][] features): double[]
    }
    KnnFacade ..> ProcessFactory
    Process ..> StatisticsFactory
    Process ..> Statistics
}
```