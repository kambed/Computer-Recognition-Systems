package backend.statistics;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private final int total;
    private final Map<String, Map<String, Integer>> confusionMatrix = new HashMap<>();
    public Statistics(List<Pair<String, String>> expectedToReceivedValues) {
        total = expectedToReceivedValues.size();
        expectedToReceivedValues.forEach(pair -> {
            confusionMatrix.putIfAbsent(pair.getKey(), new HashMap<>());
            confusionMatrix.get(pair.getKey())
                    .putIfAbsent(pair.getValue(), 0);
            confusionMatrix.get(pair.getKey())
                    .put(pair.getValue(), confusionMatrix.get(pair.getKey()).get(pair.getValue()) + 1);
        });
    }

    public double calculateAccuracy() {
        if (total == 0) {
            return 0;
        }
        int correct = confusionMatrix.entrySet()
                .stream()
                .map(entry -> entry.getValue().getOrDefault(entry.getKey(), 0))
                .reduce(0, Integer::sum);
        return (double) correct / total;
    }

    public Map<String, Double> calculatePrecision() {
        Map<String, Double> precision = new HashMap<>();
        confusionMatrix.forEach((expectedValue, receivedValues) -> {
            int classCorrect = receivedValues.getOrDefault(expectedValue, 0);
            int classTotal = receivedValues.values()
                    .stream()
                    .reduce(0, Integer::sum);
            precision.put(expectedValue, (double) classCorrect / classTotal);
        });
        return precision;
    }

    public Map<String, Double> calculateRecall() {
        Map<String, Double> recall = new HashMap<>();
        confusionMatrix.forEach((expectedValue, receivedValues) -> {
            int classCorrect = receivedValues.getOrDefault(expectedValue, 0);
            int classTotal = confusionMatrix.values()
                    .stream()
                    .mapToInt(map -> map.getOrDefault(expectedValue, 0))
                    .sum();
            recall.put(expectedValue, (double) classCorrect / classTotal);
        });
        return recall;
    }

    public Map<String, Double> calculateF1Score() {
        Map<String, Double> f1Score = new HashMap<>();
        Map<String, Double> precision = calculatePrecision();
        Map<String, Double> recall = calculateRecall();
        precision.forEach((key, value) -> {
            double recallValue = recall.get(key);
            f1Score.put(key, 2 * value * recallValue / (value + recallValue));
        });
        return f1Score;
    }

    public int getTotal() {
        return total;
    }

    public Map<String, Map<String, Integer>> getConfusionMatrix() {
        return confusionMatrix;
    }
}
