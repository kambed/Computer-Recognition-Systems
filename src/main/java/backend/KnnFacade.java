package backend;

import backend.extractor.ExtractorType;
import backend.process.ProcessFactory;
import backend.reader.FileType;

public class KnnFacade {
    private final ProcessFactory processFactory = new ProcessFactory();
    public void process(ExtractorType type, FileType fileType, String filePath) {
        processFactory.createProcess(type, fileType).process(filePath);
    }
}
