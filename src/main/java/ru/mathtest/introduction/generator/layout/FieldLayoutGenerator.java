package ru.mathtest.introduction.generator.layout;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.dto.SpinContext;

public interface FieldLayoutGenerator {
    Cell[][] fill(Cell[][] prevCells, SpinContext ctx);
}
