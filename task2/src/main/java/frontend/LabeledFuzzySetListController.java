package frontend;

import backend.lingustic.LabeledFuzzySet;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.List;
import java.util.function.Consumer;

public class LabeledFuzzySetListController {
    @FXML
    private ListView<LabeledFuzzySet> labeledFuzzySetList;
    @FXML
    public LabeledFuzzySetEditorController labeledFuzzySetEditorController;
    private List<LabeledFuzzySet> labeledFuzzySets;
    private LabeledFuzzySet selectedLabeledFuzzySet;

    public void setLabeledFuzzySets(List<LabeledFuzzySet> abstractQuantifiers) {
        this.labeledFuzzySets = abstractQuantifiers;
        labeledFuzzySetList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        labeledFuzzySetList.getItems().setAll(abstractQuantifiers);
        labeledFuzzySetList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedLabeledFuzzySet = newValue;
            labeledFuzzySetEditorController.setLabeledFuzzySet(selectedLabeledFuzzySet);
        });
    }

    public void setUpdateLabeledFuzzySets(Consumer<List<LabeledFuzzySet>> updateLabeledFuzzySets, boolean isQuantifier) {
        labeledFuzzySetEditorController.setUpdateLabeledFuzzySet(labeledFuzzySet -> {
            if (selectedLabeledFuzzySet == null) {
                labeledFuzzySets.add(labeledFuzzySet);
                labeledFuzzySetList.getItems().setAll(labeledFuzzySets);
            }
            labeledFuzzySetList.refresh();
            updateLabeledFuzzySets.accept(labeledFuzzySets);
            unselect();
        }, isQuantifier);
    }

    public void unselect() {
        labeledFuzzySetList.getSelectionModel().clearSelection();
        labeledFuzzySetEditorController.setLabeledFuzzySet(null);
    }
}
