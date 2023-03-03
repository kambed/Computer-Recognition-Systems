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
    }
    class KnnFacade {
        + process(FileType fileType, String path, String[][] features): String[]
    }
}
MainController ---> KnnFacade
MainController ..> FileType
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
            + SGM
        }
    }
    package extractor {
        class Extractor {
            + extract(String[][] texts, String[] features)
        }
        class ExtractorFactory {
            + createExtractor(): Extractor
        }
        ExtractorFactory ..> Extractor
    }
    package process {
        class Process {
            - FileReader fileReader
            + Process(FileType fileType, String[][] features)
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
        + process(FileType fileType, String path, String[][] features): String[]
    }
    KnnFacade ..> ProcessFactory
}
```