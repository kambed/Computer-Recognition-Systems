package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.helper.Helper;
import backend.model.Article;
import backend.reader.CsvReader;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostUsedWordAtTheBeginningExtractor extends Extractor<String> {

    private final List<String> stopWords = getStopWords().get("words");
    @Override
    public String extract(Article article) {
        String[] words = article.getText().getPreprocessedText().toLowerCase().split("\\s+");
        return Arrays.stream(words)
                .limit(words.length / 5)
                .filter(w -> !stopWords.contains(w))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    private Map<String, List<String>> getStopWords() {
        try {
            return CsvReader.readDictionary(Helper.getFilePath(this, "stopWordsEnglish.csv"))
                    .orElseThrow();
        } catch (Exception e) {
            return Map.of();
        }
    }
}
