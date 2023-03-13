# Computer Recognition Systems
## Frontend uses
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
        + calculateStatistics(String[][] expectedVsResult): String[][]
    }
}
MainController ---> KnnFacade
MainController ..> FileType
MainController ..> MetricType
MainController ..> MeasureType
MainController ..> ExtractorType
```
## Backend
## Extractors
```plantuml
interface Extractor {
    + extract(Article article)
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
```
## Metrics
```plantuml
package metrics {
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
}
```
```plantuml
top to bottom direction
package backend {
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
   
    package extractor {
        interface Extractor {
            + extract(Article article)
        }
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
    package metrics {
        interface Metric {
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
    package knn {
        class Knn {
            - int k
            + calculateKnn(String[] texts, String[] expected, String text)
        }
        class KnnFactory {
            + createKnn(int k): Knn
        }
       KnnFactory ..> Knn
    }
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
    package statistics {
        interface Statistics {
            - truePositive: int
            - trueNegative: int
            - falsePositive: int
            - falseNegative: int
            + calculateStatistics(String[] result, String[] expected): double[]
            - calculateTruePositive(String[] result, String[] expected): int
            - calculateTrueNegative(String[] result, String[] expected): int
            - calculateFalsePositive(String[] result, String[] expected): int
            - calculateFalseNegative(String[] result, String[] expected): int
            - calculateCorrect(): int
            - calculateIncorrect(): int
            - calculatePrecision(): double
            - calculateRecall(): double
            - calculateFMeasure(): double
        }
        class StatisticsFactory {
            + createStatistics(): Statistics
        }
        StatisticsFactory ..> Statistics
    }
    class KnnFacade {
        + process(FileType fileType, MetricType metricType, MeasureType measureType, int k, String path, Double teachPart, String[][] features): String[]
        + calculateStatistics(String[] result, String[] expected): double[]
    }
    KnnFacade ..> ProcessFactory
    KnnFacade ..> StatisticsFactory
}
```