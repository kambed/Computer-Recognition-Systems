package backend;

import backend.extractor.ExtractorType;
import backend.process.ProcessFactory;
import backend.reader.FileType;

import java.util.List;
import java.util.Map;

public class KnnFacade {
    public Map<String, Double> process(List<ExtractorType> types, FileType fileType, List<String> filePath) {
        return ProcessFactory.createProcess(types, fileType).process(filePath);
    }
}
