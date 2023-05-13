package backend.knn;

import backend.knn.measure.Measure;
import backend.knn.metric.Metric;
import javafx.util.Pair;

import java.util.List;

public class KnnFactory {
    public static Knn createKnn(int k, Metric metric, Measure measure, List<Pair<String, List<Object>>> trainingData) {
        return new Knn(k, metric, measure, trainingData);
    }
}
