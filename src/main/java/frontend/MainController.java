package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    public static final String RESOURCE = "main-view.fxml";
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}