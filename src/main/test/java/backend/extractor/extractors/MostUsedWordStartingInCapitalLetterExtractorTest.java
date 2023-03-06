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
class MostUsedWordStartingInCapitalLetterExtractorTest {
    private final MostUsedWordStartingInCapitalLetterExtractor extractor =
            (MostUsedWordStartingInCapitalLetterExtractor) ExtractorFactory.createExtractor(
                    ExtractorType.MOST_USED_WORD_STARTING_IN_CAPITAL_LETTER
            );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Text is about USA, USA. Text. Text.")
                                                .build()
                                ).build(),
                        "usa"
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Text is about England and England is country in Europe.")
                                                .build()
                                ).build(),
                        "england"
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("")
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
