package backend.knn.metric;

import java.util.List;
import java.util.stream.IntStream;

public class CustomMetric implements Metric {
    @Override
    public double calculateDistance(List<Double> vector1, List<Double> vector2) {
        if (vector1.size() != vector2.size()) {
            return Double.MAX_VALUE;
        }
        return Math.sqrt(IntStream.range(0, vector1.size())
                .mapToDouble(i -> (vector1.get(i) - vector2.get(i) + Math.max(((1 - Math.sin(vector1.get(i) * Math.PI)) / 10), ((1 - Math.sin(vector2.get(i) * Math.PI)) / 10))) *
                        (vector1.get(i) - vector2.get(i) + Math.max(((1 - Math.sin(vector1.get(i) * Math.PI)) / 10), ((1 - Math.sin(vector2.get(i) * Math.PI)) / 10))))
                .sum());
    }
}
