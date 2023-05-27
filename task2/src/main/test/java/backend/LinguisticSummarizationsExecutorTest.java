package backend;

import backend.lingustic.predefined.PredefinedQuantifiers;
import backend.lingustic.predefined.PredefinedVariables;
import backend.lingustic.summary.Summary;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinguisticSummarizationsExecutorTest {

    @Test
    void getAllSummaries() {
        List<Summary> summaries = LinguisticSummarizationsExecutor.getSummaries(
                List.of(PredefinedQuantifiers.getPredefinedRelativeQuantifiers().get(0)),
                List.of(PredefinedVariables.getPredefinedVariables().get(0).getLabeledFuzzySets().get(0),
                        PredefinedVariables.getPredefinedVariables().get(1).getLabeledFuzzySets().get(0),
                        PredefinedVariables.getPredefinedVariables().get(2).getLabeledFuzzySets().get(0)),
                List.of("Liczba przejechanych okrążeń", "Liczba zdobytych punktów", "Wiek kierowcy"),
                List.of(0.3, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07)
        );
        assertEquals(19, summaries.size());
    }
}