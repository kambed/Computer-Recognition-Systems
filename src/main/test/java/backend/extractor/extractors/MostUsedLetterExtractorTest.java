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
class MostUsedLetterExtractorTest {
    private final MostUsedLetterExtractor extractor = (MostUsedLetterExtractor) ExtractorFactory.createExtractor(
            ExtractorType.MOST_USED_LETTER
    );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("The most common letter in in English is T")
                                                .build()
                                ).build(),
                        "t"
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Najpopularniejsza litera w jezyku polskim to A")
                                                .build()
                                ).build(),
                        "a"
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("")
                                                .build()
                                ).build(),
                        null
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .build()
                                ).build(),
                        null
                )
        );
    }


    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractTest(Article article, String expectedWord) {
        assertEquals(expectedWord, extractor.extract(article));
    }
}
