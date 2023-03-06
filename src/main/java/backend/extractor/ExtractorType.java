package backend.extractor;

import backend.extractor.extractors.*;

public enum ExtractorType {
    ARTICLE_LENGTH(new ArticleLengthExtractor()),
    MOST_USED_WORD(new MostUsedWordExtractor()),
    DAYS_FROM_CREATION_DATE(new DaysFromCreationDateExtractor()),
    SENTENCE_AVERAGE_LENGTH(new SentenceAverageLengthExtractor()),
    AMOUNT_OF_NOT_LETTERS(new AmountOfNotLetterSignsExtractor()),
    MOST_USED_YEAR(new MostUsedYearExtractor()),
    MOST_USED_WORD_STARTING_IN_CAPITAL_LETTER(new MostUsedWordStartingInCapitalLetterExtractor()),
    CITY_FROM_DATELINE(new CityFromDatelineExtractor());

    private final Extractor<?> extractor;

    ExtractorType(Extractor<?> extractor) {
        this.extractor = extractor;
    }

    public Extractor<?> getExtractor() {
        return extractor;
    }
}
