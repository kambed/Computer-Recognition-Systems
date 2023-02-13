# Computer Recognition Systems
## Frontend uses
```plantuml
package frontend {
    class MainApplication
    class MainController
    package file {
        class FileChooser
    }
    MainController ..> FileChooser
    MainApplication --> MainController
}
package backend {
    enum FileType {
        + SGM
        + CSV
    }
    enum MetricType {
        + EUCLIDEAN
        + MANHATTAN
        + CHEBYSHEV
        + CUSTOM
    }
    enum MeasureType {
        + GENERALIZED_NGRAM
        + GENERALIZED_NGRAM_WITH_LIMITATIONS
    }
    class KnnFacade {
        + process(FileType fileType, MetricType metricType, MeasureType measureType, int k, String path): String[]
        + calculateStatistics(String[] result, String[] expected): String
    }
}
MainController ---> KnnFacade
MainController ..> FileType
MainController ..> MetricType
MainController ..> MeasureType
```
## Backend
```plantuml
top to bottom direction
package backend {
    package reader {
        interface FileReader {
            + read(String path)
        }
        class SgmReader {
            + read(String path)
        }
        class CsvReader {
            + read(String path)
        }
        FileReader <|.. SgmReader
        FileReader <|.. CsvReader
        
        class ReaderFactory {
            + createFileReader(FileType fileType): FileReader
        }
        ReaderFactory ..> FileReader
        ReaderFactory ..> FileType
        enum FileType {
            + SGM
            + CSV
        }
    }
    package metrics {
        interface Metric {
            + calculateDistance(String text1, String text2)
        }
        class EuclideanMetric {
            + calculateDistance(String text1, String text2)
        }
        class ManhattanMetric {
            + calculateDistance(String text1, String text2)
        }
        class ChebyshevMetric {
            + calculateDistance(String text1, String text2)
        }
        class CustomMetric {
            + calculateDistance(String text1, String text2)
        }
        Metric <|.. EuclideanMetric
        Metric <|.. ManhattanMetric
        Metric <|.. ChebyshevMetric
        Metric <|.. CustomMetric
        
        class MetricFactory {
            + {static} createMetric(MetricType metricType): Metric
        }
        MetricFactory ..> Metric
        MetricFactory ..> MetricType
        enum MetricType {
            + EUCLIDEAN
            + MANHATTAN
            + CHEBYSHEV
            + CUSTOM
        }
    }
    package measure {
        interface Measure {
            + calculateMeasure(String text1, String text2)
        }
        class GeneralizedNgramMeasure {
            + calculateMeasure(String text1, String text2)
        }
        class GeneralizedNgramMeasureWithLimitations {
            + calculateMeasure(String text1, String text2)
        }
        Measure <|.. GeneralizedNgramMeasure
        Measure <|.. GeneralizedNgramMeasureWithLimitations
        
        class MeasureFactory {
            + {static} createMeasure(): Measure
        }
        MeasureFactory ..> Measure
        MeasureFactory ..> MeasureType
        enum MeasureType {
            + GENERALIZED_NGRAM
            + GENERALIZED_NGRAM_WITH_LIMITATIONS
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
            - Metric metric
            - Measure measure
            - Knn knn
            + Process(FileType fileType, MetricType metricType, MeasureType measureType, int k)
            + process(String path)
        }
        Process ...> ReaderFactory
        Process ...> MetricFactory
        Process ...> MeasureFactory
        Process ...> KnnFactory
        Process --> FileReader
        Process --> Metric
        Process --> Measure
        Process --> Knn
        class ProcessFactory {
            + createProcess(FileType fileType, MetricType metricType, MeasureType measureType, int k): Process
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
            + calculateStatistics(String[] result, String[] expected)
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
        + process(FileType fileType, MetricType metricType, MeasureType measureType, int k, String path): String[]
        + calculateStatistics(String[] result, String[] expected): String
    }
    KnnFacade ..> ProcessFactory
    KnnFacade ..> StatisticsFactory
}
```