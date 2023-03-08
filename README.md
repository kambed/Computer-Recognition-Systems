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
        SGM
        - FileReader fileReader
        + getFileReader(): FileReader
    }
    enum ExtractorType {
        ARTICLE_LENGTH
        MOST_USED_WORD_AT_THE_BEGINNING
        DAYS_FROM_CREATION_DATE
        SENTENCE_AVERAGE_LENGTH
        MOST_USED_YEAR
        MOST_USED_WORD_STARTING_IN_CAPITAL_LETTER
        CITY_FROM_DATELINE
        MOST_USED_GEOGRAPHICAL_NAME_MAPPED_TO_COUNTRY
        - Extractor extractor
        + getExtractor(): Extractor
    }
    class KnnFacade {
        + process(FileType fileType, String path, ExtractorType extractorType): String[]
    }
}
MainController ---> KnnFacade
MainController ..> FileType
MainController ..> ExtractorType
```
## Backend
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
            + readDictionary(String path): String[][]
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
    package extractor {
        interface Extractor {
            + extract(String[][] texts)
        }
        package extractors {
            class MostUsedYearExtractor implements Extractor {
                + extract(String[][] texts): long
            }
            class ArticleLengthExtractor implements Extractor {
                + extract(String[][] texts): int
            }
            class CityFromDatelineExtractor implements Extractor {
                + extract(String[][] texts): String
            }
            class DaysFromCreationDateExtractor implements Extractor {
                + extract(String[][] texts): int
            }
            class MostUsedWordExtractor implements Extractor {
                + extract(String[][] texts): String
            }
            class SentenceAverageLengthExtractor implements Extractor {
                + extract(String[][] texts): double
            }
            class MostUsedWorkStartingInCapitalLetter implements Extractor {
                + extract(String[][] texts): String
            }
            class MostUsedGeographicalNameMappedToCountry implements Extractor {
                + extract(String[][] texts): String
            }
        }
        class ExtractorFactory {
            + {static} createExtractor(ExtractorType extractorType): Extractor
        }
        enum ExtractorType {
            ARTICLE_LENGTH
            MOST_USED_WORD
            DAYS_FROM_CREATION_DATE
            SENTENCE_NUMBER
            WORD_NUMBER
            UNIQUE_WORDS_NUMBER
            SENTENCE_AVERAGE_LENGTH
            MOST_USED_CAPITAL_LETTER
            AMOUNT_OF_NOT_LETTERS
            WORD_AVERAGE_LENGTH
            AMOUNT_OF_NUMBERS
            MOST_USED_LETTER
            MOST_USED_WORD_STARTING_IN_CAPITAL_LETTER
            - Extractor extractor
            + getExtractor(): Extractor
        }
        ExtractorFactory ..> Extractor
        ExtractorFactory ..> ExtractorType
    }
    package process {
        class Process {
            - FileReader fileReader
            + Process(FileType fileType)
            + process(String path)
        }
        Process ...> ReaderFactory
        Process ...> ExtractorFactory
        MostUsedGeographicalNameMappedToCountry ..> CsvReader
        class ProcessFactory {
            + {static} createProcess(FileType fileType): Process
        }
        ProcessFactory ..> Process
        ProcessFactory ..> FileType
    }
    package model {
        class Root {
            - articles: Article[]
        }
        FileReader ..> Root
        Extractor ..> Root
        Process ..> Root
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
    class KnnFacade {
        + process(FileType fileType, String path, ExtractorType extractorType): String[]
    }
    KnnFacade ..> ProcessFactory
}
```