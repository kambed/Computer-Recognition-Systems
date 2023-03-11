package backend.process;

import backend.extractor.Extractor;
import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.model.Article;
import backend.model.Root;
import backend.process.exception.NoDataFoundException;
import backend.reader.FileReader;
import backend.reader.FileType;
import backend.reader.ReaderFactory;

import java.util.*;

public class Process {
    private final List<Extractor<?>> extractors = new ArrayList<>();
    private final FileReader reader;

    public Process(List<ExtractorType> types, FileType fileType) {
        types.forEach(type -> extractors.add(ExtractorFactory.createExtractor(type)));
        reader = ReaderFactory.createReader(fileType);
    }

    public void process(String filePath) throws NoDataFoundException {
        Optional<Root> data = reader.read(filePath);
        if (data.isEmpty()) {
            throw new NoDataFoundException("No data found");
        }
        for (Article article : data.get().getArticles()) {
            List<Object> extractedFeatures = new ArrayList<>();
            for (Extractor<?> extractor : extractors) {
                extractedFeatures.add(extractor.extract(article));
            }
            // TODO: Save in variable and pass to the next part of process algorithm
            System.out.println(extractedFeatures);
        }
    }
}
