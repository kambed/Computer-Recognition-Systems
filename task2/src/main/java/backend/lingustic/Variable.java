package backend.lingustic;

import lombok.Getter;

import java.util.List;

@Getter
public class Variable {
    private final String name;
    private final List<LabeledFuzzySet> labeledFuzzySets;

    public Variable(String name, List<LabeledFuzzySet> labeledFuzzySets) {
        this.name = name;
        this.labeledFuzzySets = labeledFuzzySets;
    }
}
