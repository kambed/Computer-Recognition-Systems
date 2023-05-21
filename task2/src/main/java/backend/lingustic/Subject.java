package backend.lingustic;

import backend.model.Stats;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        put("Wysokość toru n.p.m.", () -> elements.stream().map(Stats::getTrackLatitude).toList());
        put("Klimat toru", () -> elements.stream().map(Stats::getTrackAltitude).toList());
    }};

    public Subject(String name, List<Stats> elements) {
        this.name = name;
        this.elements = elements;
    }

    public double getElementsCardinality(List<LabeledFuzzySet> summarizers, List<String> summarizerNames) {
        List<List<Double>> elementsParams = new ArrayList<>();
        IntStream.range(0, summarizerNames.size()).forEach(
                i -> elementsParams.add(namesToElements.get(summarizerNames.get(i)).get().stream().map(
                        element -> summarizers.get(i).getFunction().getValue(element)
                ).toList())
        );
        List<Double> minElementsParams = new ArrayList<>(elementsParams.get(0));
        elementsParams.forEach(
                elementsParam -> {
                    for (int i = 0; i < elementsParam.size(); i++) {
                        minElementsParams.set(i, Math.min(minElementsParams.get(i), elementsParam.get(i)));
                    }
                }
        );
        return minElementsParams.stream().mapToDouble(Double::doubleValue).sum();
    }
}
