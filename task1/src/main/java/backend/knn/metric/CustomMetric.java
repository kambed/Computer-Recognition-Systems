package backend.knn.metric;

import java.util.List;
import java.util.stream.IntStream;

public class CustomMetric implements Metric {
    @Override
    public double calculateDistance(List<Double> vector1, List<Double> vector2) {
        if (vector1.size() != vector2.size()) {
            return Double.MAX_VALUE;
        }
        return calculateMeasure(vector1, vector2);
    }

    private double calculateMeasure(List<Double> vector1, List<Double> vector2) {
        return IntStream.range(0, vector1.size())
                .mapToDouble(i -> Math.abs(((vector1.get(i) - vector2.get(i)) *
                        Math.max((3 - calculateGaussian(vector1.get(i))), (3 - calculateGaussian(vector2.get(i)))))))
                .sum();
    }

    private double calculateGaussian(double x) {
        return (1 / (0.2 * Math.sqrt(2 * Math.PI))) * Math.exp((-((x - 0.5) * (x - 0.5))) / (2 * 0.2 * 0.2));
    }
}
