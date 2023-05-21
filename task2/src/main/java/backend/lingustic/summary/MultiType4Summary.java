package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;

import java.util.List;

public class MultiType4Summary extends MultiSubjectSummary {
    protected MultiType4Summary(List<Subject> subjects, List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames, String summary) {
        super(subjects, summarizers, summarizerVariableNames, summary);
        this.t1 = calculateT1();
        this.finalDegreeOfTruth = calculateFinalDegreeOfTruth();
    }

    @Override
    protected double calculateT1() {
        return 0;
    }
}
