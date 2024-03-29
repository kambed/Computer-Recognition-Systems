package backend;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.quantifier.AbsoluteQuantifier;
import backend.lingustic.quantifier.AbstractQuantifier;
import backend.lingustic.summary.*;
import backend.model.Stats;
import backend.repository.StatsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LinguisticSummarizationsExecutor {

    private LinguisticSummarizationsExecutor() {
    }

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
        return Stream.concat(
                getSingleSubjectSummaries(quantifiers, fuzzySets, summarizerVariableNames, weights).stream(),
                getMultiSubjectSummaries(quantifiers, fuzzySets, summarizerVariableNames).stream()
        ).toList();
    }

    private static List<Summary> getSingleSubjectSummaries(List<AbstractQuantifier> quantifiers, List<LabeledFuzzySet> fuzzySets, List<String> summarizerVariableNames, List<Double> weights) {
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
            if (abstractQuantifier instanceof AbsoluteQuantifier) {
                continue;
            }
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

    private static List<Summary> getMultiSubjectSummaries(List<AbstractQuantifier> quantifiers, List<LabeledFuzzySet> fuzzySets, List<String> summarizerVariableNames) {
        List<Summary> summaries = new ArrayList<>();

        List<String> topTeamsNames = List.of("Ferrari", "Mercedes", "Red Bull");
        List<Stats> topTeams = StatsRepository.getStats().stream().filter(s -> topTeamsNames.contains(s.getTeam())).toList();
        List<Stats> otherTeams = StatsRepository.getStats().stream().filter(s -> !topTeamsNames.contains(s.getTeam())).toList();

        List<Subject> subjects = new ArrayList<>(List.of(
                new Subject("wyników czołowych zespołów", topTeams),
                new Subject("wyników słabszych zespołów", otherTeams)
        ));

        //Multi subject type 1 summaries
        for (AbstractQuantifier abstractQuantifier : quantifiers) {
            if (abstractQuantifier instanceof AbsoluteQuantifier) {
                continue;
            }
            List<List<Integer>> sublists = getSublistsOfList(fuzzySets.size());
            for (int i = 0; i < subjects.size(); i++) {
                summaries.addAll(sublists.stream().map(l -> new MultiType1Summary(abstractQuantifier,
                        subjects,
                        l.stream().map(fuzzySets::get).toList(),
                        l.stream().map(summarizerVariableNames::get).toList())
                ).toList());
                Collections.reverse(subjects);
            }
        }

        //Multi subject type 2 summaries
        for (AbstractQuantifier abstractQuantifier : quantifiers) {
            if (abstractQuantifier instanceof AbsoluteQuantifier) {
                continue;
            }
            List<List<Integer>> sublists = getSublistsOfList(fuzzySets.size());
            for (int i = 0; i < subjects.size(); i++) {
                summaries.addAll(sublists.stream().map(l -> {
                    List<List<Integer>> sublists2 = getSublistsOfList(fuzzySets.size()).stream().filter(l2 -> l2.stream().noneMatch(l::contains)).toList();
                    return sublists2.stream().map(l2 -> new MultiType2Summary(abstractQuantifier,
                            subjects,
                            l2.stream().map(fuzzySets::get).toList(),
                            l2.stream().map(summarizerVariableNames::get).toList(),
                            l.stream().map(fuzzySets::get).toList(),
                            l.stream().map(summarizerVariableNames::get).toList())).toList();
                }).flatMap(List::stream).toList());
                Collections.reverse(subjects);
            }
        }

        //Multi subject type 3 summaries
        for (AbstractQuantifier abstractQuantifier : quantifiers) {
            if (abstractQuantifier instanceof AbsoluteQuantifier) {
                continue;
            }
            List<List<Integer>> sublists = getSublistsOfList(fuzzySets.size());
            for (int i = 0; i < subjects.size(); i++) {
                summaries.addAll(sublists.stream().map(l -> {
                    List<List<Integer>> sublists2 = getSublistsOfList(fuzzySets.size()).stream().filter(l2 -> l2.stream().noneMatch(l::contains)).toList();
                    return sublists2.stream().map(l2 -> new MultiType3Summary(abstractQuantifier,
                            subjects,
                            l2.stream().map(fuzzySets::get).toList(),
                            l2.stream().map(summarizerVariableNames::get).toList(),
                            l.stream().map(fuzzySets::get).toList(),
                            l.stream().map(summarizerVariableNames::get).toList())).toList();
                }).flatMap(List::stream).toList());
                Collections.reverse(subjects);
            }
        }

        //Multi subject type 4 summaries
        for (int i = 0; i < subjects.size(); i++) {
            List<List<Integer>> sublists = getSublistsOfList(fuzzySets.size());
            summaries.addAll(sublists.stream().map(l -> new MultiType4Summary(subjects,
                    l.stream().map(fuzzySets::get).toList(),
                    l.stream().map(summarizerVariableNames::get).toList())
            ).toList());
            Collections.reverse(subjects);
        }

        return summaries;
    }
}
