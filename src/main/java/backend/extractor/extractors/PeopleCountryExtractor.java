package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.helper.Helper;
import backend.model.Article;
import backend.reader.CsvReader;

import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PeopleCountryExtractor implements Extractor<String> {
    private final Map<String, List<String>> peopleCountry = getPeopleCountries();

    @Override
    public String extract(Article article) {
        return Optional.ofNullable(article.getPeople()).orElse(List.of())
                .stream()
                .filter(person -> peopleCountry.values().stream().flatMap(Collection::stream).toList().contains(person))
                .map(person -> peopleCountry.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().contains(person))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("")
                ).filter(word -> !word.isEmpty())
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

    private Map<String, List<String>> getPeopleCountries() {
        try {
            return CsvReader.readDictionary(Helper.getFilePath(this, "people.csv")).orElseThrow();
        } catch (URISyntaxException e) {
            return Map.of();
        }
    }
}
