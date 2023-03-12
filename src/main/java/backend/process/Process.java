package backend.process;

import backend.extractor.Extractor;
import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.model.Article;
import backend.reader.FileReader;
import backend.reader.FileType;
import backend.reader.ReaderFactory;

import java.util.*;

public class Process {
    private final List<Extractor<?>> extractors = new LinkedList<>();
    private final FileReader reader;
    private final List<String> countriesOfInterest = List.of("west-germany", "usa", "france", "uk", "canada", "japan");

    public Process(List<ExtractorType> extractorTypes, FileType fileType) {
        extractorTypes.forEach(type -> extractors.add(ExtractorFactory.createExtractor(type)));
        reader = ReaderFactory.createReader(fileType);
    }

    public void process(List<String> filePaths) {
        List<Article> articles = filePaths.stream()
                .map(path -> reader.read(path).orElse(null))
                .filter(Objects::nonNull)
                .flatMap(list -> list.getArticles().stream())
                .filter(article -> article.getPlaces().size() == 1
                        && countriesOfInterest.contains(article.getPlaces().get(0)))
                .toList();
        for (Article article : articles) {
            List<Object> extractedFeatures = new ArrayList<>();
            for (Extractor<?> extractor : extractors) {
                extractedFeatures.add(extractor.extract(article));
            }
            // TODO: Save in variable and pass to the next part of process algorithm
            System.out.println(extractedFeatures);
        }
    }
}
