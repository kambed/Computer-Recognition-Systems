package backend.extractor.extractors;

import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.model.Article;
import backend.model.TextContent;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MostUsedCityNameMappedToCountryExtractorTest {
    private final MostUsedCityNameMappedToCountryExtractor extractor = (MostUsedCityNameMappedToCountryExtractor) ExtractorFactory.createExtractor(
            ExtractorType.MOST_USED_CITY_NAME_MAPPED_TO_COUNTRY
    );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("If the text is about french cities like Paris or Marseille, we get France even though we say something about Tokyo")
                                                .build()
                                ).build(),
                        "france"
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("What about text that has two word city like New York or Los Angeles? Hmmm?")
                                                .build()
                                ).build(),
                        "united states"
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Hello hello my name is test")
                                                .build()
                                ).build(),
                        ""
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("")
                                                .build()
                                ).build(),
                        ""
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .build()
                                ).build(),
                        ""
                )
        );
    }


    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractTest(Article article, String expectedWord) {
        assertEquals(expectedWord, extractor.extract(article));
    }
}
