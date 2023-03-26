package backend.knn.measure;

import java.util.stream.IntStream;

public class GeneralizedNgramMeasureWithLimitations implements Measure {

    private final int shortestGram;
    private final int longestGram;

    public GeneralizedNgramMeasureWithLimitations(int shortestGram, int longestGram) {
        this.shortestGram = shortestGram;
        this.longestGram = longestGram;
    }

    @Override
    public double calculateMeasure(String text1, String text2) {
        int N = Math.max(text1.length(), text2.length());
        String text1Preformatted = text1.toLowerCase().trim();
        String text2Preformatted = text2.toLowerCase().trim();
        int commonNgrams = Math.min(countCommonNgrams(text1Preformatted, text2Preformatted),
                countCommonNgrams(text2Preformatted, text1Preformatted));
        return commonNgrams * (2.0 /
                (((N - shortestGram + 1) * (N - shortestGram + 2)) - ((N - longestGram + 1) * (N - longestGram))));
    }

    private int countCommonNgrams(String text1, String text2) {
        return IntStream.range(shortestGram, longestGram + 1).map(n ->
                IntStream.range(0, text1.length() - n + 1).map(text1Index ->
                        text2.contains(text1.substring(text1Index, text1Index + n)) ? 1 : 0
                ).sum()
        ).sum();
    }
}
