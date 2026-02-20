package ru.mathtest.introduction.generator;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneratorRegistry {
    private Map<CellType, CellGenerator> generators = new HashMap<>();

    public GeneratorRegistry(List<CellGenerator<?>> generatorsList) {
        generatorsList.forEach(generator -> generators.put(generator.getGeneratedCellType(), generator));
    }

    public CellGenerator<Cell> getGenerator(CellType cellType) {
        return generators.get(cellType);
    }
}
