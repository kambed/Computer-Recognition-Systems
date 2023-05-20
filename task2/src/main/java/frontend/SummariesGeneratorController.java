package frontend;

import backend.lingustic.Variable;
import backend.lingustic.predefined.PredefinedVariables;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SummariesGeneratorController implements Initializable {

    @FXML
    private TreeView<String> tree;
    private final List<Variable> variables = PredefinedVariables.getPredefinedVariables();

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

        tree.setRoot(root);
        tree.setShowRoot(false);
        tree.setCellFactory(CheckBoxTreeCell.forTreeView());
    }

    public void generate() {
        List<CheckBoxTreeItem<String>> selected = getSelectedLastChildren((CheckBoxTreeItem<String>) tree.getRoot());
        selected.forEach(item -> System.out.println(item.getParent().getValue() + " - " + item.getValue()));
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
}
