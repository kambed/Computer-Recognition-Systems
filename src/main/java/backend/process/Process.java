package backend.process;

import backend.extractor.Extractor;
import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.reader.FileReader;
import backend.reader.FileType;
import backend.reader.ReaderFactory;

import java.util.Map;

public class Process {
    private final Extractor<?> extractor;
    private final FileReader reader;
    public Process(ExtractorType type, FileType fileType) {
        extractor = ExtractorFactory.createExtractor(type);
        this.reader = ReaderFactory.createReader(fileType);
    }

    public void process(String filePath) {
        Map<String, String> data = reader.read(filePath);
        System.out.println(extractor.extract(data));
    }
}
