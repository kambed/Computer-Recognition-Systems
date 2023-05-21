package frontend;

import backend.lingustic.quantifier.AbstractQuantifier;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.List;

public class LinguisticQuantifiersEditorController {
    @FXML
    private ListView<AbstractQuantifier> quantifiersList;
    @FXML
    public LabeledFuzzySetEditorController labeledFuzzySetEditorController;
    private List<AbstractQuantifier> quantifiers;
    private AbstractQuantifier selectedQuantifier;

    public void setQuantifiers(List<AbstractQuantifier> quantifiers) {
        this.quantifiers = quantifiers;
        quantifiersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        quantifiersList.getItems().addAll(quantifiers);
        quantifiersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedQuantifier = newValue;
            labeledFuzzySetEditorController.setLabeledFuzzySet(selectedQuantifier);
        });
    }
}
