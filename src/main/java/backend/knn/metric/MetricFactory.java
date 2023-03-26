package backend.knn.metric;

public class MetricFactory {
    private MetricFactory() {
    }

    public static Metric createMetric(MetricType type) {
        return type.getMetric();
    }
}
