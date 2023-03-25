package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DaysFromCreationDateExtractor implements Extractor<Long> {
    @Override
    public Long extract(Article article) {
        return ChronoUnit.DAYS.between(article.getDate().toInstant(), Instant.now());
    }

    @Override
    public Double normalize(Long value) {
        return value / 15000.0;
    }
}
