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
class SentenceAverageLengthExtractorTest {
    private final SentenceAverageLengthExtractor extractor = (SentenceAverageLengthExtractor) ExtractorFactory.createExtractor(
            ExtractorType.SENTENCE_AVERAGE_LENGTH
    );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .build()
                                ).build(),
                        0.0
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Hello World!")
                                                .build()
                                ).build(),
                        2.0
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Longer Hello World! Hello World!")
                                                .build()
                                ).build(),
                        2.5
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Longer Hello World! Hello World! Hello World!")
                                                .build()
                                ).build(),
                        7.0 / 3.0
                )
        );
    }


    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractTest(Article article, double expectedLength) {
        assertEquals(expectedLength, extractor.extract(article));
    }
}
