package backend.lingustic.factory;

import backend.functions.BaseFunction;
import backend.lingustic.Label;
import backend.lingustic.Variable;

import java.util.List;

public class VariableFactory {

    public static Label createLabel(String name, BaseFunction function) {
        return new Label(name, function);
    }

    public static Variable createVariable(String name, Label... labels) {
        return new Variable(name, List.of(labels));
    }
}
