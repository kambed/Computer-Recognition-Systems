package backend.statistics;

import javafx.util.Pair;

import java.util.List;

public class StatisticsFactory {
    public static Statistics createStatistics(List<Pair<String, String>> expectedToReceivedValues) {
        return new Statistics(expectedToReceivedValues);
    }
}
