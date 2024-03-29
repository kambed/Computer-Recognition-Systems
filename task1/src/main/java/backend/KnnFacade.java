package backend;

import backend.extractor.ExtractorType;
import backend.knn.dto.StatisticsDto;
import backend.knn.measure.Measure;
import backend.knn.measure.MeasureFactory;
import backend.knn.metric.MetricType;
import backend.process.ProcessFactory;
import backend.reader.FileType;

import java.util.List;

public class KnnFacade {
    public StatisticsDto process(
            List<ExtractorType> types,
            FileType fileType,
            List<String> paths,
            MetricType metricType,
            Measure measure,
            int k,
            double teachPart
    ) {
        return ProcessFactory.createProcess(types, fileType, metricType, measure, k).process(paths, teachPart);
    }

    public Measure createGeneralizedNgramMeasureWithLimitations(int shortestGram, int longestGram) {
        return MeasureFactory.createGeneralizedNgramWithLimitations(shortestGram, longestGram);
    }
}
