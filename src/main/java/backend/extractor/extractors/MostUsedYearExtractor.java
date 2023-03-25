package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedYearExtractor extends Extractor<Integer> {
    public MostUsedYearExtractor() {
        domainMax = 2999.0;
        domainMin = 1000.0;
    }
    @Override
    public Integer extract(Article article) {
        return Integer.parseInt(Arrays.stream(Optional.ofNullable(article.getText()
                                .getPreprocessedText())
                        .orElse("")
                        .split("\\s+"))
                .filter(word -> word.matches("^[12]\\d{3}$"))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("0"));
    }
}
