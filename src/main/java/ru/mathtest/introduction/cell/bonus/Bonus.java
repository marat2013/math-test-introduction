package ru.mathtest.introduction.cell.bonus;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;

import java.math.BigDecimal;

public interface Bonus extends Cell {
    boolean isRaBonus();
    BigDecimal getPrize();
    @Override
    default CellType getCellType() {
        return CellType.BONUS;
    };
}
