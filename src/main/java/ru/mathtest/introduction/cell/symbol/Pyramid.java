package ru.mathtest.introduction.cell.symbol;

import java.math.BigDecimal;

public class Pyramid implements Symbol {
    @Override
    public BigDecimal getWin(BigDecimal bet) {
        return bet.multiply(BigDecimal.valueOf(3));
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.PYRAMID;
    }

    @Override
    public String toString() {
        return "Pyramid";
    }
}
