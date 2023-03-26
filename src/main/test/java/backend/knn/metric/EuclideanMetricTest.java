package backend.knn.metric;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EuclideanMetricTest {

    @ParameterizedTest
    @MethodSource("generateData")
    void EuclideanMetricCalculationTest(List<Double> vector1, List<Double> vector2, Double expected) {
        assertEquals(expected, MetricFactory.createExtractor(MetricType.EUCLIDEAN)
                .calculateDistance(vector1, vector2), 0.001);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(List.of(0.3267, 0.8773, 0.44), List.of(0.6547, 0.8776, 0.0), 0.549),
                Arguments.of(List.of(1.0, 1.0, 1.0), List.of(0.0, 0.0, 0.0), Math.sqrt(3.0)),
                Arguments.of(List.of(0.0, 0.0, 0.0), List.of(0.0, 0.0, 0.0), 0.0)
        );
    }
}