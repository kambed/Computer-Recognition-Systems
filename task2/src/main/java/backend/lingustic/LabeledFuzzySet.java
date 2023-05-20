package backend.lingustic;

import backend.functions.BaseFunction;
import backend.sets.FuzzySet;
import lombok.Getter;

@Getter
public class LabeledFuzzySet extends FuzzySet {

    private final String label;

    public LabeledFuzzySet(String label, BaseFunction function) {
        super(function);
        this.label = label;
    }
}
