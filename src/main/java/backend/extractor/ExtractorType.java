package backend.extractor;

import backend.extractor.extractors.ArticleLengthExtractor;
import backend.extractor.extractors.MostUsedWordExtractor;
import backend.extractor.extractors.DaysFromCreationDateExtractor;

public enum ExtractorType {
    ARTICLE_LENGTH(new ArticleLengthExtractor()),
    MOST_USED_WORD(new MostUsedWordExtractor()),
    DAYS_FROM_CREATION_DATE(new DaysFromCreationDateExtractor());

    private final Extractor<?> extractor;

    ExtractorType(Extractor<?> extractor) {
        this.extractor = extractor;
    }

    public Extractor<?> getExtractor() {
        return extractor;
    }
}
