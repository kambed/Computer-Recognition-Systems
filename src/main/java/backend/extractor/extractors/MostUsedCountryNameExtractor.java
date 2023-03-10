package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;
import backend.reader.CsvReader;

import java.util.*;
import java.util.stream.Collectors;

public class MostUsedCountryNameExtractor implements Extractor<String> {
    private final Map<String, List<String>> countriesSynonyms = getCountriesSynonyms();

    @SuppressWarnings("DuplicatedCode")
    @Override
    public String extract(Article article) {
        String text = article.getText().getText().toLowerCase();

        return countriesSynonyms.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .stream()
                                .map(value -> Arrays.stream(text.split(value, -1)).count()- 1)
                                .reduce(0L, Long::sum)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .filter(entry -> entry.getValue() > 0)
                .map(Map.Entry::getKey)
                .orElse("");

    }

    private Map<String, List<String>> getCountriesSynonyms() {
        return CsvReader.readDictionary(
                Objects.requireNonNull(getClass().getResource("countries.csv")).getPath()
        ).orElseThrow();
    }
}
