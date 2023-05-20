package frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static final String RESOURCE = "main-view.fxml";

    @FXML
    private TreeView<String> tree;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Linguistic variables");
        root.setExpanded(true);
        for (int i = 0; i < 5; i++) {
            CheckBoxTreeItem<String> item = new CheckBoxTreeItem<>("Linguistic variable " + (i + 1));
            item.setExpanded(true);
            for (int j = 0; j < 5; j++) {
                CheckBoxTreeItem<String> item2 = new CheckBoxTreeItem<>("Label " + (i * 5 + j + 1));
                item2.setExpanded(true);
                item.getChildren().add(item2);
            }
            root.getChildren().add(item);
        }

        tree.setRoot(root);
        tree.setShowRoot(false);
        tree.setCellFactory(CheckBoxTreeCell.forTreeView());
    }

    public void generate() {
        List<CheckBoxTreeItem<String>> selected = getSelectedLastChildren((CheckBoxTreeItem<String>) tree.getRoot());
        selected.forEach(item -> System.out.println(item.getValue()));
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