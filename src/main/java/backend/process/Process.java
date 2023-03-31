package backend.process;

import backend.extractor.Extractor;
import backend.extractor.ExtractorFactory;
import backend.extractor.ExtractorType;
import backend.knn.Knn;
import backend.knn.KnnFactory;
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
        List<Article> articles = paths.parallelStream()
                .map(path -> reader.read(path).orElse(null))
                .filter(Objects::nonNull)
                .flatMap(list -> list.getArticles().parallelStream())
                .filter(article -> article.getPlaces().size() == 1
                        && countriesOfInterest.contains(article.getPlaces().get(0)))
                .toList();
        List<Pair<String,List<Object>>> expectedValueWithVector = articles.parallelStream()
                .map(article -> new Pair<>(
                        article.getPlaces().get(0),
                        extractors.parallelStream()
                                .map(extractor -> extractor.extractAndNormalize(article))
                                .toList())
                )
                .toList();

        List<Pair<String, List<Object>>> trainData = expectedValueWithVector.parallelStream()
                .limit((int) (expectedValueWithVector.size() * teachPart))
                .toList();
        List<Pair<String, List<Object>>> testData = expectedValueWithVector.parallelStream()
                .skip((int) (expectedValueWithVector.size() * teachPart))
                .toList();

        Knn knn = KnnFactory.createKnn(k, metric, measure, trainData);

        List<Pair<String, String>> expectedToReceivedValues = testData.parallelStream()
                .map(pair -> new Pair<>(pair.getKey(), knn.calculateKnn(pair.getValue())))
                .toList();

        Statistics statistics = StatisticsFactory.createStatistics(expectedToReceivedValues);
        Map<String, Double> statisticsMap = new LinkedHashMap<>();
        statisticsMap.put("accuracy", statistics.calculateAccuracy());
        statistics.calculatePrecision().forEach((key, value) -> statisticsMap.put("Precision for " + key, value));
        statistics.calculateRecall().forEach((key, value) -> statisticsMap.put("Recall for " + key, value));
        statistics.calculateF1Score().forEach((key, value) -> statisticsMap.put("F1 score for " + key, value));
        return statisticsMap;
    }
}
