package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbstractQuantifier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SingleType2Summary extends SingleSubjectSummary {
    protected List<LabeledFuzzySet> qualifiers;
    protected List<String> qualifierVariableNames;

    public SingleType2Summary(AbstractQuantifier quantifier, Subject subject, List<LabeledFuzzySet> qualifiers, List<String> qualifierVariableNames, List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames, List<Double> weights) {
        super(quantifier, subject, summarizers, summarizerVariableNames,
                quantifier.getLabel() + " " + subject.getName() + " będących/mających " +
                        qualifiers.stream().map(LabeledFuzzySet::getLabel).collect(Collectors.joining(" i ")) +
                        " jest/ma " + summarizers.stream().map(LabeledFuzzySet::getLabel).collect(Collectors.joining(" i ")));
        this.qualifiers = qualifiers;
        this.qualifierVariableNames = qualifierVariableNames;
        this.t1 = calculateT1();
        this.t2 = calculateT2();
        this.t3 = calculateT3();
        this.t4 = calculateT4();
        this.t5 = calculateT5();
        this.t6 = calculateT6();
        this.t7 = calculateT7();
        this.t8 = calculateT8();
        this.t9 = calculateT9();
        this.t10 = calculateT10();
        this.t11 = calculateT11();
        this.finalDegreeOfTruth = calculateFinalDegreeOfTruth(weights);
    }

    @Override
    protected double calculateT1() {
        return quantifier.getFunction().getValue(
                subject.getElementsCardinality(Stream.concat(summarizers.stream(), qualifiers.stream()).toList(),
                        Stream.concat(summarizerVariableNames.stream(), qualifierVariableNames.stream()).toList()) /
                        subject.getElementsCardinality(qualifiers, qualifierVariableNames)
        );
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
