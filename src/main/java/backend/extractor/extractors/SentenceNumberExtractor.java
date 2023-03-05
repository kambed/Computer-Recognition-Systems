package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

public class SentenceNumberExtractor implements Extractor<Long> {
    @Override
    public Long extract(Article article) {
        return article.getText()
                .getText()
                .chars()
                .filter(c -> c == '.' || c == '!' || c == '?')
                .count();
    }
}
