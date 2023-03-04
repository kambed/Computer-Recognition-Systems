package backend;

import backend.extractor.ExtractorType;
import backend.process.ProcessFactory;
import backend.process.exception.NoDataFountException;
import backend.reader.FileType;

public class KnnFacade {
    public void process(ExtractorType type, FileType fileType, String filePath) throws NoDataFountException {
        ProcessFactory.createProcess(type, fileType).process(filePath);
    }
}
