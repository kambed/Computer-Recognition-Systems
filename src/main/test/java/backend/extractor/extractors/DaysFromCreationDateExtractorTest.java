package backend.extractor.extractors;

import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.model.Article;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DaysFromCreationDateExtractorTest {
    private final DaysFromCreationDateExtractor extractor = (DaysFromCreationDateExtractor) ExtractorFactory.createExtractor(
            ExtractorType.DAYS_FROM_CREATION_DATE
    );

    public Stream<Arguments> extractTestDataProvider() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -10);
        calendar.add(Calendar.HOUR, -2);
        return Stream.of(
                Arguments.of(
                        Article.builder()
                                .date(new Date())
                                .build(),
                        0
                ),
                Arguments.of(
                        Article.builder()
                                .date(calendar.getTime())
                                .build(),
                        10
                )
        );
    }


    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractTest(Article article, int expectedNumberOfDays) {
        assertEquals(expectedNumberOfDays, extractor.extract(article));
    }


    @ParameterizedTest
    @MethodSource("extractTestDataProvider")
    void extractAndNormalizeTest(Article article, int expectedNumberOfDays) {
        assertEquals(expectedNumberOfDays / 15000.0, extractor.extractAndNormalize(article));
    }
}
