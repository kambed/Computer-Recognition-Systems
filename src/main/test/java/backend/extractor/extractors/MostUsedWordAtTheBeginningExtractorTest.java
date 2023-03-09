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
class MostUsedWordAtTheBeginningExtractorTest {
    private final MostUsedWordAtTheBeginningExtractor extractor = (MostUsedWordAtTheBeginningExtractor) ExtractorFactory.createExtractor(
            ExtractorType.MOST_USED_WORD_AT_THE_BEGINNING
    );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("The United States of America, commonly known as the United States or America, is a country primarily located in North America. It consists of 50 states, a federal district, five major unincorporated territories, nine Minor Outlying Islands,[g] and 326 Indian reservations. The United States is also in free association with three Pacific Island sovereign states: the Federated States of Micronesia, the Marshall Islands, and the Republic of Palau. It is the world's third-largest country by both land and total area.[b] It shares land borders with Canada to its north and with Mexico to its south and has maritime borders with the Bahamas, Cuba, Russia, and other nations.[h] With a population of over 333 million,[i] it is the most populous country in the Americas and the third most populous in the world. The national capital of the United States is Washington, D.C. and its most populous city and principal financial center is New York City.")
                                                .build()
                                ).build(),
                        "america"
                ),
                Arguments.of(
                        Article.builder()
                                .text(
                                        TextContent.builder()
                                                .text("Hello hello my name is test")
                                                .build()
                                ).build(),
                        "hello"
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
                                                .text("Too short text")
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
