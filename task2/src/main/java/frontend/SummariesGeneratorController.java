package frontend;

import backend.Rounder;
import backend.lingustic.Variable;
import backend.lingustic.predefined.PredefinedQuantifiers;
import backend.lingustic.predefined.PredefinedVariables;
import backend.lingustic.quantifier.AbstractQuantifier;
import frontend.model.Summary;
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
import java.util.stream.Stream;

public class SummariesGeneratorController implements Initializable {
    @FXML
    private TreeView<String> variablesLabelsTree;
    @FXML
    private ListView<AbstractQuantifier> quantifiersList;
    @FXML
    private VBox tMeasuresContainer;
    private final List<Spinner<Double>> tMeasures = new ArrayList<>();

    private final List<Variable> variables = PredefinedVariables.getPredefinedVariables();

    private final List<AbstractQuantifier> quantifiers = Stream.concat(
            PredefinedQuantifiers.getPredefinedRelativeQuantifiers().stream(),
            PredefinedQuantifiers.getPredefinedAbsoluteQuantifiers().stream()
    ).toList();
    private Consumer<List<Summary>> createSummaryTab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Linguistic variables");
        root.setExpanded(true);
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
        quantifiersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        quantifiersList.getItems().addAll(quantifiers);

        variablesLabelsTree.setRoot(root);
        variablesLabelsTree.setShowRoot(false);
        variablesLabelsTree.setCellFactory(CheckBoxTreeCell.forTreeView());

        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    HBox hBox = new HBox();
                    hBox.setSpacing(10);
                    Label label = new Label("T" + i + ": " + (i < 10 ? "  " : ""));
                    Spinner<Double> spinner = new Spinner<>(0.0, 1.0, 0.0, 0.01);
                    spinner.setEditable(true);
                    spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> validateSpinnerValues());
                    hBox.getChildren().addAll(label, spinner);
                    tMeasures.add(spinner);
                    tMeasuresContainer.getChildren().addAll(hBox);
                });
    }

    public void generate() {
        List<CheckBoxTreeItem<String>> selected = getSelectedLastChildren((CheckBoxTreeItem<String>) variablesLabelsTree.getRoot());
        selected.forEach(item -> System.out.println(item.getParent().getValue() + " - " + item.getValue()));
        quantifiersList.getSelectionModel().getSelectedItems().forEach(System.out::println);
        List<Summary> summaries = List.of(
                new Summary("Name 1", 1.0, 1.0, 1.0),
                new Summary("Name 2", 1.0, 2.0, 1.0),
                new Summary("Name 3", 3.0, 1.0, 1.0),
                new Summary("Name 4", 2.0, 2.0, 1.0),
                new Summary("Name 5", 1.5, 4.0, 1.0)
        );
        System.out.println("T measures:");
        tMeasures.forEach(spinner -> System.out.println(spinner.getValue()));
        createSummaryTab.accept(summaries);
    }

    public void setCreateSummaryTab(Consumer<List<Summary>> createSummaryTab) {
        this.createSummaryTab = createSummaryTab;
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
        double sum = tMeasures.stream().mapToDouble(Spinner::getValue).sum();
        if (sum == 1.0) {
            return;
        }
        tMeasures.forEach(spinner -> spinner.getValueFactory().setValue(
                Rounder.round(spinner.getValue() * (1.0 / sum))
        ));
    }
}
