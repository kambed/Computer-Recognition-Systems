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
            class ArticleLengthExtractor {
                + extract(String[][] texts): int
            }
            class CityFromDatelineExtractor {
                + extract(String[][] texts): String
            }
            class DaysFromCreationDateExtractor {
                + extract(String[][] texts): int
            }
            class MostUsedCityNameMappedToCountryExtractor {
                + extract(String[][] texts): String
            }
            class MostUsedCountryNameExtractor {
                + extract(String[][] texts): String
            }
            class MostUsedCountryNameInTitle {
                + extract(String[][] texts): String
            }
            class MostUsedCurrencyExtractor {
                + extract(String[][] texts): String
            }
            class MostUsedWordAtTheBeginningExtractor {
                + extract(String[][] texts): String
            }
            class MostUsedWordStartingInCapitalLetterExtractor {
                + extract(String[][] texts): String
            }
            class MostUsedYearExtractor {
                + extract(String[][] texts): long
            }
            class PeopleCountryExtractor {
                + extract(String[][] texts): String
            }
            class SentenceAverageLengthExtractor {
                + extract(String[][] texts): double
            }
            Extractor <|.. ArticleLengthExtractor
            Extractor <|.. CityFromDatelineExtractor
            Extractor <|.. DaysFromCreationDateExtractor
            Extractor <|.. MostUsedCityNameMappedToCountryExtractor
            Extractor <|.. MostUsedCountryNameExtractor
            Extractor <|.. MostUsedCountryNameInTitle
            Extractor <|.. MostUsedCurrencyExtractor
            Extractor <|.. MostUsedWordAtTheBeginningExtractor
            Extractor <|.. MostUsedWordStartingInCapitalLetterExtractor
            Extractor <|.. MostUsedYearExtractor
            Extractor <|.. PeopleCountryExtractor
            Extractor <|.. SentenceAverageLengthExtractor
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
    package process {
        class Process {
            - FileReader fileReader
            - String[] countriesOfInterest
            - Extractor[] extractors
            + Process(ExtractorType[] extractorTypes, FileType fileType)
            + process(String[] path)
        }
        Process ...> ReaderFactory
        Process ...> ExtractorFactory
        MostUsedGeographicalNameMappedToCountry ..> CsvReader
        class ProcessFactory {
            + {static} createProcess(ExtractorType[] extractorTypes, FileType fileType): Process
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