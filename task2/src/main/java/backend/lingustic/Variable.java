package backend.lingustic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Variable {
    private String name;
    private final List<LabeledFuzzySet> labeledFuzzySets;

    public Variable(String name, List<LabeledFuzzySet> labeledFuzzySets) {
        this.name = name;
        this.labeledFuzzySets = labeledFuzzySets;
    }
}
