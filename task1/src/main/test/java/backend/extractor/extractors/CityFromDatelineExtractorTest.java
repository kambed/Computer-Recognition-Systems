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
class CityFromDatelineExtractorTest {
    private final CityFromDatelineExtractor extractor = (CityFromDatelineExtractor) ExtractorFactory.createExtractor(
            ExtractorType.CITY_FROM_DATELINE
    );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .dateline("    SALVADOR, Feb 26 - ")
                                                .build()
                                ).build(),
                        "salvador"
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .dateline("")
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
    void extractTest(Article article, String expectedString) {
        assertEquals(expectedString, extractor.extract(article));
    }
}
