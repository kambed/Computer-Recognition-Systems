package frontend;

import backend.lingustic.Variable;
import java.util.List;
import java.util.function.Consumer;

public class LinguisticVariablesEditorController {
    private List<Variable> variables;
    private Consumer<List<Variable>> updateVariables;

    public void setVariables(List<Variable> variables) {
    }

    public void setUpdateVariables(Consumer<List<Variable>> updateVariables) {
        this.updateVariables = updateVariables;
    }
}
