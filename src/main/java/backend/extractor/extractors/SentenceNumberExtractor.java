package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Optional;

public class SentenceNumberExtractor implements Extractor<Long> {
    @Override
    public Long extract(Article article) {
        return Optional.ofNullable(article.getText()
                .getText())
                .orElse("")
                .chars()
                .filter(c -> c == '.' || c == '!' || c == '?')
                .count();
    }
}
