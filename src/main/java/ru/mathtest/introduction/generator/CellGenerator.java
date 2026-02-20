package ru.mathtest.introduction.generator;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.dto.CellDto;

public interface CellGenerator<T> {
    T generate(Cell[][] prevCells, Cell[][] currentCells, CellDto cellDto);
    CellType getGeneratedCellType();
}
