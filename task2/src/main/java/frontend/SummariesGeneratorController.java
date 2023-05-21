package frontend;

import backend.LinguisticSummarizationsExecutor;
import backend.lingustic.Variable;
import backend.lingustic.quantifier.AbstractQuantifier;
import frontend.model.SummaryDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SummariesGeneratorController implements Initializable {
    @FXML
    private TreeView<String> variablesLabelsTree;
    @FXML
    private ListView<AbstractQuantifier> quantifiersList;
    @FXML
    private VBox tMeasuresContainer;
    private final List<Spinner<Double>> tMeasures = new ArrayList<>();
    private Consumer<List<SummaryDto>> createSummaryTab;
    private List<Variable> variables;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IntStream.rangeClosed(1, 11)
                .forEach(i -> {
                    HBox hBox = new HBox();
                    hBox.setSpacing(10);
                    Label label = new Label("T" + i + ": " + (i < 10 ? "  " : ""));
                    Spinner<Double> spinner = new Spinner<>(0.0, 1.0, 0.0, 0.01);
                    if (i == 1) {
                        spinner.getValueFactory().setValue(0.3);
                    } else {
                        spinner.getValueFactory().setValue(0.07);
                    }
                    spinner.setEditable(true);
                    spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> validateSpinnerValues());
                    hBox.getChildren().addAll(label, spinner);
                    tMeasures.add(spinner);
                    tMeasuresContainer.getChildren().addAll(hBox);
                });
    }

    public void generate() {
        List<CheckBoxTreeItem<String>> selected = getSelectedLastChildren((CheckBoxTreeItem<String>) variablesLabelsTree.getRoot());
        List<SummaryDto> summaries = LinguisticSummarizationsExecutor.getSummaries(
                quantifiersList.getSelectionModel().getSelectedItems().stream().toList(),
                selected.stream()
                        .map(s -> variables.stream()
                                .filter(v -> v.getName().equals(s.getParent().getValue()))
                                .findFirst()
                                .orElseThrow()
                                .getLabeledFuzzySets()
                                .stream()
                                .filter(lfs -> lfs.getLabel().equals(s.getValue()))
                                .findFirst()
                                .orElseThrow()).toList(),
                selected.stream().map(i -> i.getParent().getValue()).toList(),
                tMeasures.stream().map(Spinner::getValue).toList()
        ).stream().map(SummaryDto::new).toList();
        createSummaryTab.accept(summaries);
    }

    public void setCreateSummaryTab(Consumer<List<SummaryDto>> createSummaryTab) {
        this.createSummaryTab = createSummaryTab;
    }

    public void setVariables(List<Variable> variables) {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Linguistic variables");
        root.setExpanded(true);
        variablesLabelsTree.setRoot(root);
        variablesLabelsTree.setShowRoot(false);
        variablesLabelsTree.setCellFactory(CheckBoxTreeCell.forTreeView());
        this.variables = variables;
        variables.forEach(variable -> {
            CheckBoxTreeItem<String> item = new CheckBoxTreeItem<>(variable.getName());
            item.setExpanded(true);
            variable.getLabeledFuzzySets().forEach(labeledFuzzySet -> {
                CheckBoxTreeItem<String> item2 = new CheckBoxTreeItem<>(labeledFuzzySet.getLabel());
                item2.setExpanded(true);
                item.getChildren().add(item2);
            });
            root.getChildren().add(item);
        });
    }

    public void setQuantifiers(List<AbstractQuantifier> quantifiers) {
        quantifiersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        quantifiersList.getItems().setAll(quantifiers);
    }

    private List<CheckBoxTreeItem<String>> getSelectedLastChildren(CheckBoxTreeItem<String> item) {
        List<CheckBoxTreeItem<String>> selected = new ArrayList<>();
        item.getChildren().forEach(child -> {
            if (!(child instanceof CheckBoxTreeItem<String> stringChild)) {
                return;
            }
            if (stringChild.getChildren().isEmpty() && stringChild.isSelected()) {
                selected.add(stringChild);
            } else {
                selected.addAll(getSelectedLastChildren(stringChild));
            }
        });
        return selected;
    }

    private void validateSpinnerValues() {
        double remaining = 1.0;

        for (int i = 0; i < tMeasures.size() - 1; i++) {
            double value = tMeasures.get(i).getValue();
            double maxAllowedValue = Math.min(value, remaining);
            tMeasures.get(i).getValueFactory().setValue(maxAllowedValue);
            remaining -= maxAllowedValue;
        }
        tMeasures.get(tMeasures.size() - 1).getValueFactory().setValue(remaining);
    }
}
