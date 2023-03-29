package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.helper.Helper;
import backend.model.Article;
import backend.reader.CsvReader;
import org.apache.commons.lang3.StringUtils;

import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class MostUsedCityNameMappedToCountryExtractor extends Extractor<String> {
    private final Map<String, List<String>> citiesInCountries = getCitiesInCountries();

    @SuppressWarnings("DuplicatedCode")
    @Override
    public String extract(Article article) {
        String text = Arrays.stream(
                        Optional.ofNullable(
                                        article.getText().getText()
                                ).orElse("")
                                .split("\\s+")
                ).filter(word -> word.matches("\\p{Lu}\\p{Ll}*\\p{P}?"))
                .collect(Collectors.joining(" "));

        return citiesInCountries.entrySet()
                .parallelStream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .parallelStream()
                                .filter(value -> StringUtils.countMatches(text, value) > 0)
                                .distinct()
                                .count()
                ))
                .entrySet()
                .parallelStream()
                .max(Map.Entry.comparingByValue())
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getKey().toLowerCase())
                .orElse("");
    }

    private Map<String, List<String>> getCitiesInCountries() {
        try {
            return CsvReader.readDictionary(Helper.getFilePath(this, "geographicalNames.csv"))
                    .orElseThrow();
        } catch (URISyntaxException e) {
            return Map.of();
        }
    }
}
