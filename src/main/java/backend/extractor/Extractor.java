package backend.extractor;

import backend.model.Article;

public interface Extractor<T> {
    T extract(Article article);
}
