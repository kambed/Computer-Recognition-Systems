package frontend.utils;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class AlertBox {
    private AlertBox() {
    }

    public static void show(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.initModality(Modality.NONE);
        alert.show();
    }
}
