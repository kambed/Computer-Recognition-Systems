package frontend;

import backend.functions.BaseFunction;
import backend.lingustic.quantifier.AbstractQuantifier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LinguisticQuantifiersEditorController implements Initializable {
    @FXML
    private ListView<AbstractQuantifier> quantifiersList;
    @FXML
    public LabeledFuzzySetEditorController labeledFuzzySetEditorController;
    private List<AbstractQuantifier> quantifiers;
    private AbstractQuantifier selectedQuantifier;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setQuantifiers(List<AbstractQuantifier> quantifiers) {
        this.quantifiers = quantifiers;
        quantifiersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        quantifiersList.getItems().addAll(quantifiers);
        quantifiersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedQuantifier = newValue;
        });
    }
}
