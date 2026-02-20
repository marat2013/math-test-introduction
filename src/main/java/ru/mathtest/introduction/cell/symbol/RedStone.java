package ru.mathtest.introduction.cell.symbol;

import java.math.BigDecimal;

public class RedStone implements Symbol {
    @Override
    public BigDecimal getWin(BigDecimal bet) {
        return bet.multiply(BigDecimal.valueOf(1));
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.RED_STONE;
    }

    @Override
    public String toString() {
        return "RedStone";
    }
}
