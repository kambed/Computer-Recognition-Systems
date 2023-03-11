package frontend;

import backend.KnnFacade;
import backend.extractor.ExtractorType;
import backend.reader.FileType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static final String RESOURCE = "main-view.fxml";
    private List<String> pathToArticles;
    private final KnnFacade knnFacade = new KnnFacade();
    @FXML
    private ListView<ExtractorType> listView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(ExtractorType.values());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    protected void loadFiles(ActionEvent actionEvent) {
        pathToArticles = FileChoose.choose("Open articles", actionEvent);
    }
    @FXML
    public void process() {
        knnFacade.process(listView.getSelectionModel().getSelectedItems(), FileType.SGM, pathToArticles);
    }
}