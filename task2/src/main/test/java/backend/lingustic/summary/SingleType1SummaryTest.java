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

class SingleType1SummaryTest {

    @Test
    void calculateT1Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 5.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 4.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2), new Subject("Wyniki", stats), List.of(PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0)), List.of("Liczba przejechanych okrążeń"), List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(1.0, s.getT1());
    }

    @Test
    void calculateT1TestWithAndOperator() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 0.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 0.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        LabeledFuzzySet fuzzySet1 = PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0);
        LabeledFuzzySet fuzzySet2 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0);

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2),
                new Subject("wyników", stats),
                List.of(fuzzySet1, fuzzySet2),
                List.of("Liczba przejechanych okrążeń", "Liczba zdobytych punktów"),
                List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals("Około połowa wyników jest/ma Incydent w wyścigu i Nieskuteczny kierowca", s.getSummaryString());
        assertEquals(0.456, s.getT1(), 0.001);
    }

    @Test
    void calculateT2Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 5.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 4.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2), new Subject("Wyniki", stats), List.of(PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0)), List.of("Liczba przejechanych okrążeń"), List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(0.56, s.getT2(), 0.01);
    }

    @Test
    void calculateT2TestWithAndOperator() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 0.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 0.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        LabeledFuzzySet fuzzySet1 = PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0);
        LabeledFuzzySet fuzzySet2 = PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0);

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2),
                new Subject("wyników", stats),
                List.of(fuzzySet1, fuzzySet2),
                List.of("Liczba przejechanych okrążeń", "Liczba zdobytych punktów"),
                List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals("Około połowa wyników jest/ma Incydent w wyścigu i Nieskuteczny kierowca", s.getSummaryString());
        assertEquals(0.71, s.getT2(), 0.01);
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

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2),
                new Subject("wyników", stats),
                List.of(fuzzySet1, fuzzySet2),
                List.of("Liczba przejechanych okrążeń", "Liczba zdobytych punktów"),
                List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals("Około połowa wyników jest/ma Incydent w wyścigu i Nieskuteczny kierowca", s.getSummaryString());
        assertEquals(0.5, s.getT5(), 0.001);
        assertEquals(1.0, s.getT11(), 0.001);
    }

    @Test
    void calculateT6Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 5.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 4.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2), new Subject("Wyniki", stats), List.of(PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0)), List.of("Liczba przejechanych okrążeń"), List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(0.0, s.getT6(), 0.01);
    }

    @Test
    void calculateT7Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 5.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 4.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(1), new Subject("Wyniki", stats), List.of(PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0)), List.of("Liczba przejechanych okrążeń"), List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(0.8, s.getT7(), 0.01);
    }

    @Test
    void calculateT8Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 5.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 4.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(1), new Subject("Wyniki", stats), List.of(PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0)), List.of("Liczba zdobytych punktów"), List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(0.90, s.getT8(), 0.01);
    }

    @Test
    void calculateT9Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 5.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 4.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(2), new Subject("Wyniki", stats), List.of(PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0)), List.of("Liczba przejechanych okrążeń"), List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(1.0, s.getT9(), 0.01);
    }

    @Test
    void calculateT10Test() {
        List<Stats> stats = new ArrayList<>();
        stats.add(new Stats(1, "Hamilton Lewis", "McLaren", "Albert Park Grand Prix Circuit", "2008", 1, 1, 58, 10.0, 23.1869, 71.43, 87.452, 218.3, null, 85.187, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(2, "Heidfeld Nick", "BMW Sauber", "Albert Park Grand Prix Circuit", "2008", 2, 5, 58, 8.0, 30.8501, 100.00, 87.739, 217.586, null, 85.518, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(3, "Rosberg Nico", "Williams", "Albert Park Grand Prix Circuit", "2008", 3, 7, 30, 6.0, 22.7187, 66.67, 88.09, 216.719, null, 86.059, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(4, "Alonso Fernando", "Renault", "Albert Park Grand Prix Circuit", "2008", 4, 11, 20, 5.0, 26.6311, 100.00, 88.603, 215.464, null, 86.188, 75, 4.5, -37.8497, 10.0));
        stats.add(new Stats(5, "Kovalainen Heikki", "McLaren", "Albert Park Grand Prix Circuit", "2008", 5, 3, 1, 4.0, 26.4066, 28.57, 87.418, 218.385, null, 85.452, 75, 4.5, -37.8497, 10.0));

        Summary s = new SingleType1Summary(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(1), new Subject("Wyniki", stats), List.of(PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0)), List.of("Liczba zdobytych punktów"), List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
        assertEquals(1.0, s.getT10(), 0.01);
    }
}