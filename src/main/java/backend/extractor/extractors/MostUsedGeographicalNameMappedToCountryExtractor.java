package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;
import backend.reader.CsvReader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedGeographicalNameMappedToCountryExtractor implements Extractor<String> {
    @Override
    public String extract(Article article) {
        return Arrays.stream(
                        Optional.ofNullable(
                                        article.getText().getPreprocessedText()
                                ).orElse("")
                                .split("\\s+"))
                .map(word -> {
                    for (Map.Entry<String, List<String>> entry : citiesInCountries().entrySet()) {
                        if (entry.getValue().contains(word)) {
                            return entry.getKey();
                        }
                    }
                    return "";
                }).filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey().toLowerCase())
                .orElse("");
    }

    private Map<String, List<String>> citiesInCountries() {
        return CsvReader.readDictionary("src/main/resources/backend/geographicalNames.csv").orElseThrow();
    }
}
