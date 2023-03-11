package frontend;

import backend.KnnFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.List;

public class MainController {
    public static final String RESOURCE = "main-view.fxml";
    private List<String> pathToArticles;
    private final KnnFacade knnFacade = new KnnFacade();

    @FXML
    protected void loadFiles(ActionEvent actionEvent) {
        pathToArticles = FileChoose.choose("Open articles", actionEvent);
    }
}