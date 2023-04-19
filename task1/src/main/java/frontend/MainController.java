package frontend;

import backend.KnnFacade;
import backend.extractor.ExtractorType;
import backend.knn.dto.StatisticsDto;
import backend.knn.measure.MeasureType;
import backend.knn.metric.MetricType;
import backend.reader.FileType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

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
    @FXML
    private GridPane resultGridPane;
    @FXML
    public GridPane confusionMatrixGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(ExtractorType.values());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        metricComboBox.getItems().addAll(MetricType.values());

        measureComboBox.getItems().addAll(MeasureType.values());
        measureComboBox.getSelectionModel().select(MeasureType.GENERALIZED_NGRAM_WITH_LIMITATIONS);

        resultGridPane.setOnKeyPressed(keyEvent -> {
            if (keyEvent.isControlDown() && keyEvent.getCode().toString().equals("C")) {
                copyToClipboard(resultGridPane);
            }
        });
        resultGridPane.setOnMouseEntered(mouseEvent -> resultGridPane.requestFocus());
        resultGridPane.setOnMouseExited(mouseEvent -> resultGridPane.getParent().requestFocus());

        confusionMatrixGrid.setOnKeyPressed(keyEvent -> {
            if (keyEvent.isControlDown() && keyEvent.getCode().toString().equals("C")) {
                copyToClipboard(confusionMatrixGrid);
            }
        });
        confusionMatrixGrid.setOnMouseEntered(mouseEvent -> confusionMatrixGrid.requestFocus());
        confusionMatrixGrid.setOnMouseExited(mouseEvent -> confusionMatrixGrid.getParent().requestFocus());
    }

    private void copyToClipboard(GridPane gridPane) {
        StringBuilder html = new StringBuilder(
                "<table border=\"1\" cellspacing=\"0\" cellpadding=\"5\" color=\"black\">"
        );
        int numCols = gridPane.getColumnCount();
        if (gridPane.getId().equals(confusionMatrixGrid.getId())) {
            html.append("<tr><td></td>");
        }
        gridPane.getChildren().forEach(node -> {
            if (GridPane.getColumnIndex(node) == 0) {
                html.append("<tr>");
            }
            html.append("<td>").append(((Label)node).getText()).append("</td>");
            if (GridPane.getColumnIndex(node) == numCols - 1) {
                html.append("</tr>");
            }
        });
        html.append("</table>");
        ClipboardContent content = new ClipboardContent();
        content.putHtml(html.toString());
        Clipboard.getSystemClipboard().setContent(content);
    }

    @FXML
    public void loadFiles(ActionEvent actionEvent) {
        pathToArticles = FileChoose.choose("Open articles", actionEvent);
        startProcessButton.setDisable(false);
    }

    @FXML
    public void process() {
        StatisticsDto results = knnFacade.process(
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
        resultGridPane.getChildren().clear();
        results.statistics().forEach((key, value) -> {
            resultGridPane.add(new Label(key), 0, resultGridPane.getRowCount());
            if (!Double.isNaN(value)) {
                value = Math.round(value * 1000) / 1000.0;
            }
            resultGridPane.add(
                    new Label(Double.toString(value)),
                    1,
                    resultGridPane.getRowCount() - 1
            );
        });
        confusionMatrixGrid.getChildren().clear();
        AtomicInteger labelColumn = new AtomicInteger(1);
        results.classes().forEach(country -> confusionMatrixGrid.add(
                new Label(country),
                labelColumn.getAndIncrement(),
                0
        ));
        AtomicInteger row = new AtomicInteger(1);
        AtomicInteger column = new AtomicInteger(1);
        results.classes().forEach(countryRow -> {
            confusionMatrixGrid.add(new Label(countryRow), 0, row.get());
            Map<String, Integer> confusionMatrixRow = results.confusionMatrix().getOrDefault(countryRow, Map.of());
            results.classes().forEach(countryColumn -> confusionMatrixGrid.add(
                    new Label(confusionMatrixRow.getOrDefault(countryColumn, 0).toString()),
                    column.getAndIncrement(),
                    row.get()
            ));
            row.getAndIncrement();
            column.set(1);
        });
    }
}