package backend;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbstractQuantifier;
import backend.lingustic.summary.SingleType1Summary;
import backend.lingustic.summary.SingleType2Summary;
import backend.lingustic.summary.Summary;
import backend.repository.StatsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinguisticSummarizationsExecutor {

    private static List<List<Integer>> getSublistsOfList(int n) {
        List<Integer> list = IntStream.range(0, n).boxed().toList();
        int totalSubsets = 1 << n;

        List<List<Integer>> sublists = new ArrayList<>();
        for (int i = 0; i < totalSubsets; i++) {
            List<Boolean> isPresent = Integer.toBinaryString(i).chars().mapToObj(c -> c == '1').collect(Collectors.toList());
            while (isPresent.size() < n) {
                isPresent.add(0, false);
            }

            sublists.add(new ArrayList<>());

            for (int j = 0; j < n; j++) {
                if (isPresent.get(j)) {
                    sublists.get(i).add(list.get(j));
                }
            }
        }
        return sublists.stream().filter(l -> !l.isEmpty()).toList();
    }

    public static List<Summary> getSummaries(List<AbstractQuantifier> quantifiers, List<LabeledFuzzySet> fuzzySets, List<String> summarizerVariableNames, List<Double> weights) {
        List<Summary> summaries = new ArrayList<>();

        //Single subject type 1 summaries
        for (AbstractQuantifier abstractQuantifier : quantifiers) {
            List<List<Integer>> sublists = getSublistsOfList(fuzzySets.size());
            summaries.addAll(sublists.stream().map(l -> new SingleType1Summary(abstractQuantifier,
                    new Subject("wyników", StatsRepository.getStats()),
                    l.stream().map(fuzzySets::get).toList(),
                    l.stream().map(summarizerVariableNames::get).toList(),
                    weights)
            ).toList());
        }

        //Single subject type 2 summaries
        for (AbstractQuantifier abstractQuantifier : quantifiers) {
            List<List<Integer>> sublists = getSublistsOfList(fuzzySets.size());
            summaries.addAll(sublists.stream().map(l -> {
                List<List<Integer>> sublists2 = getSublistsOfList(fuzzySets.size()).stream().filter(l2 -> l2.stream().noneMatch(l::contains)).toList();
                return sublists2.stream().map(l2 -> new SingleType2Summary(abstractQuantifier,
                        new Subject("wyników", StatsRepository.getStats()),
                        l2.stream().map(fuzzySets::get).toList(),
                        l2.stream().map(summarizerVariableNames::get).toList(),
                        l.stream().map(fuzzySets::get).toList(),
                        l.stream().map(summarizerVariableNames::get).toList(),
                        weights)).toList();
            }).flatMap(List::stream).toList());
        }

        return summaries;
    }
}
