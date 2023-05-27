package backend.lingustic.summary;

import backend.lingustic.Subject;
import backend.lingustic.predefined.PredefinedQuantifiers;
import backend.lingustic.predefined.PredefinedVariables;
import backend.model.Stats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiType4SummaryTest {

    private List<Subject> subjects = new ArrayList<>();

    @BeforeEach
    void setUp() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "Mercedes", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 10, 2.5, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "Mercedes", "Albert Park Grand Prix Circuit", "2008", 5, 3, 30, 20.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        List<String> topTeamsNames = List.of("Ferrari", "Mercedes", "Red Bull");
        List<Stats> topTeams = stats.stream().filter(s -> topTeamsNames.contains(s.getTeam())).toList();
        List<Stats> otherTeams = stats.stream().filter(s -> !topTeamsNames.contains(s.getTeam())).toList();
        subjects = new ArrayList<>(List.of(
                new Subject("wyników czołowych zespołów", topTeams),
                new Subject("wyników słabszych zespołów", otherTeams)
        ));
    }

    @Test
    void evaluateT1Test() {
        Summary s = new MultiType4Summary(subjects,
                List.of(PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0)),
                List.of("Liczba zdobytych punktów")
        );
        assertEquals(0.0, s.getT1());
    }

}