package frontend;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.factory.LinguisticFactory;
import backend.lingustic.quantifier.AbsoluteQuantifier;
import backend.lingustic.quantifier.AbstractQuantifier;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.function.Consumer;

public class LabeledFuzzySetEditorController {
    @FXML
    public TextField labelTextField;
    @FXML
    private HBox quantifierSection;
    @FXML
    private CheckBox isAbsoluteCheckBox;
    @FXML
    private FunctionController functionController;
    private LabeledFuzzySet labeledFuzzySet;
    private Consumer<LabeledFuzzySet> updateLabeledFuzzySet;
    private boolean isQuantifier;

    public void setLabeledFuzzySet(LabeledFuzzySet labeledFuzzySet) {
        this.labeledFuzzySet = labeledFuzzySet;
        if (labeledFuzzySet == null) {
            return;
        }
        labelTextField.setText(labeledFuzzySet.getLabel());
        functionController.setFunction(labeledFuzzySet.getFunction());
        if (isQuantifier) {
            isAbsoluteCheckBox.setSelected(labeledFuzzySet instanceof AbsoluteQuantifier);
        }
    }

    public void setUpdateLabeledFuzzySet(Consumer<LabeledFuzzySet> updateLabeledFuzzySet, boolean isQuantifier) {
        this.updateLabeledFuzzySet = updateLabeledFuzzySet;
        this.isQuantifier = isQuantifier;
        if (isQuantifier) {
            quantifierSection.setVisible(true);
            functionController.setQuantifier(true);
        }
    }

    public void save() {
        if (updateLabeledFuzzySet == null) {
            return;
        }
        if (labeledFuzzySet == null) {
            if (isQuantifier && isAbsoluteCheckBox.isSelected()) {
                labeledFuzzySet = LinguisticFactory.createAbsoluteQuantifier(
                        labelTextField.getText(),
                        functionController.getFunction()
                );
            } else if (isQuantifier) {
                labeledFuzzySet = LinguisticFactory.createRelativeQuantifier(
                        labelTextField.getText(),
                        functionController.getFunction()
                );
            } else {
                labeledFuzzySet = LinguisticFactory.createLabel(
                        labelTextField.getText(),
                        functionController.getFunction()
                );
            }
        } else {
            labeledFuzzySet.setLabel(labelTextField.getText());
            labeledFuzzySet.setFunction(functionController.getFunction());
        }
        updateLabeledFuzzySet.accept(labeledFuzzySet);
    }
}
