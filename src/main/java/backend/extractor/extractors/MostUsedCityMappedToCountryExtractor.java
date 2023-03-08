package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;
import backend.reader.CsvReader;

import java.util.List;
import java.util.Map;

public class MostUsedCityMappedToCountryExtractor implements Extractor<String> {
    @Override
    public String extract(Article article) {
        return null;
    }

    private Map<String, List<String>> citiesInCountries() {
        return CsvReader.readDictionary("src/main/resources/backend/cities.csv").orElseThrow();
    }
}
