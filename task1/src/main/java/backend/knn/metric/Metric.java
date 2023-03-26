package backend.knn.metric;

import java.util.List;

public interface Metric {
    double calculateDistance(List<Double> vector1, List<Double> vector2);
}
