package ru.mathtest.introduction.generator;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.dto.CellDto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class CellTypeDeterminator {

    private Map<CellType, Float> probabilities = new LinkedHashMap<>();

    private final Random random = new Random();

    public CellTypeDeterminator() {
        probabilities.put(CellType.BONUS, 0.01f);
        probabilities.put(CellType.SYMBOL, 0.89f);
        probabilities.put(CellType.PRIZE, 0.1f);
    }

    public CellType determinate(Cell[][] prevCells, Cell[][] currentCells, CellDto dto) {
        float r = random.nextFloat();
        float cumulative = 0f;

        for (Map.Entry<CellType, Float> entry : probabilities.entrySet()) {
            cumulative += entry.getValue();
            if (r < cumulative) {
                return entry.getKey();
            }
        }
        return CellType.SYMBOL;
    }

}
