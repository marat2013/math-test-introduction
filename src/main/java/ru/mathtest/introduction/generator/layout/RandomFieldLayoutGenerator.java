package ru.mathtest.introduction.generator.layout;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.dto.CellDto;
import ru.mathtest.introduction.generator.CellTypeDeterminator;
import ru.mathtest.introduction.generator.GeneratorRegistry;

public class RandomFieldLayoutGenerator extends AbstractFieldLayoutGenerator {

    private final GeneratorRegistry generatorRegistry;
    private final CellTypeDeterminator cellTypeDeterminator;

    public RandomFieldLayoutGenerator(GeneratorRegistry generatorRegistry,
                                      CellTypeDeterminator cellTypeDeterminator) {
        this.generatorRegistry = generatorRegistry;
        this.cellTypeDeterminator = cellTypeDeterminator;
    }

    @Override
    public Cell generateCell(Cell[][] prevCells, Cell[][] currentCells, CellDto dto) {
        CellType cellType = cellTypeDeterminator.determinate(prevCells, currentCells, dto);
        return generatorRegistry.getGenerator(cellType).generate(prevCells, currentCells, dto);
    }
}
