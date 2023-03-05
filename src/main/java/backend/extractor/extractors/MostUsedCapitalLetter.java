package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedCapitalLetter implements Extractor<String> {
    @Override
    public String extract(Article article) {
        return article.getText()
                .getText()
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
