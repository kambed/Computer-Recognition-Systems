package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MostUsedWordStartingInCapitalLetterExtractor implements Extractor<String> {
    @Override
    public String extract(Article article) {
        List<String> articleWords = List.of(article.getText().getText().trim().split("\\s+"));
        return IntStream.range(2, articleWords.size())
                .filter(index -> !Pattern.matches(
                        "[.?!]",
                        String.valueOf(
                                articleWords.get(index - 1)
                                        .charAt(articleWords.get(index - 1).length() - 1)
                        ))
                ).boxed()
                .map(index -> articleWords.get(index)
                        .replaceAll("\\p{P}+(?=\\s|$)", ""))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().length() > 1)
                .filter(entry -> (entry.getKey().charAt(0)) >= 65 &&
                        (entry.getKey().charAt(0)) <= 90)
                .max(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey().toLowerCase())
                .orElse("");
    }
}
