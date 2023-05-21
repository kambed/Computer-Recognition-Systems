package frontend;

import backend.lingustic.Variable;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.util.List;

public class LinguisticVariablesEditorController {
    @FXML
    private TreeView<String> variablesLabelsTree;
    private List<Variable> variables;

    public void setVariables(List<Variable> variables) {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Linguistic variables");
        root.setExpanded(true);
        variablesLabelsTree.setRoot(root);
        variablesLabelsTree.setShowRoot(false);
        variablesLabelsTree.setCellFactory(CheckBoxTreeCell.forTreeView());
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
}
