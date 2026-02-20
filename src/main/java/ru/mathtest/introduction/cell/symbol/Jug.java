package ru.mathtest.introduction.cell.symbol;

import java.math.BigDecimal;

public class Jug implements Symbol {
    @Override
    public BigDecimal getWin(BigDecimal bet) {
        return bet.multiply(BigDecimal.valueOf(1.6));
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.JUG;
    }

    @Override
    public String toString() {
        return "Jug";
    }
}
