package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedWordAtTheBeginningExtractor implements Extractor<String> {
    @Override
    public String extract(Article article) {
        String[] words = article.getText().getPreprocessedText().toLowerCase().split("\\s+");
        return Arrays.stream(words)
                .limit(words.length / 5)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }
}
