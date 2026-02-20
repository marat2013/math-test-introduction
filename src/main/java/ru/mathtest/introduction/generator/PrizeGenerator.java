package ru.mathtest.introduction.generator;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.prize.Prize;
import ru.mathtest.introduction.cell.prize.PrizeRegistry;
import ru.mathtest.introduction.cell.prize.PrizeType;
import ru.mathtest.introduction.dto.CellDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PrizeGenerator implements CellGenerator<Prize> {

    private final PrizeRegistry prizeRegistry;

    private Map<PrizeType, Float> probabilities = new HashMap<>();
    private final Random random = new Random();

    public PrizeGenerator(PrizeRegistry prizeRegistry) {
        this.prizeRegistry = prizeRegistry;
        probabilities.put(PrizeType.MINI, 0.5f);
        probabilities.put(PrizeType.MINOR, 0.3f);
        probabilities.put(PrizeType.MAJOR, 0.15f);
        probabilities.put(PrizeType.GRAND, 0.05f);
    }

    @Override
    public Prize generate(Cell[][] prevCells, Cell[][] currentCells, CellDto cellDto) {
        float r = random.nextFloat();
        float cumulative = 0f;

        for (Map.Entry<PrizeType, Float> entry : probabilities.entrySet()) {
            cumulative += entry.getValue();
            if (r < cumulative) {
                return prizeRegistry.getPrize(entry.getKey());
            }
        }
        return prizeRegistry.getPrize(PrizeType.MINI);
    }

    @Override
    public CellType getGeneratedCellType() {
        return CellType.PRIZE;
    }
}
