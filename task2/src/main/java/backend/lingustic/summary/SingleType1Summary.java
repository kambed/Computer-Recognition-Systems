package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbstractQuantifier;
import backend.lingustic.quantifier.RelativeQuantifier;
import backend.sets.FuzzySet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingleType1Summary extends SingleSubjectSummary {

    public SingleType1Summary(AbstractQuantifier quantifier, Subject subject, List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames, List<Double> weights) {
        super(quantifier, subject, summarizers, summarizerVariableNames, quantifier.getLabel() + " " +
                subject.getName() + " jest/ma " + summarizers.stream().map(LabeledFuzzySet::getLabel).collect(Collectors.joining(" i ")));
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
        int M = 1;
        if (quantifier instanceof RelativeQuantifier) {
            M = subject.getElements().size();
        }
        return quantifier.getFunction().getValue(subject.getElementsCardinality(summarizers, summarizerVariableNames) / M);
    }

    @Override
    protected double calculateT2() {
        return 1 - Math.pow(summarizers.stream().mapToDouble(FuzzySet::getDegreeOfFuzziness).reduce(1, (a, b) -> a * b),
                1.0 / summarizers.size());
    }

    @Override
    protected double calculateT3() {
        return subject.getElementsSupportCardinality(summarizers, summarizerVariableNames) / subject.getElements().size();
    }

    @Override
    protected double calculateT4() {
        List<Double> cardinalities = new ArrayList<>();
        IntStream.range(0, summarizers.size()).forEach(i -> cardinalities.add(subject.getElementsSupportCardinality(List.of(summarizers.get(i)), List.of(summarizerVariableNames.get(i))) / subject.getElements().size()));
        return Math.abs(cardinalities.stream().mapToDouble(Double::doubleValue).reduce(1, (a, b) -> a * b) - t3);
    }

    @Override
    protected double calculateT5() {
        return 2 * Math.pow(0.5, summarizers.size());
    }

    @Override
    protected double calculateT6() {
        return 1 - quantifier.getDegreeOfFuzziness();
    }

    @Override
    protected double calculateT7() {
        return 1 - quantifier.getDegreeOfCardinality();
    }

    @Override
    protected double calculateT8() {
        return 1 - Math.pow(summarizers.stream().mapToDouble(FuzzySet::getDegreeOfCardinality).reduce(1, (a, b) -> a * b),
                1.0 / summarizers.size());
    }

    @Override
    protected double calculateT9() {
        return 1;
    }

    @Override
    protected double calculateT10() {
        return 1;
    }

    @Override
    protected double calculateT11() {
        return 1;
    }
}
