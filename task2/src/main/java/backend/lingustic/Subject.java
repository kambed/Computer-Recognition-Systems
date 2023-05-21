package backend.lingustic;

import backend.model.Stats;
import lombok.Getter;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@Getter
public class Subject {
    private final String name;
    private List<Stats> elements;
    private final Map<String, Supplier<List<Double>>> namesToElements = new HashMap<>() {{
        put("Miejsce końcowe w wyścigu", () -> elements.stream().map(Stats::getFinishPosition).map(Double::valueOf).toList());
        put("Pozycja startowa", () -> elements.stream().map(Stats::getStartPosition).map(Double::valueOf).toList());
        put("Liczba przejechanych okrążeń", () -> elements.stream().map(Stats::getNumberOfLaps).map(Double::valueOf).toList());
        put("Liczba zdobytych punktów", () -> elements.stream().map(Stats::getNumberOfPoints).toList());
        put("Wiek kierowcy", () -> elements.stream().map(Stats::getDriverAge).toList());
        put("Procent punktów zdobytych dla zespołu", () -> elements.stream().map(Stats::getPercentageOfPointsGotForATeam).toList());
        put("Najszybsze okrążenie w wyścigu", () -> elements.stream().map(Stats::getFastestLap).toList());
        put("Prędkość najszybszego okrążenia", () -> elements.stream().map(Stats::getFastestLapSpeed).toList());
        put("Najszybszy pitstop", () -> elements.stream().map(Stats::getFastestPitStop).toList());
        put("Najszybsze okrążenie kwalifikacyjne", () -> elements.stream().map(Stats::getFastestQualiLap).toList());
        put("Dzień roku wyścigu", () -> elements.stream().map(Stats::getRaceData).map(Double::valueOf).toList());
        put("Godzina rozpoczęcia wyścigu", () -> elements.stream().map(Stats::getRaceTime).toList());
        put("Wysokość toru n.p.m.", () -> elements.stream().map(Stats::getTrackAltitude).toList());
        put("Klimat toru", () -> elements.stream().map(Stats::getTrackLatitude).toList());
    }};

    public Subject(String name, List<Stats> elements) {
        this.name = name;
        this.elements = elements;
    }

    private List<Double> getSumOfAll(List<LabeledFuzzySet> fuzzySets, List<String> summarizerNames) {
        List<List<Double>> elementsParams = new ArrayList<>();
        IntStream.range(0, summarizerNames.size()).forEach(
                i -> elementsParams.add(namesToElements.get(summarizerNames.get(i)).get().stream().map(
                        element -> element == null ? null : fuzzySets.get(i).getFunction().getValue(element)
                ).toList())
        );

        List<List<Double>> elementsParamsCopy = new ArrayList<>();
        elementsParams.forEach(elementsParam -> elementsParamsCopy.add(new ArrayList<>()));

        IntStream.range(0, elementsParams.get(0).size()).forEach(i -> {
                    if (elementsParams.stream().allMatch(elementsParam -> elementsParam.get(i) != null)) {
                        IntStream.range(0, elementsParamsCopy.size()).forEach(j -> elementsParamsCopy.get(j).add(elementsParams.get(j).get(i)));
                    }
                }
        );

        List<Double> minElementsParams = new ArrayList<>(elementsParamsCopy.get(0));

        elementsParamsCopy.forEach(
                elementsParam -> {
                    for (int i = 0; i < elementsParam.size(); i++) {
                        minElementsParams.set(i, (Math.min(
                                minElementsParams.get(i),
                                elementsParam.get(i))));
                    }
                }
        );
        return minElementsParams;
    }

    public double getElementsCardinality(List<LabeledFuzzySet> fuzzySets, List<String> summarizerNames) {
        return getSumOfAll(fuzzySets, summarizerNames).stream().mapToDouble(Double::doubleValue).sum();
    }

    public double getElementsSupportCardinality(List<LabeledFuzzySet> fuzzySets, List<String> summarizerNames) {
        return getSumOfAll(fuzzySets, summarizerNames).stream().mapToDouble(d -> d > 0 ? 1 : 0).sum();
    }
}
