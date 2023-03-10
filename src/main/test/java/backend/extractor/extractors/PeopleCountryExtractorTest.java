package backend.extractor.extractors;

import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.model.Article;
import backend.model.TextContent;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PeopleCountryExtractorTest {
    private final PeopleCountryExtractor extractor = (PeopleCountryExtractor) ExtractorFactory.createExtractor(
            ExtractorType.PEOPLE_COUNTRY
    );

    public Stream<Arguments> extractTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .people(List.of("MULRONEY", "REAGAN", "JAMES-BAKER"))
                                .build(),
                        "united states"
                ),
                Arguments.of(
                        Article.builder()
                                .people(List.of("STOLTENBERG", "JAMES-BAKER"))
                                .build(),
                        "west-germany"
                ),
                Arguments.of(
                        Article.builder()
                                .build(),
                        ""
                ),
                Arguments.of(
                        Article.builder()
                                .people(List.of(""))
                                .build(),
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
