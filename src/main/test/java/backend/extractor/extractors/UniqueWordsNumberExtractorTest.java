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
class UniqueWordsNumberExtractorTest {
    private final UniqueWordsNumberExtractor extractor = (UniqueWordsNumberExtractor) ExtractorFactory.createExtractor(
            ExtractorType.UNIQUE_WORDS_NUMBER
    );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .build()
                                ).build(),
                        0
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Hello World!")
                                                .build()
                                ).build(),
                        2
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Hello World! Hello World!")
                                                .build()
                                ).build(),
                        2
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Hello World! Hello World! Hello World!")
                                                .build()
                                ).build(),
                        2
                )
        );
    }


    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractTest(Article article, int expectedLength) {
        assertEquals(expectedLength, extractor.extract(article));
    }
}
