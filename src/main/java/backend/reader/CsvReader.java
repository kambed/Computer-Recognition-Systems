package backend.reader;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class CsvReader {
    private CsvReader() {
    }

    public static Optional<Map<String, List<String>>> readDictionary(String path) {
        Map<String, List<String>> dictionary = new HashMap<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(path))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();
        ) {
            List<String[]> rows = reader.readAll();
            rows.remove(0);
            rows.forEach(row -> {
                if (!dictionary.containsKey(row[0])) {
                    dictionary.put(row[0], new ArrayList<>());
                }
                dictionary.get(row[0]).add(row[1]);
            });
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(dictionary.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                )));
    }
}
