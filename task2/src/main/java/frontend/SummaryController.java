package frontend;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {
    @FXML
    private TableView<Summary> table;
    @FXML
    private TableColumn<Summary, Boolean> selectColumn;
    @FXML
    private TableColumn<Summary, String> summaryColumn;
    @FXML
    private TableColumn<Summary, Double> t1Column;
    @FXML
    private TableColumn<Summary, Double> t2Column;
    @FXML
    private TableColumn<Summary, Double> t3Column;

    private ObservableList<Summary> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        prepareColumns();

        data = FXCollections.observableArrayList();
        data.addAll(
                new Summary("Name 1", 1.0, 1.0, 1.0),
                new Summary("Name 2", 1.0, 2.0, 1.0),
                new Summary("Name 3", 3.0, 1.0, 1.0),
                new Summary("Name 4", 2.0, 2.0, 1.0),
                new Summary("Name 5", 1.5, 4.0, 1.0)
        );
        table.setItems(data);
    }

    private void prepareColumns() {
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));
        selectColumn.setCellValueFactory(param -> {
            Summary summary = param.getValue();
            SimpleBooleanProperty selectedProperty = new SimpleBooleanProperty(summary.isSelected());
            selectedProperty.addListener((obs, oldValue, newValue) -> summary.setSelected(newValue));
            return selectedProperty;
        });
        CheckBox selectAllCheckBox = new CheckBox();
        selectAllCheckBox.setOnAction(event -> {
            for (Summary summary : data) {
                summary.setSelected(selectAllCheckBox.isSelected());
            }
            table.refresh();
        });
        selectColumn.setGraphic(selectAllCheckBox);

        summaryColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSummary()));
        t1Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT1()));
        t2Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT2()));
        t3Column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getT3()));
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

    private List<Summary> getSelectedSummaries() {
        return data.filtered(Summary::isSelected);
    }
}

@Getter
@Setter
@AllArgsConstructor
class Summary {
    private static final String CELL_PATTERN = "<td>%s</td>";
    private String summary;
    private Double t1;
    private Double t2;
    private Double t3;
    private boolean selected;

    public Summary(String summary, Double t1, Double t2, Double t3) {
        this.summary = summary;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.selected = false;
    }

    @Override
    public String toString() {
        return "Summary: \n" + summary + "\n" +
                "T1: " + t1 + "\n" +
                "T2: " + t2 + "\n" +
                "T3: " + t3 + "\n" +
                "\n";
    }

    public String toHtmlTableRow() {
        return "<tr>" +
                CELL_PATTERN.formatted(summary) +
                CELL_PATTERN.formatted(t1) +
                CELL_PATTERN.formatted(t2) +
                CELL_PATTERN.formatted(t3) +
                "</tr>";
    }
}