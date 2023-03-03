package backend.process;

import backend.extractor.ExtractorType;
import backend.reader.FileType;

public class ProcessFactory {
    public Process createProcess(ExtractorType type, FileType fileType) {
        return new Process(type, fileType);
    }

}
