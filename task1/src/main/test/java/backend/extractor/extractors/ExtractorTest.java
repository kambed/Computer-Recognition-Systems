package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExtractorTest {
    @Test
    void normalizationOver1Test() {
        Extractor<Integer> numberExtractor = new Extractor<>() {
            @Override
            public Integer extract(Article article) {
                domainMax = 1.0;
                return 2;
            }
        };
        assertEquals(1.0, numberExtractor.extractAndNormalize(null));
    }

    @Test
    void normalizationBelow0Test() {
        Extractor<Integer> numberExtractor = new Extractor<>() {
            @Override
            public Integer extract(Article article) {
                return -1;
            }
        };
        assertEquals(0.0, numberExtractor.extractAndNormalize(null));
    }

    @Test
    void unsupportedNormalizationTest() {
        Extractor<String> numberExtractor = new Extractor<>() {
            @Override
            public String extract(Article article) {
                return "test";
            }
        };
        assertEquals("test", numberExtractor.extractAndNormalize(null));
    }
}
