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
    enum ExtractorType {
        + String
        + Number
        + getExtractorReturnType()
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
            + SGM
        }
    }
    package extractor {
        interface Extractor {
            + extract(String[][] texts)
        }
        class Extractor1 implements Extractor {
            + extract(String[][] texts)
        }
        class Extractor2 implements Extractor {
            + extract(String[][] texts)
        }
        class ExtractorFactory {
            + createExtractor(ExtractorType extractorType): Extractor
        }
        enum ExtractorType {
            + String
            + Number
            + getExtractorReturnType()
        }
        ExtractorFactory ..> Extractor
        ExtractorFactory ..> ExtractorType
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
        + process(FileType fileType, String path, ExtractorType extractorType): String[]
    }
    KnnFacade ..> ProcessFactory
}
```