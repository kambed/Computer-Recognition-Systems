package backend.knn.dto;

import java.util.List;
import java.util.Map;

public record StatisticsDto(
        Map<String, Double> statistics,
        Map<String, Map<String, Integer>> confusionMatrix,
        List<String> classes
) {
}
