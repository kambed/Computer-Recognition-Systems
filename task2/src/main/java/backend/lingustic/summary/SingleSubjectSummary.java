package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbstractQuantifier;

import java.util.List;

public abstract class SingleSubjectSummary extends Summary {
    protected AbstractQuantifier quantifier;
    protected Subject subject;
    protected List<LabeledFuzzySet> summarizers;
    protected List<String> summarizerVariableNames;

    protected SingleSubjectSummary(AbstractQuantifier quantifier, Subject subject, List<LabeledFuzzySet> summarizers,
                                   List<String> summarizerVariableNames, String summary) {
        super(summary);
        this.quantifier = quantifier;
        this.subject = subject;
        this.summarizers = summarizers;
        this.summarizerVariableNames = summarizerVariableNames;
    }

    protected double evaluateFinalDegreeOfTruth(List<Double> weights) {
        return (t1 * weights.get(0) +
                t2 * weights.get(1) +
                t3 * weights.get(2) +
                t4 * weights.get(3) +
                t5 * weights.get(4) +
                t6 * weights.get(5) +
                t7 * weights.get(6) +
                t8 * weights.get(7) +
                t9 * weights.get(8) +
                t10 * weights.get(9) +
                t11 * weights.get(10)) /
                (weights.stream().mapToDouble(Double::doubleValue).sum());
    }

    protected abstract double evaluateT1();

    protected abstract double evaluateT2();

    protected abstract double evaluateT3();

    protected abstract double evaluateT4();

    protected abstract double evaluateT5();

    protected abstract double evaluateT6();

    protected abstract double evaluateT7();

    protected abstract double evaluateT8();

    protected abstract double evaluateT9();

    protected abstract double evaluateT10();

    protected abstract double evaluateT11();
}
