package ru.mathtest.introduction.cell.symbol;

import java.math.BigDecimal;

public class Wings implements Symbol {
    @Override
    public BigDecimal getWin(BigDecimal bet) {
        return bet.multiply(BigDecimal.valueOf(5));
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.WINGS;
    }

    @Override
    public String toString() {
        return "Wings";
    }
}
