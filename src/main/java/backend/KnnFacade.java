package backend;

import backend.extractor.ExtractorType;
import backend.process.ProcessFactory;
import backend.reader.FileType;

import java.util.List;

public class KnnFacade {
    public void process(List<ExtractorType> types, FileType fileType, List<String> filePath) {
        ProcessFactory.createProcess(types, fileType).process(filePath);
    }
}
