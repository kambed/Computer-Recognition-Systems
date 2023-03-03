package backend.extractor.extractors;

import backend.extractor.Extractor;

import java.util.Map;

public class MostUsedWorldExtractor implements Extractor<String> {
    @Override
    public String extract(Map<String, String> value) {
        return "temp";
    }
}
