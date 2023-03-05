package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Arrays;
import java.util.Optional;

public class UniqueWordsNumberExtractor implements Extractor<Integer> {
    @Override
    public Integer extract(Article article) {
        return Optional.ofNullable(article.getText().getText())
                .map(
                        text -> Arrays.stream(text.split("\\s+"))
                                .distinct()
                                .count()
                )
                .orElse(0L)
                .intValue();
    }
}
