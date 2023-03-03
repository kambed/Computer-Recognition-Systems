package backend;

import backend.extractor.ExtractorType;
import backend.process.ProcessFactory;
import backend.reader.FileType;

public class KnnFacade {
    public void process(ExtractorType type, FileType fileType, String filePath) {
        ProcessFactory.createProcess(type, fileType).process(filePath);
    }
}
