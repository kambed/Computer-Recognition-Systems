package backend.knn.measure;

public class MeasureFactory {

    private MeasureFactory() {
    }

    public static Measure createGeneralizedNgramWithLimitations(int shortestGram, int longestGram) {
        return new GeneralizedNgramMeasureWithLimitations(shortestGram, longestGram);
    }
}
