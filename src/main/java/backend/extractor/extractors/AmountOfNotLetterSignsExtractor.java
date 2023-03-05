package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Optional;

public class AmountOfNotLetterSignsExtractor implements Extractor<Long> {

    @Override
    public Long extract(Article article) {
        return Optional.ofNullable(article.getText()
                .getPreprocessedText())
                .orElse("")
                .chars()
                .boxed()
                .filter(c -> c < 65 || (c > 90 && c < 97) || c > 122)
                .count();
    }
}
