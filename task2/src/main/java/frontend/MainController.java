package frontend;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Variable;
import backend.lingustic.predefined.PredefinedQuantifiers;
import backend.lingustic.predefined.PredefinedVariables;
import backend.lingustic.quantifier.AbstractQuantifier;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainController implements Initializable {
    public static final String RESOURCE = "main.fxml";
    @FXML
    private LinguisticVariablesEditorController variablesEditTabController;
    @FXML
    private LabeledFuzzySetListController quantifiersEditTabController;
    @FXML
    private TabPane tabPane;
    @FXML
    private SummariesGeneratorController summariesGeneratorTabController;

    private final List<Variable> variables = PredefinedVariables.getPredefinedVariables();

    private final List<AbstractQuantifier> quantifiers = Stream.concat(
            PredefinedQuantifiers.getPredefinedRelativeQuantifiers().stream(),
            PredefinedQuantifiers.getPredefinedAbsoluteQuantifiers().stream()
    ).collect(Collectors.toList());

    private int summaryTabNumber = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabPane.getSelectionModel().select(1);
        summariesGeneratorTabController.setCreateSummaryTab(this::createNewSummaryTab);
        summariesGeneratorTabController.setVariables(variables);
        summariesGeneratorTabController.setQuantifiers(quantifiers);
        variablesEditTabController.setVariables(variables);
        variablesEditTabController.setUpdateVariables(this::updateVariables);
        quantifiersEditTabController.setLabeledFuzzySets(
                quantifiers.stream()
                        .map(LabeledFuzzySet.class::cast)
                        .collect(Collectors.toList())
        );
        quantifiersEditTabController.setUpdateLabeledFuzzySets(this::updateQuantifiers, true);
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

    public void updateVariables(List<Variable> variables) {
        summariesGeneratorTabController.setVariables(variables);
    }

    public void updateQuantifiers(List<LabeledFuzzySet> quantifiers) {
        List<AbstractQuantifier> quantifiersMapped = quantifiers.stream()
                .map(AbstractQuantifier.class::cast)
                .collect(Collectors.toList());

        summariesGeneratorTabController.setQuantifiers(quantifiersMapped);
    }
}