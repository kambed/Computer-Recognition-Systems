package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;
import backend.reader.CsvReader;

import java.util.*;
import java.util.stream.Collectors;

public class MostUsedCurrencyExtractor implements Extractor<String> {
    private final Map<String, List<String>> currenciesSynonyms = getCurrenciesSynonyms();

    @Override
    public String extract(Article article) {
        String text = article.getText().getText().toLowerCase();

        Map<String, Long> currencyCounts = currenciesSynonyms.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .stream()
                                .map(value -> Arrays.stream(text.split(value, -1)).count() - 1)
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

    private Map<String, List<String>> getCurrenciesSynonyms() {
        return CsvReader.readDictionary(
                Objects.requireNonNull(getClass().getResource("currencies.csv")).getPath()
        ).orElseThrow();
    }
}
