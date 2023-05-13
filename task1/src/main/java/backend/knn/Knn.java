package backend.knn;

import backend.knn.measure.Measure;
import backend.knn.metric.Metric;
import javafx.util.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Knn {
    private final int k;
    private final Metric metric;
    private final Measure measure;
    private final List<Pair<String, List<Object>>> trainingData;

    public Knn(int k, Metric metric, Measure measure, List<Pair<String, List<Object>>> trainingData) {
        this.k = k;
        this.metric = metric;
        this.measure = measure;
        this.trainingData = trainingData;
    }

    public String calculateKnn(List<Object> vector) {
        List<Pair<String, Double>> distances = trainingData.stream()
                .map(pair -> {
                    List<Object> values = pair.getValue();
                    List<Double> trainingVector = IntStream.range(0, values.size())
                            .mapToDouble(i -> {
                                if (values.get(i) instanceof Number numberValue) {
                                    return numberValue.doubleValue();
                                } else if(values.get(i) instanceof String stringFeature) {
                                    return 0.5 - (measure.calculateMetric(stringFeature, (String) vector.get(i)) / 2);
                                }
                                return 0.0;
                            }).boxed().toList();
                    List<Double> vectorToCalculate = IntStream.range(0, vector.size())
                            .mapToDouble(i -> {
                                if (vector.get(i) instanceof String stringFeature) {
                                    return 0.5 + (measure.calculateMetric(stringFeature, (String) values.get(i)) / 2);
                                } else if (vector.get(i) instanceof Double numberFeature) {
                                    return numberFeature;
                                }
                                return 0.0;
                            }).boxed().toList();
                    return new Pair<>(pair.getKey(), metric.calculateDistance(trainingVector, vectorToCalculate));
                })
                .toList();
        return distances
                .stream()
                .sorted(Comparator.comparing(Pair::getValue))
                .limit(k)
                .collect(Collectors.groupingBy(Pair::getKey, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow()
                .getKey();
    }
}
