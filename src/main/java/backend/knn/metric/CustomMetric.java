package backend.knn.metric;

import java.util.List;

public class CustomMetric implements Metric {
    @Override
    public double calculateDistance(List<Double> vector1, List<Double> vector2) {
        return 0; //TODO: INVENT SOMETHING GOOD
    }
}