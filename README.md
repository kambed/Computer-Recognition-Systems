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
        STRING
        NUMBER
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
                + extract(String[][] texts)
            }
            class MostUsedWorldExtractor implements Extractor {
                + extract(String[][] texts)
            }
        }
        class ExtractorFactory {
            + {static} createExtractor(ExtractorType extractorType): Extractor
        }
        enum ExtractorType {
            STRING
            NUMBER
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