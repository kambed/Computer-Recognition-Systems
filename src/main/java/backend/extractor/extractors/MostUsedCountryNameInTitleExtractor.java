package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;
import backend.reader.CsvReader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MostUsedCountryNameInTitleExtractor implements Extractor<String> {
    private final Map<String, List<String>> countriesSynonyms = getCountriesSynonyms();

    @SuppressWarnings("DuplicatedCode")
    @Override
    public String extract(Article article) {
        String title = article.getText().getTitle().toLowerCase();

        return countriesSynonyms.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .stream()
                                .map(value -> Arrays.stream(title.split(value, -1)).count()- 1)
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
