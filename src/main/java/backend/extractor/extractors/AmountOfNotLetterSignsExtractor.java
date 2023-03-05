package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

public class AmountOfNotLetterSignsExtractor implements Extractor<Integer> {

    @Override
    public Integer extract(Article article) {
        return (int) article.getText()
                .getText()
                .chars()
                .boxed()
                .filter(c -> c < 65 || (c > 90 && c < 97) || c > 122)
                .count();
    }
}
