package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExtractorInterfaceTest {
    @Test
    void normalizationOver1Test() {
        Extractor<Integer> numberExtractor = new Extractor<>() {
            @Override
            public Integer extract(Article article) {
                return 1;
            }

            public Double normalize(Integer value) {
                return 1.5;
            }
        };
        assertEquals(1.0, numberExtractor.extractAndNormalize(null));
    }

    @Test
    void normalizationBelow0Test() {
        Extractor<Integer> numberExtractor = new Extractor<>() {
            @Override
            public Integer extract(Article article) {
                return 1;
            }

            public Double normalize(Integer value) {
                return -1.5;
            }
        };
        assertEquals(0.0, numberExtractor.extractAndNormalize(null));
    }

    @Test
    void unsupportedNormalizationTest() {
        Extractor<String> numberExtractor = article -> "test";
        assertEquals("test", numberExtractor.extractAndNormalize(null));
    }
}
