package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbstractQuantifier;

import java.util.List;

public class MultiType2Summary extends MultiSubjectSummary {

    private final LabeledFuzzySet quantifier;
    private final List<LabeledFuzzySet> qualifiers;
    private final List<String> qualifierVariableNames;

    public MultiType2Summary(AbstractQuantifier quantifier, List<Subject> subjects, List<LabeledFuzzySet> qualifiers, List<String> qualifierVariableNames,
                             List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames) {
        super(subjects, summarizers, summarizerVariableNames, "");
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.qualifierVariableNames = qualifierVariableNames;
        this.t1 = calculateT1();
        this.finalDegreeOfTruth = calculateFinalDegreeOfTruth();
    }

    @Override
    protected double calculateT1() {
        return 0;
    }
}
