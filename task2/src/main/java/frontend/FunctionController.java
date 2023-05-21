package frontend;

import backend.functions.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FunctionController implements Initializable {
    @FXML
    private ComboBox<String> functionComboBox;
    @FXML
    private HBox averageSection;
    @FXML
    private HBox standardDeviationSection;
    @FXML
    private HBox minimumSupportSection;
    @FXML
    private HBox maximumSupportSection;
    @FXML
    private HBox minimumHeightSection;
    @FXML
    private HBox maximumHeightSection;
    @FXML
    private TextField minimumSupportTextField;
    @FXML
    private TextField maximumSupportTextField;
    @FXML
    private TextField minimumHeightTextField;
    @FXML
    private TextField maximumHeightTextField;
    @FXML
    private TextField averageTextField;
    @FXML
    private TextField standardDeviationTextField;
    @FXML
    private FunctionDomainController functionDomainController;
    private boolean isQuantifier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        functionComboBox.getItems()
                .addAll("GaussianFunction", "RectangularFunction", "TriangularFunction", "TrapezoidalFunction");
    }

    public BaseFunction getFunction() {
        String functionName = functionComboBox.getSelectionModel().getSelectedItem();
        return switch (functionName) {
            case "GaussianFunction" -> new GaussianFunction(
                    functionDomainController.getDomain(),
                    Double.parseDouble(averageTextField.getText()),
                    Double.parseDouble(standardDeviationTextField.getText())
            );
            case "RectangularFunction" -> new RectangularFunction(
                    functionDomainController.getDomain(),
                    Double.parseDouble(minimumSupportTextField.getText()),
                    Double.parseDouble(maximumSupportTextField.getText())
            );
            case "TriangularFunction" -> new TriangularFunction(
                    functionDomainController.getDomain(),
                    Double.parseDouble(minimumSupportTextField.getText()),
                    Double.parseDouble(maximumSupportTextField.getText()),
                    Double.parseDouble(maximumHeightTextField.getText())
            );
            case "TrapezoidalFunction" -> new TrapezoidalFunction(
                    functionDomainController.getDomain(),
                    Double.parseDouble(minimumSupportTextField.getText()),
                    Double.parseDouble(maximumSupportTextField.getText()),
                    Double.parseDouble(minimumHeightTextField.getText()),
                    Double.parseDouble(maximumHeightTextField.getText())
            );
            default -> null;
        };
    }

    public void setFunction(BaseFunction function) {
        functionComboBox.getSelectionModel().clearSelection();
        averageTextField.setText("");
        standardDeviationTextField.setText("");
        minimumSupportTextField.setText("");
        maximumSupportTextField.setText("");
        minimumHeightTextField.setText("");
        maximumHeightTextField.setText("");

        averageSection.setVisible(false);
        standardDeviationSection.setVisible(false);
        minimumSupportSection.setVisible(false);
        maximumSupportSection.setVisible(false);
        minimumHeightSection.setVisible(false);
        maximumHeightSection.setVisible(false);

        if (function == null) {
            functionDomainController.setDomain(null);
            return;
        }
        functionComboBox.getSelectionModel().select(function.getClass().getSimpleName());
        functionDomainController.setDomain(function.getDomain());

        if (function instanceof GaussianFunction gaussianFunction) {
            averageTextField.setText(String.valueOf(gaussianFunction.getAverage()));
            standardDeviationTextField.setText(String.valueOf(gaussianFunction.getStandardDeviation()));

            averageSection.setVisible(true);
            standardDeviationSection.setVisible(true);
        } else if (function instanceof RectangularFunction rectangularFunction) {
            minimumSupportTextField.setText(String.valueOf(rectangularFunction.getMinSupp()));
            maximumSupportTextField.setText(String.valueOf(rectangularFunction.getMaxSupp()));

            minimumSupportSection.setVisible(true);
            maximumSupportSection.setVisible(true);
        } else if (function instanceof TriangularFunction triangularFunction) {
            minimumSupportTextField.setText(String.valueOf(triangularFunction.getMinSupp()));
            maximumSupportTextField.setText(String.valueOf(triangularFunction.getMaxSupp()));
            maximumHeightTextField.setText(String.valueOf(triangularFunction.getMaxHeight()));

            minimumSupportSection.setVisible(true);
            maximumSupportSection.setVisible(true);
            maximumHeightSection.setVisible(true);
        } else if (function instanceof TrapezoidalFunction trapezoidalFunction) {
            minimumSupportTextField.setText(String.valueOf(trapezoidalFunction.getMinSupp()));
            maximumSupportTextField.setText(String.valueOf(trapezoidalFunction.getMaxSupp()));
            minimumHeightTextField.setText(String.valueOf(trapezoidalFunction.getMinHeight()));
            maximumHeightTextField.setText(String.valueOf(trapezoidalFunction.getMaxHeight()));

            minimumSupportSection.setVisible(true);
            maximumSupportSection.setVisible(true);
            minimumHeightSection.setVisible(true);
            maximumHeightSection.setVisible(true);
        }
    }

    public void updateFunctionType() {
        String functionType = functionComboBox.getSelectionModel().getSelectedItem();
        if (functionType == null) {
            return;
        }
        averageSection.setVisible(false);
        standardDeviationSection.setVisible(false);
        minimumSupportSection.setVisible(false);
        maximumSupportSection.setVisible(false);
        minimumHeightSection.setVisible(false);
        maximumHeightSection.setVisible(false);
        switch (functionType) {
            case "GaussianFunction" -> {
                averageSection.setVisible(true);
                standardDeviationSection.setVisible(true);
            }
            case "RectangularFunction" -> {
                minimumSupportSection.setVisible(true);
                maximumSupportSection.setVisible(true);
            }
            case "TriangularFunction" -> {
                minimumSupportSection.setVisible(true);
                maximumSupportSection.setVisible(true);
                maximumHeightSection.setVisible(true);
            }
            case "TrapezoidalFunction" -> {
                minimumSupportSection.setVisible(true);
                maximumSupportSection.setVisible(true);
                minimumHeightSection.setVisible(true);
                maximumHeightSection.setVisible(true);
            }
            default -> throw new IllegalStateException("Unexpected value: " + functionType);
        }
    }

    public void setQuantifier(boolean isQuantifier) {
        functionDomainController.setIsQuantifier(isQuantifier);
    }
}
