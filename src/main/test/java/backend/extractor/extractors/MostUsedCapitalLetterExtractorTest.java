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
class MostUsedCapitalLetterExtractorTest {
    private final MostUsedCapitalLetterExtractor extractor = (MostUsedCapitalLetterExtractor) ExtractorFactory.createExtractor(
            ExtractorType.MOST_USED_CAPITAL_LETTER
    );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("text without capital letters")
                                                .build()
                                ).build(),
                        null
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Most used capital letter is M")
                                                .build()
                                ).build(),
                        "M"
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
                                                .text("Two lETtErs same amount")
                                                .build()
                                ).build(),
                        "T"
                )
        );
    }


    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractTest(Article article, String expectedWord) {
        assertEquals(expectedWord, extractor.extract(article));
    }
}
