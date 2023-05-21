package frontend;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.quantifier.AbstractQuantifier;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.function.Consumer;

public class LabeledFuzzySetEditorController {
    @FXML
    public TextField labelTextField;
    @FXML
    private FunctionController functionController;
    private LabeledFuzzySet labeledFuzzySet;
    private Consumer<LabeledFuzzySet> updateLabeledFuzzySet;

    public LabeledFuzzySet getLabeledFuzzySet() {
        if (labeledFuzzySet != null) {
            labeledFuzzySet.setLabel(labelTextField.getText());
            labeledFuzzySet.setFunction(functionController.getFunction());
        }
        return labeledFuzzySet;
    }

    public void setLabeledFuzzySet(LabeledFuzzySet labeledFuzzySet) {
        this.labeledFuzzySet = labeledFuzzySet;
        if (labeledFuzzySet == null) {
            return;
        }
        labelTextField.setText(labeledFuzzySet.getLabel());
        functionController.setFunction(labeledFuzzySet.getFunction(), labeledFuzzySet instanceof AbstractQuantifier);
    }

    public void setUpdateLabeledFuzzySet(Consumer<LabeledFuzzySet> updateLabeledFuzzySet) {
        this.updateLabeledFuzzySet = updateLabeledFuzzySet;
    }

    public void save() {
        if (updateLabeledFuzzySet == null) {
            return;
        }
        labeledFuzzySet.setLabel(labelTextField.getText());
        labeledFuzzySet.setFunction(functionController.getFunction());
        updateLabeledFuzzySet.accept(getLabeledFuzzySet());
    }
}
