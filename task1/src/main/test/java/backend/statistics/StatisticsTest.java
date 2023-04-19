package backend.statistics;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {
    Statistics statistics = StatisticsFactory.createStatistics(List.of(
            new Pair<>("a", "a"),
            new Pair<>("a", "b"),
            new Pair<>("b", "b"),
            new Pair<>("b", "c"),
            new Pair<>("b", "c"),
            new Pair<>("c", "c")
    ));

    @Test
    void getTotal() {
        assertEquals(6, statistics.getTotal());
    }

    @Test
    void getConfusionMatrix() {
        Map<String, Map<String, Integer>> confusionMatrix = statistics.getConfusionMatrix();
        assertEquals(3, confusionMatrix.size());
        assertEquals(1, confusionMatrix.get("a").get("a"));
        assertEquals(1, confusionMatrix.get("a").get("b"));
        assertEquals(1, confusionMatrix.get("b").get("b"));
        assertEquals(2, confusionMatrix.get("b").get("c"));
        assertEquals(1, confusionMatrix.get("c").get("c"));
    }
    @Test
    void calculateAccuracy() {
        assertEquals(0.5, statistics.calculateAccuracy());
    }

    @Test
    void calculateAccuracyForTotalZero() {
        assertEquals(0.0, new Statistics(List.of()).calculateAccuracy());
    }

    @Test
    void calculatePrecision() {
        Map<String, Double> precision = statistics.calculatePrecision();
        assertEquals(1.0, precision.get("a"));
        assertEquals(0.5, precision.get("b"));
        assertEquals(1.0 / 3.0, precision.get("c"));
        assertEquals(0.638, precision.get("all"), 0.001);
    }

    @Test
    void calculateRecall() {
        Map<String, Double> recall = statistics.calculateRecall();
        assertEquals(0.5, recall.get("a"));
        assertEquals(1.0 / 3.0, recall.get("b"));
        assertEquals(1.0, recall.get("c"));
        assertEquals(0.500, recall.get("all"), 0.001);
    }

    @Test
    void calculateF1Score() {
        Map<String, Double> f1Score = statistics.calculateF1Score();
        assertEquals(1.0 / 1.5, f1Score.get("a"));
        assertEquals(0.4, f1Score.get("b"));
        assertEquals(0.5, f1Score.get("c"));
        assertEquals(0.505, f1Score.get("all"), 0.001);
    }
}