package frontend;

import backend.lingustic.quantifier.AbstractQuantifier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class LinguisticQuantifiersEditorController implements Initializable {
    @FXML
    private ListView<AbstractQuantifier> quantifiersList;
    @FXML
    public LabeledFuzzySetEditorController labeledFuzzySetEditorController;
    private List<AbstractQuantifier> quantifiers;
    private AbstractQuantifier selectedQuantifier;
    private Consumer<List<AbstractQuantifier>> updateQuantifiers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labeledFuzzySetEditorController.setUpdateLabeledFuzzySet(labeledFuzzySet -> {
            if (selectedQuantifier == null) {
                quantifiers.add((AbstractQuantifier) labeledFuzzySet);
                quantifiersList.getItems().setAll(quantifiers);
            }
            quantifiersList.refresh();
            updateQuantifiers.accept(quantifiers);
        }, true);
    }

    public void setQuantifiers(List<AbstractQuantifier> quantifiers) {
        this.quantifiers = quantifiers;
        quantifiersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        quantifiersList.getItems().addAll(quantifiers);
        quantifiersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedQuantifier = newValue;
            labeledFuzzySetEditorController.setLabeledFuzzySet(selectedQuantifier);
        });
    }

    public void setUpdateQuantifiers(Consumer<List<AbstractQuantifier>> updateQuantifiers) {
        this.updateQuantifiers = updateQuantifiers;
    }
}
