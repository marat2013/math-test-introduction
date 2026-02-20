package ru.mathtest.introduction.cell.symbol;

import java.math.BigDecimal;

public class Wild implements Symbol {
    @Override
    public BigDecimal getWin(BigDecimal bet) {
        return bet.multiply(BigDecimal.valueOf(10));
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.WILD;
    }

    @Override
    public String toString() {
        return "Wild";
    }
}
