package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Optional;

public class ArticleLengthExtractor implements Extractor<Integer> {
    @Override
    public Integer extract(Article article) {
        return Optional.ofNullable(article.getText().getText())
                .map(String::trim)
                .map(String::length)
                .orElse(0);
    }
}
