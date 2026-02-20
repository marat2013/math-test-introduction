package ru.mathtest.introduction.generator;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.symbol.Symbol;
import ru.mathtest.introduction.cell.symbol.SymbolRegistry;
import ru.mathtest.introduction.cell.symbol.SymbolType;
import ru.mathtest.introduction.dto.CellDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SymbolGenerator implements CellGenerator<Symbol> {

    private final SymbolRegistry symbolRegistry;

    private Map<SymbolType, Float> baseProbabilities = new HashMap<>();
    private final Random random = new Random();

    private static final float COMBO_BOOST = 5.0f;

    public SymbolGenerator(SymbolRegistry symbolRegistry) {
        this.symbolRegistry = symbolRegistry;
        baseProbabilities.put(SymbolType.GREEN_STONE, 0.15f);
        baseProbabilities.put(SymbolType.PURPLE_STONE, 0.15f);
        baseProbabilities.put(SymbolType.ORANGE_STONE, 0.15f);
        baseProbabilities.put(SymbolType.RED_STONE, 0.15f);
        baseProbabilities.put(SymbolType.JUG, 0.1f);
        baseProbabilities.put(SymbolType.EYE, 0.1f);
        baseProbabilities.put(SymbolType.PYRAMID, 0.05f);
        baseProbabilities.put(SymbolType.WINGS, 0.05f);
        baseProbabilities.put(SymbolType.WILD, 0.1f);
    }

    @Override
    public Symbol generate(Cell[][] prevCells, Cell[][] currentCells, CellDto cellDto) {
        int row = cellDto.getPositionDto().getRowIndex();
        int col = cellDto.getPositionDto().getColumnIndex();
        Map<SymbolType, Float> weights = new HashMap<>(baseProbabilities);

        boostHorizontal(weights, currentCells, row, col);
        boostVertical(weights, currentCells, row, col);
        boostDiagonal(weights, currentCells, row, col);

        return symbolRegistry.getSymbol(getWeightedRandomSymbolType(weights));
    }

    @Override
    public CellType getGeneratedCellType() {
        return CellType.SYMBOL;
    }

    private SymbolType getWeightedRandomSymbolType(Map<SymbolType, Float> weights) {
        float total = 0f;
        for (float w : weights.values()) {
            total += w;
        }

        float r = random.nextFloat() * total;
        float cumulative = 0f;

        for (Map.Entry<SymbolType, Float> entry : weights.entrySet()) {
            cumulative += entry.getValue();
            if (r < cumulative) {
                return entry.getKey();
            }
        }
        return SymbolType.GREEN_STONE;
    }

    private void boostHorizontal(Map<SymbolType, Float> weights, Cell[][] currentCells, int row, int col) {
        if (col >= 2) {
            SymbolType s1 = getSymbolType(currentCells[row][col - 1]);
            SymbolType s2 = getSymbolType(currentCells[row][col - 2]);
            if (s1 != null && s1 == s2) {
                weights.computeIfPresent(s1, (k, v) -> v * COMBO_BOOST);
            }
        }
    }

    private void boostVertical(Map<SymbolType, Float> weights, Cell[][] currentCells, int row, int col) {
        if (row >= 2) {
            SymbolType s1 = getSymbolType(currentCells[row - 1][col]);
            SymbolType s2 = getSymbolType(currentCells[row - 2][col]);
            if (s1 != null && s1 == s2) {
                weights.computeIfPresent(s1, (k, v) -> v * COMBO_BOOST);
            }
        }
    }

    private void boostDiagonal(Map<SymbolType, Float> weights, Cell[][] currentCells, int row, int col) {
        int rows = currentCells.length;
        int cols = currentCells[0].length;

        if (row >= 2 && col >= 2) {
            SymbolType s1 = getSymbolType(currentCells[row - 1][col - 1]);
            SymbolType s2 = getSymbolType(currentCells[row - 2][col - 2]);
            if (s1 != null && s1 == s2) {
                weights.computeIfPresent(s1, (k, v) -> v * COMBO_BOOST);
            }
        }
        if (row >= 2 && col <= cols - 3) {
            SymbolType s1 = getSymbolType(currentCells[row - 1][col + 1]);
            SymbolType s2 = getSymbolType(currentCells[row - 2][col + 2]);
            if (s1 != null && s1 == s2) {
                weights.computeIfPresent(s1, (k, v) -> v * COMBO_BOOST);
            }
        }
    }

    private SymbolType getSymbolType(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.SYMBOL) {
            return ((Symbol) cell).getSymbolType();
        }
        return null;
    }

}
