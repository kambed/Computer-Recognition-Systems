package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbstractQuantifier;

import java.util.List;

public class MultiType1Summary extends MultiSubjectSummary {

    private final LabeledFuzzySet quantifier;

    public MultiType1Summary(AbstractQuantifier quantifier, List<Subject> subjects, List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames) {
        super(subjects, summarizers, summarizerVariableNames, "");
        this.quantifier = quantifier;
        this.t1 = evaluateT1();
        this.finalDegreeOfTruth = evaluateFinalDegreeOfTruth();
    }

    @Override
    protected double evaluateT1() {
        return 0;
    }
}
