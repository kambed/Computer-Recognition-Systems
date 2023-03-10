package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.helper.Helper;
import backend.model.Article;
import backend.reader.CsvReader;
import org.apache.commons.lang3.StringUtils;

import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedCityNameMappedToCountryExtractor implements Extractor<String> {
    private final Map<String, List<String>> citiesInCountries = getCitiesInCountries();
    @Override
    public String extract(Article article) {
        final StringBuilder noMatchersPossibleMultipleWords = new StringBuilder();
        return Arrays.stream(
                        Optional.ofNullable(
                                        article.getText().getPreprocessedText()
                                ).orElse("")
                                .split("\\s+")
                ).filter(word -> word.matches("\\p{Lu}\\p{Ll}*"))
                .map(word -> {
                    for (Map.Entry<String, List<String>> entry : citiesInCountries.entrySet()) {
                        for (String geo : entry.getValue()) {
                            boolean noMatchersPossible = StringUtils.countMatches(noMatchersPossibleMultipleWords.toString(), " ") > 0 && StringUtils.countMatches(geo, " ") > 0 &&
                                    StringUtils.countMatches(noMatchersPossibleMultipleWords + " " + word, geo) > 0;
                            if (noMatchersPossible) {
                                noMatchersPossibleMultipleWords.setLength(0);
                            }
                            if (geo.equals(word) || noMatchersPossible) {
                                noMatchersPossibleMultipleWords.append(" ").append(word);
                                return entry.getKey();
                            }
                        }
                    }
                    noMatchersPossibleMultipleWords.append(" ").append(word);
                    return "";
                }).filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
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
