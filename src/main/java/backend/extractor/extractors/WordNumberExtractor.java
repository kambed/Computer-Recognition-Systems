package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Optional;

public class WordNumberExtractor implements Extractor<Integer> {
    @Override
    public Integer extract(Article article) {
        return Optional.ofNullable(article.getText().getText())
                .map(text -> text.trim().split("\\s+").length)
                .orElse(0);
    }
}
