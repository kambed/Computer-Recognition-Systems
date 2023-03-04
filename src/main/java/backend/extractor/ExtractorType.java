package backend.extractor;

import backend.extractor.extractors.ArticleLengthExtractor;
import backend.extractor.extractors.MostUsedWorldExtractor;

public enum ExtractorType {
    ARTICLE_LENGTH(new ArticleLengthExtractor()),
    MOST_USED_WORD(new MostUsedWorldExtractor()),
    ;
    private final Extractor<?> extractor;

    ExtractorType(Extractor<?> extractor) {
        this.extractor = extractor;
    }

    public Extractor<?> getExtractor() {
        return extractor;
    }
}
