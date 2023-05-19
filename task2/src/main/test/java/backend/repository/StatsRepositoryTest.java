package backend.repository;

import backend.model.Stats;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatsRepositoryTest {

    @Test
    void getStats() {
        StatsRepository statsRepository = new StatsRepository();
        List<Stats> stats = statsRepository.getStats();
        assertEquals(11073, stats.size());
    }
}