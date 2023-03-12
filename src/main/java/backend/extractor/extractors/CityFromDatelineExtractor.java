package backend.extractor.extractors;

import backend.extractor.Extractor;
import backend.model.Article;

import java.util.Optional;

public class CityFromDatelineExtractor implements Extractor<String> {
    @Override
    public String extract(Article article) {
        return Optional.ofNullable(
                article.getText().getDateline()
        ).map(dateline -> dateline.substring(0,
                                dateline.contains(",") ? dateline.indexOf(",") : dateline.length()
                        )
                        .trim()
                        .toLowerCase()
        ).orElse("");
    }
}
