package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbstractQuantifier;

import java.util.List;
import java.util.stream.Collectors;

public class MultiType1Summary extends MultiSubjectSummary {

    private final LabeledFuzzySet quantifier;

    public MultiType1Summary(AbstractQuantifier quantifier, List<Subject> subjects, List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames) {
        super(subjects, summarizers, summarizerVariableNames, quantifier.getLabel() + " " + subjects.get(0).getName() +
                " w porównaniu do " + subjects.get(1).getName() + " jest/ma " + summarizers.stream().map(LabeledFuzzySet::getLabel).collect(Collectors.joining(" i ")));
        this.quantifier = quantifier;
        this.t1 = evaluateT1();
        this.finalDegreeOfTruth = evaluateFinalDegreeOfTruth();
    }

    @Override
    protected double evaluateT1() {
        double denominator = (subjects.get(0).getElementsCardinality(summarizers, summarizerVariableNames) / subjects.get(0).getElements().size()) +
                (subjects.get(1).getElementsCardinality(summarizers, summarizerVariableNames) / subjects.get(1).getElements().size());
        if (denominator == 0) {
            return 0;
        }
        return quantifier.getFunction().getValue(
                (subjects.get(0).getElementsCardinality(summarizers, summarizerVariableNames) / subjects.get(0).getElements().size()) / denominator
        );
    }
}
