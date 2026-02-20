package ru.mathtest.introduction.cell.symbol;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;

import java.math.BigDecimal;

public interface Symbol extends Cell {
    BigDecimal getWin(BigDecimal bet);
    SymbolType getSymbolType();

    @Override
    default CellType getCellType() {
        return CellType.SYMBOL;
    }
}
