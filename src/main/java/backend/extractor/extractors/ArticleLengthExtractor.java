package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

public class ArticleLengthExtractor implements Extractor<Double> {
    @Override
    public Double extract(Article article) {
        return 1.0;
    }
}
