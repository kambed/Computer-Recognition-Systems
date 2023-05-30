package frontend.utils;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

public class FileChoose {
    private static String lastUsedDir = "";

    private FileChoose() {
    }
    public static String choose(
            String windowTitle,
            ActionEvent actionEvent
    ) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle(windowTitle);
        if (!lastUsedDir.isEmpty()) {
            chooser.setInitialDirectory(new File(lastUsedDir));
        }
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text (*.txt)", "*.txt"));
        File chosenFile = chooser.showSaveDialog(
                ((Button) actionEvent.getSource())
                        .getScene()
                        .getWindow()
        );
        if (chosenFile == null) {
            return "";
        }
        lastUsedDir = chosenFile.getAbsoluteFile().getParentFile().getAbsolutePath();
        return chosenFile.getAbsolutePath();
    }
}

