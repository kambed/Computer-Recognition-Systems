package frontend;

import backend.KnnFacade;
import backend.extractor.ExtractorType;
import backend.knn.measure.MeasureType;
import backend.knn.metric.MetricType;
import backend.reader.FileType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static final String RESOURCE = "main-view.fxml";
    private List<String> pathToArticles;
    private final KnnFacade knnFacade = new KnnFacade();
    @FXML
    private ListView<ExtractorType> listView;
    @FXML
    private Button startProcessButton;
    @FXML
    private ComboBox<MetricType> metricComboBox;
    @FXML
    private ComboBox<MeasureType> measureComboBox;
    @FXML
    private TextField shortestGramTextField;
    @FXML
    private TextField longestGramTextField;
    @FXML
    private TextField numberOfNeighborsTextField;
    @FXML
    private Slider teachPartSizeSlider;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(ExtractorType.values());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        metricComboBox.getItems().addAll(MetricType.values());

        measureComboBox.getItems().addAll(MeasureType.values());
        measureComboBox.getSelectionModel().select(MeasureType.GENERALIZED_NGRAM_WITH_LIMITATIONS);
    }

    @FXML
    public void loadFiles(ActionEvent actionEvent) {
        pathToArticles = FileChoose.choose("Open articles", actionEvent);
        startProcessButton.setDisable(false);
    }
    @FXML
    public void process() {
        knnFacade.process(
                listView.getSelectionModel().getSelectedItems(),
                FileType.SGM,
                pathToArticles,
                metricComboBox.getSelectionModel().getSelectedItem(),
                knnFacade.createGeneralizedNgramMeasureWithLimitations(
                        Integer.parseInt(shortestGramTextField.getText()),
                        Integer.parseInt(longestGramTextField.getText())
                ),
                Integer.parseInt(numberOfNeighborsTextField.getText()),
                teachPartSizeSlider.getValue() / 100.0

        );
    }
}