package ru.mathtest.introduction.cell.symbol;

import java.math.BigDecimal;

public class Eye implements Symbol {
    @Override
    public BigDecimal getWin(BigDecimal bet) {
        return bet.multiply(BigDecimal.valueOf(2));
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.EYE;
    }

    @Override
    public String toString() {
        return "Eye";
    }
}
