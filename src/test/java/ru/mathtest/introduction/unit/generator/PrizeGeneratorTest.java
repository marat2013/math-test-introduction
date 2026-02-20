package ru.mathtest.introduction.unit.generator;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.prize.Prize;
import ru.mathtest.introduction.cell.prize.PrizeRegistry;
import ru.mathtest.introduction.cell.prize.PrizeType;
import ru.mathtest.introduction.dto.CellDto;
import ru.mathtest.introduction.generator.PrizeGenerator;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PrizeGeneratorTest {

    static class StubPrize implements Prize {
        private final PrizeType type;

        StubPrize(PrizeType type) {
            this.type = type;
        }

        public PrizeType getType() {
            return type;
        }

        @Override
        public BigDecimal getAward() {
            return null;
        }

        @Override
        public PrizeType getPrizeType() {
            return null;
        }
    }

    static class StubPrizeRegistry extends PrizeRegistry {

        private final Map<PrizeType, Prize> prizes = new EnumMap<>(PrizeType.class);

        StubPrizeRegistry() {
            for (PrizeType type : PrizeType.values()) {
                prizes.put(type, new StubPrize(type));
            }
        }

        @Override
        public Prize getPrize(PrizeType type) {
            return prizes.get(type);
        }
    }

    @Test
    void generate_shouldRespectApproximateProbabilities() {

        StubPrizeRegistry registry = new StubPrizeRegistry();
        PrizeGenerator generator = new PrizeGenerator(registry);

        int iterations = 200_000;

        Map<PrizeType, Integer> counters = new EnumMap<>(PrizeType.class);

        for (int i = 0; i < iterations; i++) {
            Prize prize = generator.generate(
                    new Cell[1][1],
                    new Cell[1][1],
                    CellDto.builder().build()
            );

            PrizeType type = ((StubPrize) prize).getType();
            counters.merge(type, 1, Integer::sum);
        }

        double miniRatio  = counters.getOrDefault(PrizeType.MINI, 0)  / (double) iterations;
        double minorRatio = counters.getOrDefault(PrizeType.MINOR, 0) / (double) iterations;
        double majorRatio = counters.getOrDefault(PrizeType.MAJOR, 0) / (double) iterations;
        double grandRatio = counters.getOrDefault(PrizeType.GRAND, 0) / (double) iterations;

        assertTrue(miniRatio  > 0.45 && miniRatio  < 0.55);
        assertTrue(minorRatio > 0.25 && minorRatio < 0.35);
        assertTrue(majorRatio > 0.12 && majorRatio < 0.18);
        assertTrue(grandRatio > 0.03 && grandRatio < 0.07);
    }

    @Test
    void getGeneratedCellType_shouldReturnPrize() {
        PrizeGenerator generator = new PrizeGenerator(new StubPrizeRegistry());
        assertEquals(CellType.PRIZE, generator.getGeneratedCellType());
    }
}