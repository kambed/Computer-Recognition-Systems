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
        MOST_USED_WORD
        DAYS_FROM_CREATION_DATE
        SENTENCE_NUMBER
        WORD_NUMBER
        UNIQUE_WORDS_NUMBER
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
            class ArticleLengthExtractor implements Extractor {
                + extract(String[][] texts): int
            }
            class DaysFromCreationDateExtractor implements Extractor {
                + extract(String[][] texts): int
            }
            class MostUsedWorldExtractor implements Extractor {
                + extract(String[][] texts): String
            }
            class SentenceAverageLengthExtractor implements Extractor {
                + extract(String[][] texts): double
            }
            class SentenceNumberExtractor implements Extractor {
                + extract(String[][] texts): int
            }
            class UniqueWordsNumberExtractor implements Extractor {
                + extract(String[][] texts): int
            }
            class WordNumberExtractor implements Extractor {
                + extract(String[][] texts): long
            }
            class AmountOfNotLetterSignsExtractor implements Extractor {
                + extract(String[][] texts): long
            }
            class AmountOfNumbersExtractor implements Extractor {
                + extract(String[][] texts): long
            }
            class MostUsedCapitalLetterExtractor implements Extractor {
                + extract(String[][] texts): String
            }
            class MostUsedLetterExtractor implements Extractor {
                + extract(String[][] texts): String
            }
            class WordAverageLength implements Extractor {
                + extract(String[][] texts): double
            }
            class MostUsedWorkStartingInCapitalLetter implements Extractor {
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