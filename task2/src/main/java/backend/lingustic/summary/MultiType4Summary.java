package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiType4Summary extends MultiSubjectSummary {
    public MultiType4Summary(List<Subject> subjects, List<LabeledFuzzySet> summarizers, List<String> summarizerVariableNames) {
        super(subjects, summarizers, summarizerVariableNames, "Więcej " + subjects.get(0).getName() + " niż " + subjects.get(1).getName() +
                " jest/ma " + summarizers.stream().map(LabeledFuzzySet::getLabel).collect(Collectors.joining(" i ")));
        this.t1 = evaluateT1();
        this.finalDegreeOfTruth = evaluateFinalDegreeOfTruth();
    }

    @Override
    protected double evaluateT1() {
        Map<Integer, Double> s1Card = subjects.get(0).getAllElementsCardinality(summarizers, summarizerVariableNames);
        Map<Integer, Double> s2Card = subjects.get(1).getAllElementsCardinality(summarizers, summarizerVariableNames);
        return 1 - IntStream.range(0, s1Card.size() + s2Card.size())
                .mapToDouble(i ->
                        Math.min(1, 1 - s1Card.getOrDefault(i, 0.0) + s2Card.getOrDefault(i - s1Card.size(), 0.0))
                )
                .boxed()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElseThrow();
    }
}
