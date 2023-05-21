package frontend;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.quantifier.AbstractQuantifier;
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
    private List<LabeledFuzzySet> abstractQuantifiers;
    private LabeledFuzzySet selectedAbstractQuantifier;

    public void setLabeledFuzzySets(List<LabeledFuzzySet> abstractQuantifiers) {
        this.abstractQuantifiers = abstractQuantifiers;
        quantifiersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        quantifiersList.getItems().addAll(abstractQuantifiers);
        quantifiersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedAbstractQuantifier = newValue;
            labeledFuzzySetEditorController.setLabeledFuzzySet(selectedAbstractQuantifier);
        });
    }

    public void setUpdateLabeledFuzzySets(Consumer<List<LabeledFuzzySet>> updateLabeledFuzzySets, boolean isQuantifier) {
        labeledFuzzySetEditorController.setUpdateLabeledFuzzySet(labeledFuzzySet -> {
            if (selectedAbstractQuantifier == null) {
                abstractQuantifiers.add((AbstractQuantifier) labeledFuzzySet);
                quantifiersList.getItems().setAll(abstractQuantifiers);
            }
            quantifiersList.refresh();
            updateLabeledFuzzySets.accept(abstractQuantifiers);
        }, isQuantifier);
    }
}
