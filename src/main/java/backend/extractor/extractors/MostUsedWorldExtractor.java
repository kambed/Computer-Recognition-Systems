package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

public class MostUsedWorldExtractor implements Extractor<String> {
    @Override
    public String extract(Article article) {
        return "temp";
    }
}
