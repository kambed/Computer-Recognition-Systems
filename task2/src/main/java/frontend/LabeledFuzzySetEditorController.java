package frontend;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.quantifier.AbstractQuantifier;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LabeledFuzzySetEditorController {
    @FXML
    public TextField labelTextField;
    @FXML
    private FunctionController functionController;
    private LabeledFuzzySet labeledFuzzySet;

    public LabeledFuzzySet getLabeledFuzzySet() {
        return labeledFuzzySet;
    }

    public void setLabeledFuzzySet(LabeledFuzzySet labeledFuzzySet) {
        this.labeledFuzzySet = labeledFuzzySet;
        if (labeledFuzzySet != null) {
            labelTextField.setText(labeledFuzzySet.getLabel());
            functionController.setFunction(labeledFuzzySet.getFunction(), labeledFuzzySet instanceof AbstractQuantifier);
        }
    }
}
