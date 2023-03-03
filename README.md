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
            + read(String path)
        }
        class SgmReader implements FileReader {
            + read(String path)
        }
        
        class ReaderFactory {
            + createFileReader(FileType fileType): FileReader
        }
        ReaderFactory ..> FileReader
        ReaderFactory ..> FileType
        enum FileType {
            SGM
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
            + createExtractor(ExtractorType extractorType): Extractor
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
            + createProcess(FileType fileType): Process
        }
        ProcessFactory ..> Process
        ProcessFactory ..> FileType
    }
    class KnnFacade {
        + process(FileType fileType, String path, ExtractorType extractorType): String[]
    }
    KnnFacade ..> ProcessFactory
}
```