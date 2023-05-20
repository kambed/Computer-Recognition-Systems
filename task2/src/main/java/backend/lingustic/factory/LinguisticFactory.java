package backend.lingustic.factory;

import backend.functions.BaseFunction;
import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Variable;
import backend.lingustic.quantifier.AbsoluteQuantifier;
import backend.lingustic.quantifier.AbstractQuantifier;
import backend.lingustic.quantifier.RelativeQuantifier;

import java.util.List;

public class LinguisticFactory {

    private LinguisticFactory() {
    }

    public static LabeledFuzzySet createLabel(String label, BaseFunction function) {
        return new LabeledFuzzySet(label, function);
    }

    public static Variable createVariable(String name, LabeledFuzzySet... labeledFuzzySets) {
        return new Variable(name, List.of(labeledFuzzySets));
    }

    public static AbsoluteQuantifier createAbsoluteQuantifier(String label, BaseFunction function) {
        return new AbsoluteQuantifier(label, function);
    }

    public static RelativeQuantifier createRelativeQuantifier(String label, BaseFunction function) {
        return new RelativeQuantifier(label, function);
    }
}
