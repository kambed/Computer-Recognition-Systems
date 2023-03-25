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
class MostUsedYearExtractorTest {
    private final MostUsedYearExtractor extractor = (MostUsedYearExtractor) ExtractorFactory
            .createExtractor(
                    ExtractorType.MOST_USED_YEAR
            );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Text 123 with 545 many 1410 years. 1999 and 1999")
                                                .build()
                                ).build(),
                        1999
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Text 123 with 545 many 1410 years. 1999 and 1999 or maybe 1410 again?")
                                                .build()
                                ).build(),
                        1410
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Hello World! no years here")
                                                .build()
                                ).build(),
                        0
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .build()
                                ).build(),
                        0
                )
        );
    }

    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractTest(Article article, Integer expectedLength) {
        assertEquals(expectedLength, extractor.extract(article));
    }


    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractAndNormalizeTest(Article article, Integer expectedLength) {
        assertEquals(expectedLength / 1999.0, extractor.extractAndNormalize(article));
    }
}
