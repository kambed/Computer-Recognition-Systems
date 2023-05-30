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
        return (eliminateNaN(t1) * weights.get(0) +
                eliminateNaN(t2) * weights.get(1) +
                eliminateNaN(t3) * weights.get(2) +
                eliminateNaN(t4) * weights.get(3) +
                eliminateNaN(t5) * weights.get(4) +
                eliminateNaN(t6) * weights.get(5) +
                eliminateNaN(t7) * weights.get(6) +
                eliminateNaN(t8) * weights.get(7) +
                eliminateNaN(t9) * weights.get(8) +
                eliminateNaN(t10) * weights.get(9) +
                eliminateNaN(t11) * weights.get(10)) /
                (weights.stream().mapToDouble(Double::doubleValue).sum());
    }

    protected double eliminateNaN(double value) {
        return Double.isNaN(value) ? 0 : value;
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
