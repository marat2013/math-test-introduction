package ru.mathtest.introduction.unit.generator;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.dto.CellDto;
import ru.mathtest.introduction.generator.CellTypeDeterminator;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CellTypeDeterminatorTest {

    @Test
    void determinate_shouldGenerateAllTypesWithApproximateProbabilities() {

        CellTypeDeterminator determinator = new CellTypeDeterminator();

        int iterations = 200_000;

        Map<CellType, Integer> counters = new EnumMap<>(CellType.class);

        for (int i = 0; i < iterations; i++) {
            CellType result = determinator.determinate(
                    new Cell[1][1],
                    new Cell[1][1],
                    CellDto.builder().build()
            );

            counters.merge(result, 1, Integer::sum);
        }

        double bonusRatio = counters.getOrDefault(CellType.BONUS, 0) / (double) iterations;
        double symbolRatio = counters.getOrDefault(CellType.SYMBOL, 0) / (double) iterations;
        double prizeRatio = counters.getOrDefault(CellType.PRIZE, 0) / (double) iterations;

        assertTrue(bonusRatio > 0.005 && bonusRatio < 0.15);
        assertTrue(symbolRatio > 0.85 && symbolRatio < 0.95);
        assertTrue(prizeRatio > 0.07 && prizeRatio < 0.13);
    }

    @Test
    void determinate_shouldAlwaysReturnNonNull() {

        CellTypeDeterminator determinator = new CellTypeDeterminator();

        for (int i = 0; i < 10_000; i++) {
            CellType result = determinator.determinate(
                    new Cell[1][1],
                    new Cell[1][1],
                    CellDto.builder().build()
            );

            assertNotNull(result);
        }
    }
}