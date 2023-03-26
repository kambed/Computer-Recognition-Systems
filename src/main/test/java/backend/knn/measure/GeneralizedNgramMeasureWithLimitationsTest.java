package backend.knn.measure;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GeneralizedNgramMeasureWithLimitationsTest {

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("NORTH AMERICA", "AMERICA", 0.478),
                Arguments.of("uNiTeD sTaTeS", "UnItEd KiNgDoM", 0.44),
                Arguments.of("AMERICA", "AMERICA", 1.0),
                Arguments.of("NONO", "YES", 0.0)
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void GeneralizedNgramMeasureCalculationTest(String text1, String text2, Double expected) {
        assertEquals(expected, MeasureFactory.createGeneralizedNgramWithLimitations(2, 3)
                .calculateMeasure(text1, text2), 0.001);
    }

    static Stream<Arguments> generateDataForMetric() {
        return Stream.of(
                Arguments.of("NORTH AMERICA", "AMERICA", 0.522),
                Arguments.of("uNiTeD sTaTeS", "UnItEd KiNgDoM", 0.56),
                Arguments.of("AMERICA", "AMERICA", 0.0),
                Arguments.of("NONO", "YES", 1.0)
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataForMetric")
    void GeneralizedNgramMetricCalculationTest(String text1, String text2, Double expected) {
        assertEquals(expected, MeasureFactory.createGeneralizedNgramWithLimitations(2, 3)
                .calculateMetric(text1, text2), 0.001);
    }
}