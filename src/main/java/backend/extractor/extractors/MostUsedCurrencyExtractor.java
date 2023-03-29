package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.helper.Helper;
import backend.model.Article;
import backend.reader.CsvReader;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class MostUsedCurrencyExtractor extends Extractor<String> {
    private final Map<String, List<String>> currenciesSynonyms = getCurrenciesSynonyms();

    @SuppressWarnings("DuplicatedCode")
    @Override
    public String extract(Article article) {
        String text = article.getText().getText().toLowerCase();

        return currenciesSynonyms.entrySet()
                .parallelStream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .parallelStream()
                                .map(value -> StringUtils.countMatches(text, value))
                                .reduce(0, Integer::sum)
                ))
                .entrySet()
                .parallelStream()
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
