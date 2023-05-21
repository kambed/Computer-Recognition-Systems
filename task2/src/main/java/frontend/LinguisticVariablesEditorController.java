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
    private VBox labeledFuzzySetList;
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
        selectedVariable  = linguisticVariablesComboBox.getSelectionModel().getSelectedItem();
        nameSection.setVisible(selectedVariable != null);
        labeledFuzzySetList.setVisible(selectedVariable != null);
        if (selectedVariable == null) {
            return;
        }
        labeledFuzzySetListController.setLabeledFuzzySets(selectedVariable.getLabeledFuzzySets());
        labeledFuzzySetListController.setUpdateLabeledFuzzySets(
                labeledFuzzySets -> {
                    selectedVariable.setLabeledFuzzySets(labeledFuzzySets);
                    linguisticVariablesComboBox.getItems().setAll(variables);
                    updateVariables.accept(variables);
                },
                false
        );
    }

    public void saveName() {
        labeledFuzzySetList.setVisible(selectedVariable != null);
        if (selectedVariable == null) {
            return;
        }
        selectedVariable.setName(linguisticVariableNameTextField.getText());
        linguisticVariablesComboBox.getItems().setAll(variables);
        updateVariables.accept(variables);
    }
}
