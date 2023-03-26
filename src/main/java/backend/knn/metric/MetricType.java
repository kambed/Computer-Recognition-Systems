package backend.knn.metric;

public enum MetricType {
    EUCLIDEAN(new EuclideanMetric()),
    MANHATTAN(new ManhattanMetric()),
    CHEBYSHEV(new ChebyshevMetric()),
    CUSTOM(new CustomMetric());

    private final Metric metric;

    MetricType(Metric metric) {
        this.metric = metric;
    }

    public Metric getMetric() {
        return metric;
    }
}
