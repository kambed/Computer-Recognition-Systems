package backend.lingustic.quantifier;

import backend.functions.BaseFunction;

public abstract class LabeledFuzzySet extends backend.lingustic.LabeledFuzzySet {
    protected LabeledFuzzySet(String label, BaseFunction function) {
        super(label, function);
    }

    @Override
    public String toString() {
        return label;
    }
}
