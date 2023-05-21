package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbstractQuantifier;
import backend.lingustic.quantifier.RelativeQuantifier;

import java.util.List;
import java.util.stream.Collectors;

public class SingleType1Summary extends SingleSubjectSummary {

    public SingleType1Summary(AbstractQuantifier quantifier, Subject subject, List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames, List<Double> weights) {
        super(quantifier, subject, summarizers, summarizerVariableNames, weights, quantifier.getLabel() + " " +
                subject.getName() + " jest/ma " + summarizers.stream().map(LabeledFuzzySet::getLabel).collect(Collectors.joining(" i ")));
    }

    @Override
    protected double calculateT1() {
        int M = 1;
        if (quantifier instanceof RelativeQuantifier) {
            M = subject.getElements().size();
        }
        return quantifier.getFunction().getValue(subject.getElementsCardinality(summarizers, summarizerVariableNames) / M);
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
