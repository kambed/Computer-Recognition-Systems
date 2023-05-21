package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbsoluteQuantifier;

import java.util.List;

public class SingleType1Summary extends SingleSubjectSummary {

    public SingleType1Summary(AbsoluteQuantifier quantifier, Subject subject, LabeledFuzzySet summarizer, List<Double> weights) {
        super(quantifier, subject, summarizer, weights, quantifier.getLabel() + " " + subject.getName() + " jest/ma " + summarizer.getLabel());
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
