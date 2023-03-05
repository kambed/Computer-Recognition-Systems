package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Optional;

public class AmountOfNumbersExtractor implements Extractor<Long> {
    @Override
    public Long extract(Article article) {
        return Optional.ofNullable(article.getText()
                        .getPreprocessedText())
                .orElse("")
                .chars()
                .filter(c -> c >= 48 && c <= 57)
                .boxed()
                .count();
    }
}
