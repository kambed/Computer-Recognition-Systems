package backend;

import backend.extractor.ExtractorType;
import backend.process.ProcessFactory;
import backend.process.exception.NoDataFoundException;
import backend.reader.FileType;

import java.util.List;

public class KnnFacade {
    public void process(List<ExtractorType> types, FileType fileType, String filePath) throws NoDataFoundException {
        ProcessFactory.createProcess(types, fileType).process(filePath);
    }
}
