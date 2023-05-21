package frontend;

import backend.lingustic.quantifier.AbstractQuantifier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LinguisticQuantifiersEditorController implements Initializable {
    @FXML
    private ListView<AbstractQuantifier> quantifiersList;
    private List<AbstractQuantifier> quantifiers;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setQuantifiers(List<AbstractQuantifier> quantifiers) {
        this.quantifiers = quantifiers;
        quantifiersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        quantifiersList.getItems().addAll(quantifiers);
    }
}
