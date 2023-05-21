package frontend;

import backend.lingustic.Variable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

public class LinguisticVariablesEditorController {
    @FXML
    private HBox nameSection;
    @FXML
    private ComboBox<Variable> linguisticVariablesComboBox;
    @FXML
    private TextField linguisticVariableNameTextField;
    @FXML
    private LabeledFuzzySetListController labeledFuzzySetListController;
    private List<Variable> variables;
    private Consumer<List<Variable>> updateVariables;
    private Variable selectedVariable;

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
        linguisticVariablesComboBox.getItems().setAll(variables);
        linguisticVariablesComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                linguisticVariableNameTextField.setText(newValue.getName());
            }
        });
    }

    public void setUpdateVariables(Consumer<List<Variable>> updateVariables) {
        this.updateVariables = updateVariables;
    }

    public void variableSelected() {
        Variable selected = linguisticVariablesComboBox.getSelectionModel().getSelectedItem();
        nameSection.setVisible(selected != null);
        selectedVariable = selected;
        labeledFuzzySetListController.setLabeledFuzzySets(selectedVariable.getLabeledFuzzySets());
    }

    public void saveName() {
        if (selectedVariable == null) {
            return;
        }
        selectedVariable.setName(linguisticVariableNameTextField.getText());
        linguisticVariablesComboBox.getItems().setAll(variables);
        updateVariables.accept(variables);
    }
}
