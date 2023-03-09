package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;
import backend.reader.CsvReader;

import java.util.*;
import java.util.stream.Collectors;

public class MostUsedCountryNameExtractor implements Extractor<String> {
    private final Map<String, List<String>> countriesSynonyms = getCountriesSynonyms();

    @Override
    public String extract(Article article) {
        String text = article.getText().getText().toLowerCase();
        String title = article.getText().getTitle().toLowerCase();

        Map<String, Long> currencyCounts = countriesSynonyms.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .stream()
                                .map(value -> Arrays.stream(title.split(value, -1)).count()
                                        + Arrays.stream(text.split(value, -1)).count() - 2
                                )
                                .reduce(0L, Long::sum)
                ));
        Optional<Map.Entry<String, Long>> maxEntry = currencyCounts.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        return maxEntry.filter(
                        entry -> currencyCounts.values()
                                .stream()
                                .filter(value -> value.equals(maxEntry.get().getValue()))
                                .count() == 1
                )
                .map(Map.Entry::getKey)
                .orElse(null);

    }

    private Map<String, List<String>> getCountriesSynonyms() {
        return CsvReader.readDictionary(
                Objects.requireNonNull(getClass().getResource("countries.csv")).getPath()
        ).orElseThrow();
    }
}
