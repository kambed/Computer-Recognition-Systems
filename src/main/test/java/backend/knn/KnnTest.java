package backend.knn;

import backend.knn.measure.MeasureFactory;
import backend.knn.metric.MetricType;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnnTest {
    private Knn knn;

    @BeforeEach
    void setUp() {
        knn = KnnFactory.createKnn(
                3,
                MetricType.EUCLIDEAN.getMetric(),
                MeasureFactory.createGeneralizedNgramWithLimitations(2, 4),
                List.of(
                        new Pair<>("A", List.of(0.1, 0.2, 0.3, "Tom has a cat")),
                        new Pair<>("A", List.of(0.2, 0.3, 0.4, "Tom has a dog")),
                        new Pair<>("A", List.of(0.3, 0.4, 0.5, "Tom has a fish")),
                        new Pair<>("B", List.of(0.4, 0.5, 0.6, "Alice has a cat")),
                        new Pair<>("B", List.of(0.5, 0.6, 0.7, "Alice has a dog")),
                        new Pair<>("B", List.of(0.6, 0.7, 0.8, "Alice has a fish"))
                )
        );
    }

    static Stream<Arguments> calculateKnnDataProvider() {
        return Stream.of(
                Arguments.of(List.of(0.1, 0.2, 0.3, "Tom has a kitty"), "A"),
                Arguments.of(List.of(0.2, 0.35, 0.4, "Tom has a doggy"), "A"),
                Arguments.of(List.of(0.3, 0.4, 0.5, "Alice has a turtle"), "B"),
                Arguments.of(List.of(0.12, 0.13, 0.14, "Tola is a cat"), "A")
        );
    }
    @ParameterizedTest
    @MethodSource("calculateKnnDataProvider")
    void calculateKnn(List<Object> vector, String expected) {
        String result = knn.calculateKnn(vector);
        assertEquals(expected, result);
    }
}