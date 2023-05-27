package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;

import java.util.List;

public class MultiType4Summary extends MultiSubjectSummary {
    protected MultiType4Summary(List<Subject> subjects, List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames, String summary) {
        super(subjects, summarizers, summarizerVariableNames, summary);
        this.t1 = evaluateT1();
        this.finalDegreeOfTruth = evaluateFinalDegreeOfTruth();
    }

    @Override
    protected double evaluateT1() {
        return 0;
    }
}
