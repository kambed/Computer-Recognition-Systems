package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedCapitalLetterExtractor implements Extractor<String> {
    @Override
    public String extract(Article article) {
        return Optional.ofNullable(article.getText()
                .getText())
                .orElse("")
                .chars()
                .filter(c -> c >= 65 && c <= 90)
                .boxed()
                .collect(
                        Collectors.groupingBy(Function.identity(),
                                Collectors.counting())
                ).entrySet()
                .stream()
                .max(
                        Map.Entry.comparingByValue()
                ).map(
                        v -> String.valueOf((char) v.getKey().intValue())
                )
                .orElse(null);
    }
}
