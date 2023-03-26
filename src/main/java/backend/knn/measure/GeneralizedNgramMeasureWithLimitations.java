package backend.knn.measure;

import java.util.stream.IntStream;

public class GeneralizedNgramMeasureWithLimitations implements Measure {

    int shortestNgram;
    int longestNgram;

    public GeneralizedNgramMeasureWithLimitations(int shortestNgram, int longestNgram) {
        this.shortestNgram = shortestNgram;
        this.longestNgram = longestNgram;
    }

    @Override
    public double calculateMeasure(String text1, String text2) {
        int N = Math.max(text1.length(), text2.length());
        String text1LowerCase = text1.toLowerCase();
        String text2LowerCase = text2.toLowerCase();
        int commonNgrams = Math.min(countCommonNgrams(text1LowerCase, text2LowerCase),
                countCommonNgrams(text2LowerCase, text1LowerCase));
        return commonNgrams * (2.0 /
                (((N - shortestNgram + 1) * (N - shortestNgram + 2)) - ((N - longestNgram + 1) * (N - longestNgram))));
    }

    private int countCommonNgrams(String text1, String text2) {
        return IntStream.range(shortestNgram, longestNgram + 1).map(n ->
                IntStream.range(0, text1.length() - n + 1).map(text1Index ->
                        text2.contains(text1.substring(text1Index, text1Index + n)) ? 1 : 0
                ).sum()
        ).sum();
    }
}
