package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedWorkStartingInCapitalLetterExtractor implements Extractor<String> {
    @Override
    public String extract(Article article) {
        return Arrays.stream(article.getText().getPreprocessedText()
                        .split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> (entry.getKey().charAt(0)) >= 65 &&
                        (entry.getKey().charAt(0)) <= 90)
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
