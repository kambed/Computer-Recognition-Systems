package backend.lingustic.quantifier;

import backend.functions.BaseFunction;
import backend.lingustic.LabeledFuzzySet;

public abstract class AbstractQuantifier extends LabeledFuzzySet {
    protected AbstractQuantifier(String label, BaseFunction function) {
        super(label, function);
    }

    @Override
    public String toString() {
        return label;
    }
}
