package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.helper.Helper;
import backend.model.Article;
import backend.reader.CsvReader;

import java.util.*;
import java.util.stream.Collectors;

public class MostUsedCurrencyExtractor implements Extractor<String> {
    private final Map<String, List<String>> currenciesSynonyms = getCurrenciesSynonyms();

    @SuppressWarnings("DuplicatedCode")
    @Override
    public String extract(Article article) {
        String text = article.getText().getText().toLowerCase();

        return currenciesSynonyms.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .stream()
                                .map(value -> Arrays.stream(text.split(value, -1)).count() - 1)
                                .reduce(0L, Long::sum)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .filter(entry -> entry.getValue() > 0)
                .map(Map.Entry::getKey)
                .orElse("");
    }

    private Map<String, List<String>> getCurrenciesSynonyms() {
        try {
            return CsvReader.readDictionary(Helper.getFilePath(this, "currencies.csv")).orElseThrow();
        } catch (Exception e) {
            return Map.of();
        }
    }
}
