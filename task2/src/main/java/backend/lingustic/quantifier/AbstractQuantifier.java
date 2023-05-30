package backend.lingustic.quantifier;

import backend.functions.BaseFunction;
import backend.lingustic.LabeledFuzzySet;

public abstract class AbstractQuantifier extends LabeledFuzzySet {
    protected AbstractQuantifier(String label, BaseFunction function) {
        super(label, function);
        if (!this.isConvex() || !this.isNormal()) {
            throw new IllegalArgumentException("Quantifier is not convex and normal");
        }
    }
}
