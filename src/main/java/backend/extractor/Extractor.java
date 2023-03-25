package backend.extractor;

import backend.model.Article;

public interface Extractor<T> {
    T extract(Article article);
    default Double normalize(T value) {
        throw new UnsupportedOperationException("This extractor does not support normalization");
    }

    default Object extractAndNormalize(Article article) {
        T extractedValue = extract(article);
        try {
            Double normalizedValue = normalize(extractedValue);
            if (normalizedValue > 1.0) {
                return 1.0;
            } else if (normalizedValue < 0.0) {
                return 0.0;
            } else {
                return normalizedValue;
            }
        } catch (UnsupportedOperationException e) {
            return extractedValue;
        }
    }
}