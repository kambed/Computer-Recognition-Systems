package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Arrays;
import java.util.Optional;

public class WordAverageLengthExtractor implements Extractor<Double> {

    @Override
    public Double extract(Article article) {
        return Optional.ofNullable(
                        article.getText().getText()
                ).map(
                        text -> text.trim().split("\\s+")
                ).map(
                        words -> Arrays.stream(words)
                                .mapToInt(String::length)
                                .average().orElse(0.0)
                ).orElse(0.0);
    }
}
