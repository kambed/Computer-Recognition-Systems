package frontend;

import backend.lingustic.LabeledFuzzySet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LabeledFuzzySetEditorController implements Initializable {
    @FXML
    public TextField labelTextField;
    @FXML
    public ComboBox<String> functionComboBox;
    @FXML
    private FunctionController functionController;
    private LabeledFuzzySet labeledFuzzySet;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        functionComboBox.getItems().addAll("Triangle", "Trapezoid", "Gaussian", "Rectangular");
    }

    public LabeledFuzzySet getLabeledFuzzySet() {
        return labeledFuzzySet;
    }

    public void setLabeledFuzzySet(LabeledFuzzySet labeledFuzzySet) {
        this.labeledFuzzySet = labeledFuzzySet;
        if (labeledFuzzySet != null) {
            labelTextField.setText(labeledFuzzySet.getLabel());
            functionController.setFunction(labeledFuzzySet.getFunction());
        }
    }
}
