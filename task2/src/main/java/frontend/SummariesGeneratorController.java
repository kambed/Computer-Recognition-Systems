package frontend;

import backend.lingustic.Variable;
import backend.lingustic.predefined.PredefinedQuantifiers;
import backend.lingustic.predefined.PredefinedVariables;
import backend.lingustic.quantifier.AbstractQuantifier;
import frontend.model.Summary;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class SummariesGeneratorController implements Initializable {
    @FXML
    private TreeView<String> variablesLabelsTree;
    @FXML
    private ListView<AbstractQuantifier> quantifiersList;
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
        createSummaryTab.accept(summaries);
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

    public void setCreateSummaryTab(Consumer<List<Summary>> createSummaryTab) {
        this.createSummaryTab = createSummaryTab;
    }
}
