package backend.lingustic;

import backend.functions.BaseFunction;
import backend.sets.FuzzySet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabeledFuzzySet extends FuzzySet {

    protected String label;

    public LabeledFuzzySet(String label, BaseFunction function) {
        super(function);
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
