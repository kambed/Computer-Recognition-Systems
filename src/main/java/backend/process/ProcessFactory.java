package backend.process;

import backend.extractor.ExtractorType;
import backend.reader.FileType;

public class ProcessFactory {
    private ProcessFactory() {
    }
    public static Process createProcess(ExtractorType type, FileType fileType) {
        return new Process(type, fileType);
    }
}
