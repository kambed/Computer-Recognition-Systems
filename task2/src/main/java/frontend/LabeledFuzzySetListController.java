package frontend;

import backend.lingustic.quantifier.LabeledFuzzySet;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.List;
import java.util.function.Consumer;

public class LabeledFuzzySetListController {
    @FXML
    private ListView<LabeledFuzzySet> quantifiersList;
    @FXML
    public LabeledFuzzySetEditorController labeledFuzzySetEditorController;
    private List<LabeledFuzzySet> labeledFuzzySets;
    private LabeledFuzzySet selectedLabeledFuzzySet;

    public void setLabeledFuzzySets(List<LabeledFuzzySet> labeledFuzzySets) {
        this.labeledFuzzySets = labeledFuzzySets;
        quantifiersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        quantifiersList.getItems().addAll(labeledFuzzySets);
        quantifiersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedLabeledFuzzySet = newValue;
            labeledFuzzySetEditorController.setLabeledFuzzySet(selectedLabeledFuzzySet);
        });
    }

    public void setUpdateLabeledFuzzySets(Consumer<List<LabeledFuzzySet>> updateLabeledFuzzySets, boolean isQuantifier) {
        labeledFuzzySetEditorController.setUpdateLabeledFuzzySet(labeledFuzzySet -> {
            if (selectedLabeledFuzzySet == null) {
                labeledFuzzySets.add((LabeledFuzzySet) labeledFuzzySet);
                quantifiersList.getItems().setAll(labeledFuzzySets);
            }
            quantifiersList.refresh();
            updateLabeledFuzzySets.accept(labeledFuzzySets);
        }, isQuantifier);
    }
}
