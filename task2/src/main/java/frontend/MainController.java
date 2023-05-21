package frontend;

import frontend.model.Summary;
import frontend.utils.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static final String RESOURCE = "main.fxml";
    @FXML
    private TabPane tabPane;
    @FXML
    private SummariesGeneratorController summariesGeneratorTabController;

    private int summaryTabNumber = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabPane.getSelectionModel().select(1);
        summariesGeneratorTabController.setCreateSummaryTab(this::createNewSummaryTab);
    }

    public void createNewSummaryTab(List<Summary> summaries) {
        try {
            Tab tab = new Tab();
            tab.setText("Summary " + summaryTabNumber++);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SummaryController.RESOURCE));
                tab.setContent(fxmlLoader.load());
            tab.setClosable(true);
            SummaryController summaryController = fxmlLoader.getController();
            summaryController.setData(summaries);
            tabPane.getTabs().add(tabPane.getTabs().size(), tab);
            tabPane.getSelectionModel().select(tab);
        } catch (IOException e) {
            AlertBox.show("Error", "Error while creating new summary tab", Alert.AlertType.ERROR);
        }
    }
}