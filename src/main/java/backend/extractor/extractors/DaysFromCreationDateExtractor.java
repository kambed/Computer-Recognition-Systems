package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class DaysFromCreationDateExtractor extends Extractor<Long> {
    public DaysFromCreationDateExtractor() {
        domainMax = 15000.0;
    }

    @Override
    public Long extract(Article article) {
        return ChronoUnit.DAYS.between(article.getDate().toInstant(),
                LocalDateTime.of(2023, Month.APRIL, 1, 12, 0).toInstant(ZoneOffset.UTC));
    }
}
