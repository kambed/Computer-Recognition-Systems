package backend.extractor;

import backend.extractor.extractors.*;

public enum ExtractorType {
    ARTICLE_LENGTH(new ArticleLengthExtractor()),
    MOST_USED_WORD(new MostUsedWordExtractor()),
    DAYS_FROM_CREATION_DATE(new DaysFromCreationDateExtractor()),
    SENTENCE_NUMBER(new SentenceNumberExtractor()),
    WORD_NUMBER(new WordNumberExtractor()),
    UNIQUE_WORDS_NUMBER(new UniqueWordsNumberExtractor()),
    SENTENCE_AVERAGE_LENGTH(new SentenceAverageLengthExtractor()),
    MOST_USED_CAPITAL_LETTER(new MostUsedCapitalLetterExtractor());

    private final Extractor<?> extractor;

    ExtractorType(Extractor<?> extractor) {
        this.extractor = extractor;
    }

    public Extractor<?> getExtractor() {
        return extractor;
    }
}
