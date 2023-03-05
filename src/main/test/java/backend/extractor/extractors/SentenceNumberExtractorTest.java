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
class SentenceNumberExtractorTest {
    private final SentenceNumberExtractor extractor = (SentenceNumberExtractor) ExtractorFactory.createExtractor(
            ExtractorType.SENTENCE_NUMBER
    );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("This text tries to be sentence, but it is not")
                                                .build()
                                ).build(),
                        0
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("This is first sentence.")
                                                .build()
                                ).build(),
                        1
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("This is sentence with exclamation mark!")
                                                .build()
                                ).build(),
                        1
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("This is sentence with question mark?")
                                                .build()
                                ).build(),
                        1
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("This is sentence with comma, and this is continuation of this sentence.")
                                                .build()
                                ).build(),
                        1
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("This is sentence with exclamation mark! This is second sentence.")
                                                .build()
                                ).build(),
                        2
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("This is sentence with question mark? This is second sentence. This is third sentence.")
                                                .build()
                                ).build(),
                        3
                )
        );
    }


    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractTest(Article article, int expectedLength) {
        assertEquals(expectedLength, extractor.extract(article));
    }
}
