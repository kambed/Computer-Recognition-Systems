package backend.process;

import backend.extractor.Extractor;
import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.model.Article;
import backend.model.Root;
import backend.process.exception.NoDataFountException;
import backend.reader.FileReader;
import backend.reader.FileType;
import backend.reader.ReaderFactory;

import java.util.Optional;

public class Process {
    private final Extractor<?> extractor;
    private final FileReader reader;

    public Process(ExtractorType type, FileType fileType) {
        extractor = ExtractorFactory.createExtractor(type);
        reader = ReaderFactory.createReader(fileType);
    }

    public void process(String filePath) throws NoDataFountException {
        Optional<Root> data = reader.read(filePath);
        if (data.isEmpty()) {
            throw new NoDataFountException("No data found");
        }
        for (Article article : data.get().getArticles()) {
            // TODO: Save in variable and pass to the next part of process algorithm
            System.out.println(extractor.extract(article));
        }
    }
}
