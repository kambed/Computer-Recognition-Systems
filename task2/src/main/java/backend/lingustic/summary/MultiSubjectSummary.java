package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;

import java.util.List;

public abstract class MultiSubjectSummary extends Summary {

    protected List<Subject> subjects;
    protected List<LabeledFuzzySet> summarizers;
    protected List<String> summarizerVariableNames;

    protected MultiSubjectSummary(List<Subject> subjects, List<LabeledFuzzySet> summarizers,
                                  List<String> summarizerVariableNames, String summary) {
        super(summary);
        this.subjects = subjects;
        this.summarizers = summarizers;
        this.summarizerVariableNames = summarizerVariableNames;
    }

    protected double evaluateFinalDegreeOfTruth() {
        return t1;
    }

    protected abstract double evaluateT1();
}
