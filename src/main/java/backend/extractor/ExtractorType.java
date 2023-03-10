package backend.extractor;

import backend.extractor.extractors.*;

public enum ExtractorType {
    ARTICLE_LENGTH(new ArticleLengthExtractor()),
    MOST_USED_WORD_AT_THE_BEGINNING(new MostUsedWordAtTheBeginningExtractor()),
    DAYS_FROM_CREATION_DATE(new DaysFromCreationDateExtractor()),
    SENTENCE_AVERAGE_LENGTH(new SentenceAverageLengthExtractor()),
    MOST_USED_YEAR(new MostUsedYearExtractor()),
    MOST_USED_WORD_STARTING_IN_CAPITAL_LETTER(new MostUsedWordStartingInCapitalLetterExtractor()),
    CITY_FROM_DATELINE(new CityFromDatelineExtractor()),
    MOST_USED_COUNTRY_NAME(new MostUsedCountryNameExtractor()),
    MOST_USED_COUNTRY_NAME_IN_TITLE(new MostUsedCountryNameInTitleExtractor()),
    PEOPLE_COUNTRY(new PeopleCountryExtractor()),
    MOST_USED_CITY_NAME_MAPPED_TO_COUNTRY(new MostUsedCityNameMappedToCountryExtractor()),
    MOST_USED_CURRENCY(new MostUsedCurrencyExtractor());

    private final Extractor<?> extractor;

    ExtractorType(Extractor<?> extractor) {
        this.extractor = extractor;
    }

    public Extractor<?> getExtractor() {
        return extractor;
    }
}
