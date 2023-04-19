package backend.process;

import backend.extractor.ExtractorType;
import backend.knn.measure.Measure;
import backend.knn.metric.MetricType;
import backend.reader.FileType;

import java.util.List;

public class ProcessFactory {
    private ProcessFactory() {
    }
    public static Process createProcess(
            List<ExtractorType> extractorTypes,
            FileType fileType,
            MetricType metricType,
            Measure measure,
            int k
    ) {
        return new Process(extractorTypes, fileType, metricType, measure, k);
    }
}
