package backend.repository;

import backend.model.Stats;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatsRepositoryTest {

    @Test
    void getStats() {
        List<Stats> stats = StatsRepository.getStats();
        assertEquals(11073, stats.size());
    }
}