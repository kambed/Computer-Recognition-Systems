package frontend;

import backend.model.Article;
import backend.reader.FileReader;
import backend.reader.SgmReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.List;
import java.util.Objects;

public class MainController {
    public static final String RESOURCE = "main-view.fxml";

    @FXML
    protected void loadFiles(ActionEvent actionEvent) {
        FileReader reader = new SgmReader();
        List<String> countriesOfInterest = List.of("west-germany", "usa", "france", "uk", "canada", "japan");
        List<Article> articles = FileChoose.choose("Open articles", actionEvent)
                .stream()
                .map(path -> reader.read(path).orElse(null))
                .filter(Objects::nonNull)
                .flatMap(list -> list.getArticles().stream())
                .filter(article -> article.getPlaces().size() == 1
                        && countriesOfInterest.contains(article.getPlaces().get(0)))
                .toList();
    }
}