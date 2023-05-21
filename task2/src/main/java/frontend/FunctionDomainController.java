package frontend;

import backend.domain.ContinuousDomain;
import backend.domain.DiscreteDomain;
import backend.domain.Domain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FunctionDomainController implements Initializable {
    @FXML
    private HBox functionDomainSection;
    @FXML
    private ComboBox<String> functionDomainComboBox;
    @FXML
    private HBox continuousFunctionDomainSection;
    @FXML
    private TextField functionDomainStartTextField;
    @FXML
    private TextField functionDomainEndTextField;
    @FXML
    private VBox discreteFunctionDomainSection;
    @FXML
    private TextField functionDomainValuesTextField;
    private Domain domain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        functionDomainComboBox.getItems().addAll("Continuous", "Discrete");
    }

    public void domainTypeSelected() {
        if (functionDomainComboBox.getValue().equals("Continuous")) {
            continuousFunctionDomainSection.setVisible(true);
            discreteFunctionDomainSection.setVisible(false);
        } else {
            continuousFunctionDomainSection.setVisible(false);
            discreteFunctionDomainSection.setVisible(true);
        }
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain, boolean isQuantifier) {
        this.domain = domain;
        if (domain == null) {
            return;
        }
        if (isQuantifier) {
            functionDomainSection.setVisible(false);
        }
        if (domain instanceof ContinuousDomain) {
            continuousFunctionDomainSection.setVisible(true);
            discreteFunctionDomainSection.setVisible(false);
            functionDomainComboBox.setValue("Continuous");
            functionDomainStartTextField.setText(String.valueOf(domain.getMin()));
            functionDomainEndTextField.setText(String.valueOf(domain.getMax()));
        } else if (domain instanceof DiscreteDomain discreteDomain) {
            continuousFunctionDomainSection.setVisible(false);
            discreteFunctionDomainSection.setVisible(true);
            functionDomainComboBox.setValue("Discrete");
            functionDomainValuesTextField.setText(
                    discreteDomain.getValues()
                            .stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(", "))
            );
        }
    }
}
