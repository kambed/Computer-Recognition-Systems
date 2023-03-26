package backend.process;

import backend.extractor.Extractor;
import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.knn.measure.Measure;
import backend.knn.metric.Metric;
import backend.knn.metric.MetricFactory;
import backend.knn.metric.MetricType;
import backend.model.Article;
import backend.reader.FileReader;
import backend.reader.FileType;
import backend.reader.ReaderFactory;
import backend.statistics.Statistics;
import backend.statistics.StatisticsFactory;
import javafx.util.Pair;

import java.util.*;

public class Process {
    private final List<Extractor<?>> extractors = new LinkedList<>();
    private final FileReader reader;
    private final Metric metric;
    private final Measure measure;
    private final int k;

    private final List<String> countriesOfInterest = List.of("west-germany", "usa", "france", "uk", "canada", "japan");
    public Process(
            List<ExtractorType> extractorTypes,
            FileType fileType,
            MetricType metricType,
            Measure measure,
            int k
    ) {
        extractorTypes.forEach(type -> extractors.add(ExtractorFactory.createExtractor(type)));
        reader = ReaderFactory.createReader(fileType);
        metric = MetricFactory.createMetric(metricType);
        this.measure = measure;
        this.k = k;
    }

    public Map<String, Double> process(List<String> paths, double teachPart) {
        List<Article> articles = paths.stream()
                .map(path -> reader.read(path).orElse(null))
                .filter(Objects::nonNull)
                .flatMap(list -> list.getArticles().stream())
                .filter(article -> article.getPlaces().size() == 1
                        && countriesOfInterest.contains(article.getPlaces().get(0)))
                .toList();
        List<Pair<String, String>> expectedToReceivedValues = new ArrayList<>();
        for (Article article : articles) {
            List<Object> extractedFeatures = new ArrayList<>();
            for (Extractor<?> extractor : extractors) {
                extractedFeatures.add(extractor.extractAndNormalize(article));
            }
            // TODO: KNN
            String valueReceivedFromKnn = "Value received from KNN";
            expectedToReceivedValues.add(new Pair<>(article.getPlaces().get(0), valueReceivedFromKnn));
        }

        Statistics statistics = StatisticsFactory.createStatistics(expectedToReceivedValues);
        Map<String, Double> statisticsMap = new LinkedHashMap<>();
        statisticsMap.put("accuracy", statistics.calculateAccuracy());
        statistics.calculatePrecision().forEach((key, value) -> statisticsMap.put("precision for " + key, value));
        statistics.calculateRecall().forEach((key, value) -> statisticsMap.put("recall for " + key, value));
        statistics.calculateF1Score().forEach((key, value) -> statisticsMap.put("f1Score for " + key, value));
        return statisticsMap;
    }
}
