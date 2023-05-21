package frontend;

import frontend.model.SummaryDto;
import frontend.utils.AlertBox;
import frontend.utils.FileChoose;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {
    public static final String RESOURCE = "summary.fxml";
    @FXML
    private TableView<SummaryDto> table;
    @FXML
    private TableColumn<SummaryDto, Boolean> selectColumn;
    @FXML
    private TableColumn<SummaryDto, String> summaryColumn;
    @FXML
    private TableColumn<SummaryDto, Double> tavgColumn;
    @FXML
    private TableColumn<SummaryDto, Double> t1Column;
    @FXML
    private TableColumn<SummaryDto, Double> t2Column;
    @FXML
    private TableColumn<SummaryDto, Double> t3Column;
    @FXML
    private TableColumn<SummaryDto, Double> t4Column;
    @FXML
    private TableColumn<SummaryDto, Double> t5Column;
    @FXML
    private TableColumn<SummaryDto, Double> t6Column;
    @FXML
    private TableColumn<SummaryDto, Double> t7Column;
    @FXML
    private TableColumn<SummaryDto, Double> t8Column;
    @FXML
    private TableColumn<SummaryDto, Double> t9Column;
    @FXML
    private TableColumn<SummaryDto, Double> t10Column;
    @FXML
    private TableColumn<SummaryDto, Double> t11Column;

    private ObservableList<SummaryDto> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        prepareColumns();
    }

    public void setData(List<SummaryDto> data) {
        this.data = FXCollections.observableArrayList(data);
        table.setItems(this.data);
        table.refresh();
    }

    private void prepareColumns() {
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));
        selectColumn.setCellValueFactory(param -> {
            SummaryDto summary = param.getValue();
            SimpleBooleanProperty selectedProperty = new SimpleBooleanProperty(summary.isSelected());
            selectedProperty.addListener((obs, oldValue, newValue) -> summary.setSelected(newValue));
            return selectedProperty;
        });
        CheckBox selectAllCheckBox = new CheckBox();
        selectAllCheckBox.setOnAction(event -> {
            for (SummaryDto summary : data) {
                summary.setSelected(selectAllCheckBox.isSelected());
            }
            table.refresh();
        });
        selectColumn.setGraphic(selectAllCheckBox);

        summaryColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSummaryString()));
        tavgColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getFinalDegreeOfTruth()));
        t1Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT1()));
        t2Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT2()));
        t3Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT3()));
        t4Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT4()));
        t5Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT5()));
        t6Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT6()));
        t7Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT7()));
        t8Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT8()));
        t9Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT9()));
        t10Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT10()));
        t11Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT11()));
    }

    public void copyToClipboard() {
        StringBuilder html = new StringBuilder(
                "<table border=\"1\" cellspacing=\"0\" cellpadding=\"5\" color=\"black\">"
        );
        table.getColumns()
                .stream()
                .skip(1)
                .forEach(column -> html.append("<th>").append(column.getText()).append("</th>"));
        getSelectedSummaries().forEach(summary -> html.append(summary.toHtmlTableRow()));
        html.append("</table>");
        ClipboardContent content = new ClipboardContent();
        content.putHtml(html.toString());
        Clipboard.getSystemClipboard().setContent(content);
    }

    public void saveToFile(ActionEvent actionEvent) {
        String path = FileChoose.choose("Save to file", actionEvent);
        if (path.isEmpty()) {
            return;
        }

        StringBuilder text = new StringBuilder();
        getSelectedSummaries().forEach(summary -> text.append(summary.toString()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(text.toString());
            writer.flush();
        } catch (IOException e) {
            AlertBox.show("Error", "Error while saving to file: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private List<SummaryDto> getSelectedSummaries() {
        return data.filtered(SummaryDto::isSelected);
    }
}