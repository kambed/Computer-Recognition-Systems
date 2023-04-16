package backend.extractor;

import backend.model.Article;

public abstract class Extractor<T> {
    protected double domainMin = 0;
    protected double domainMax = 0;
    public abstract T extract(Article article);

    @SuppressWarnings("ManualMinMaxCalculation")
    public Object extractAndNormalize(Article article) {
        T extractedValue = extract(article);
        if (!(extractedValue instanceof Number)) {
            return extractedValue;
        }
        double normalizedValue = (((Number) extractedValue).doubleValue() - domainMin) / (domainMax - domainMin);
        if (normalizedValue > 1.0) {
            return 1.0;
        } else if (normalizedValue < 0.0) {
            return 0.0;
        } else {
            return normalizedValue;
        }
    }
}