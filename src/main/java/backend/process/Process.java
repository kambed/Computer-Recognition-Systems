package backend.process;

import backend.extractor.Extractor;
import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.model.Article;
import backend.model.Root;
import backend.reader.FileReader;
import backend.reader.FileType;
import backend.reader.ReaderFactory;

import java.util.Optional;

public class Process {
    private final Extractor<?> extractor;
    private final FileReader reader;

    public Process(ExtractorType type, FileType fileType) {
        extractor = ExtractorFactory.createExtractor(type);
        this.reader = ReaderFactory.createReader(fileType);
    }

    public void process(String filePath) {
        Optional<Root> data = reader.read(filePath);
        if (data.isEmpty()) {
            throw new RuntimeException("No data found");
        }
        for (Article article : data.get().getArticles()) {
            System.out.println(extractor.extract(article));
        }
    }
}