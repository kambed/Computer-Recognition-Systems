package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbstractQuantifier;
import backend.sets.FuzzySet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SingleType2Summary extends SingleSubjectSummary {
    private final List<LabeledFuzzySet> qualifiers;
    private final List<String> qualifierVariableNames;

    public SingleType2Summary(AbstractQuantifier quantifier, Subject subject, List<LabeledFuzzySet> qualifiers, List<String> qualifierVariableNames,
                              List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames, List<Double> weights) {
        super(quantifier, subject, summarizers, summarizerVariableNames,
                quantifier.getLabel() + " " + subject.getName() + " będących/mających " +
                        qualifiers.stream().map(LabeledFuzzySet::getLabel).collect(Collectors.joining(" i ")) +
                        " jest/ma " + summarizers.stream().map(LabeledFuzzySet::getLabel).collect(Collectors.joining(" i ")));
        this.qualifiers = qualifiers;
        this.qualifierVariableNames = qualifierVariableNames;
        this.t1 = evaluateT1();
        this.t2 = evaluateT2();
        this.t3 = evaluateT3();
        this.t4 = evaluateT4();
        this.t5 = evaluateT5();
        this.t6 = evaluateT6();
        this.t7 = evaluateT7();
        this.t8 = evaluateT8();
        this.t9 = evaluateT9();
        this.t10 = evaluateT10();
        this.t11 = evaluateT11();
        this.finalDegreeOfTruth = evaluateFinalDegreeOfTruth(weights);
    }

    @Override
    protected double evaluateT1() {
        if (subject.getElementsCardinality(qualifiers, qualifierVariableNames) == 0) {
            return 0;
        }
        return quantifier.getFunction().getValue(
                subject.getElementsCardinality(Stream.concat(summarizers.stream(), qualifiers.stream()).toList(),
                        Stream.concat(summarizerVariableNames.stream(), qualifierVariableNames.stream()).toList()) /
                        subject.getElementsCardinality(qualifiers, qualifierVariableNames)
        );
    }

    @Override
    protected double evaluateT2() {
        return 1 - subject.getElementsSupportCardinality(summarizers, summarizerVariableNames) / subject.getElements().size();
    }

    @Override
    protected double evaluateT3() {
        if (subject.getElementsSupportCardinality(qualifiers, qualifierVariableNames) == 0) {
            return 0;
        }
        return subject.getElementsSupportCardinality(Stream.concat(summarizers.stream(), qualifiers.stream()).toList(),
                Stream.concat(summarizerVariableNames.stream(), qualifierVariableNames.stream()).toList()) /
                subject.getElementsSupportCardinality(qualifiers, qualifierVariableNames);
    }

    @Override
    protected double evaluateT4() {
        List<Double> cardinalities = new ArrayList<>();
        IntStream.range(0, summarizers.size()).forEach(i -> cardinalities.add(subject.getElementsSupportCardinality(List.of(summarizers.get(i)), List.of(summarizerVariableNames.get(i))) / subject.getElements().size()));
        return Math.abs(cardinalities.stream().mapToDouble(Double::doubleValue).reduce(1, (a, b) -> a * b) - t3);
    }

    @Override
    protected double evaluateT5() {
        return 2 * Math.pow(0.5, summarizers.size());
    }

    @Override
    protected double evaluateT6() {
        return 1 - quantifier.getDegreeOfFuzziness();
    }

    @Override
    protected double evaluateT7() {
        return 1 - quantifier.getCardinality();
    }

    @Override
    protected double evaluateT8() {
        return 1 - subject.getElementsCardinality(summarizers, summarizerVariableNames) / subject.getElements().size();
    }

    @Override
    protected double evaluateT9() {
        return 1 - subject.getElementsSupportCardinality(qualifiers, qualifierVariableNames) / subject.getElements().size();
    }

    @Override
    protected double evaluateT10() {
        return 1 - subject.getElementsCardinality(qualifiers, qualifierVariableNames) / subject.getElements().size();
    }

    @Override
    protected double evaluateT11() {
        return 2 * Math.pow(0.5, qualifiers.size());
    }
}
