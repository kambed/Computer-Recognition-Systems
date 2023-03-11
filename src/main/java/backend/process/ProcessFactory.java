package backend.process;

import backend.extractor.ExtractorType;
import backend.reader.FileType;

import java.util.List;

public class ProcessFactory {
    private ProcessFactory() {
    }
    public static Process createProcess(List<ExtractorType> types, FileType fileType) {
        return new Process(types, fileType);
    }
}
