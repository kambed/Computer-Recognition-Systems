package backend.knn.measure;

public interface Measure {
    double calculateMetric(String text1, String text2);
    double calculateMeasure(String text1, String text2);
}
