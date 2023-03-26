package backend.knn.metric;

public class MetricFactory {
    private MetricFactory() {
    }

    public static Metric createExtractor(MetricType type) {
        return type.getMetric();
    }
}
