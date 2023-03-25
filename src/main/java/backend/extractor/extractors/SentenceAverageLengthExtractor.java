package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Arrays;
import java.util.Optional;

public class SentenceAverageLengthExtractor implements Extractor<Double> {
    @Override
    public Double extract(Article article) {
        return Optional.ofNullable(article.getText().getText())
                .map(text -> text.split("[.!?]+"))
                .map(sentences -> Arrays.stream(sentences)
                        .filter(s -> !s.isEmpty())
                        .mapToInt(sentence -> sentence.trim().split("\\s+").length)
                        .average()
                        .orElse(0.0))
                .orElse(0.0);
    }

    @Override
    public Double normalize(Double value) {
        return value / 150.0;
    }
}
