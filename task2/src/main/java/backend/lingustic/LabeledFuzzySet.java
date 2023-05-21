package backend.lingustic;

import backend.functions.BaseFunction;
import backend.sets.FuzzySet;
import lombok.Getter;

@Getter
public class LabeledFuzzySet extends FuzzySet {

    protected final String label;

    public LabeledFuzzySet(String label, BaseFunction function) {
        super(function);
        this.label = label;
    }

    @Override
    public String toString() {
        return "Label: " + label + "\n" + super.toString();
    }
}
