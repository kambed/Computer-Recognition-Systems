package backend.extractor.extractors;

import backend.extractor.Extractor;

import java.util.Map;

public class ArticleLengthExtractor implements Extractor<Double> {
    @Override
    public Double extract(Map<String, String> value) {
        return 1.0;
    }
}
