package ru.mathtest.introduction.cell.prize;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;

import java.math.BigDecimal;

public interface Prize extends Cell {
    BigDecimal getAward();
    PrizeType getPrizeType();

    @Override
    default CellType getCellType() {
        return CellType.PRIZE;
    }
}
