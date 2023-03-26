package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DaysFromCreationDateExtractor extends Extractor<Long> {
    public DaysFromCreationDateExtractor() {
        domainMax = 15000.0;
    }
    @Override
    public Long extract(Article article) {
        return ChronoUnit.DAYS.between(article.getDate().toInstant(), Instant.now());
    }
}
