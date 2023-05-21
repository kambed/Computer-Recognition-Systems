package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbsoluteQuantifier;

import java.util.List;

public class SingleType2Summary extends SingleSubjectSummary {
    protected LabeledFuzzySet qualifier;

    public SingleType2Summary(AbsoluteQuantifier quantifier, Subject subject, LabeledFuzzySet qualifier, LabeledFuzzySet summarizer, List<Double> weights) {
        super(quantifier, subject, summarizer, weights, quantifier.getLabel() + " " + subject.getName() + " będących/mających " + qualifier.getLabel() + " jest/ma " + summarizer.getLabel());
        this.qualifier = qualifier;
    }

    @Override
    protected double calculateT1() {
        return 0;
    }

    @Override
    protected double calculateT2() {
        return 0;
    }

    @Override
    protected double calculateT3() {
        return 0;
    }

    @Override
    protected double calculateT4() {
        return 0;
    }

    @Override
    protected double calculateT5() {
        return 0;
    }

    @Override
    protected double calculateT6() {
        return 0;
    }

    @Override
    protected double calculateT7() {
        return 0;
    }

    @Override
    protected double calculateT8() {
        return 0;
    }

    @Override
    protected double calculateT9() {
        return 0;
    }

    @Override
    protected double calculateT10() {
        return 0;
    }

    @Override
    protected double calculateT11() {
        return 0;
    }
}
