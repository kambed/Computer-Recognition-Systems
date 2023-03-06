package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedYearExtractor implements Extractor<Integer> {
    @Override
    public Integer extract(Article article) {
        return Integer.parseInt(Arrays.stream(Optional.ofNullable(article.getText()
                                .getPreprocessedText())
                        .orElse("")
                        .split("\\s+"))
                .filter(word -> word.matches("\\d{4}$"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("0"));
    }
}
