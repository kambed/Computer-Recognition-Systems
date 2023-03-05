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
    MOST_USED_CAPITAL_LETTER(new MostUsedCapitalLetterExtractor()),
    AMOUNT_OF_NOT_LETTERS(new AmountOfNotLetterSignsExtractor()),
    WORD_AVERAGE_LENGTH(new WordAverageLengthExtractor()),
    AMOUNT_OF_NUMBERS(new AmountOfNumbersExtractor()),
    MOST_USED_LETTER(new MostUsedLetterExtractor()),
    MOST_USED_WORD_STARTING_IN_CAPITAL_LETTER(new MostUsedWorkStartingInCapitalLetterExtractor());

    private final Extractor<?> extractor;

    ExtractorType(Extractor<?> extractor) {
        this.extractor = extractor;
    }

    public Extractor<?> getExtractor() {
        return extractor;
    }
}
