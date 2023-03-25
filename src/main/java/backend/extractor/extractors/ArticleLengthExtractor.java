package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Optional;

public class ArticleLengthExtractor extends Extractor<Integer> {
    public ArticleLengthExtractor() {
        domainMax = 15000.0;
    }

    @Override
    public Integer extract(Article article) {
        return Optional.ofNullable(article.getText().getText())
                .map(String::length)
                .orElse(0);
    }
}
