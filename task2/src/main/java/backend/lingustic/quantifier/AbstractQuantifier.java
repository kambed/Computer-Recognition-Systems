package backend.lingustic.quantifier;

import backend.functions.BaseFunction;
import backend.lingustic.LabeledFuzzySet;

public class AbstractQuantifier extends LabeledFuzzySet {
    public AbstractQuantifier(String label, BaseFunction function) {
        super(label, function);
    }
}
