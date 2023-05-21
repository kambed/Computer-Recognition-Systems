package backend.lingustic.summary;

import backend.lingustic.LabeledFuzzySet;
import backend.lingustic.Subject;
import backend.lingustic.predefined.PredefinedQuantifiers;
import backend.lingustic.predefined.PredefinedVariables;
import backend.model.Stats;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SingleType2SummaryTest {

    @Test
    void calculateT1Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 0.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 0.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        LabeledFuzzySet fuzzySet1 = PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0);
        LabeledFuzzySet fuzzySet2 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0);

        Summary s = new SingleType2Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2),
                new Subject("wyników", stats),
                List.of(fuzzySet1),
                List.of("Liczba przejechanych okrążeń"),
                List.of(fuzzySet2),
                List.of("Liczba zdobytych punktów"),
                List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(0.0, s.getT1(), 0.001);
    }

    @Test
    void calculateT2Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 0.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 0.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        LabeledFuzzySet fuzzySet1 = PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0);
        LabeledFuzzySet fuzzySet2 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0);

        Summary s = new SingleType2Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2),
                new Subject("wyników", stats),
                List.of(fuzzySet1),
                List.of("Liczba przejechanych okrążeń"),
                List.of(fuzzySet2),
                List.of("Liczba zdobytych punktów"),
                List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(0.81, s.getT2(), 0.01);
    }

    @Test
    void calculateT5andT11Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 0.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 0.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        LabeledFuzzySet fuzzySet1 = PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0);
        LabeledFuzzySet fuzzySet2 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0);
        LabeledFuzzySet fuzzySet3 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(1);
        LabeledFuzzySet fuzzySet4 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(2);
        LabeledFuzzySet fuzzySet5 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(3);

        Summary s = new SingleType2Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2),
                new Subject("wyników", stats),
                List.of(fuzzySet1, fuzzySet2),
                List.of("Liczba przejechanych okrążeń", "Liczba zdobytych punktów"),
                List.of(fuzzySet3, fuzzySet4, fuzzySet5),
                List.of("Liczba zdobytych punktów", "Liczba zdobytych punktów", "Liczba zdobytych punktów"),
                List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(0.25, s.getT5(), 0.001);
        assertEquals(0.5, s.getT11(), 0.001);
    }

    @Test
    void calculateT6Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 0.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 0.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        LabeledFuzzySet fuzzySet1 = PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0);
        LabeledFuzzySet fuzzySet2 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0);

        Summary s = new SingleType2Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(3),
                new Subject("wyników", stats),
                List.of(fuzzySet2),
                List.of("Liczba zdobytych punktów"),
                List.of(fuzzySet1),
                List.of("Liczba przejechanych okrążeń"),
                List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(0.6, s.getT6(), 0.01);
    }

    @Test
    void calculateT9Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 0.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 0.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        LabeledFuzzySet fuzzySet1 = PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0);
        LabeledFuzzySet fuzzySet2 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0);

        Summary s = new SingleType2Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2),
                new Subject("wyników", stats),
                List.of(fuzzySet2),
                List.of("Liczba zdobytych punktów"),
                List.of(fuzzySet1),
                List.of("Liczba przejechanych okrążeń"),
                List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(0.81, s.getT9(), 0.01);
    }
}