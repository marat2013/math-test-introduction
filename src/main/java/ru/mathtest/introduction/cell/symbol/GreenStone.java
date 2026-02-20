package ru.mathtest.introduction.cell.symbol;

import java.math.BigDecimal;

public class GreenStone implements Symbol {
    @Override
    public BigDecimal getWin(BigDecimal bet) {
        return bet.multiply(BigDecimal.valueOf(0.2));
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.GREEN_STONE;
    }

    @Override
    public String toString() {
        return "GreenStone";
    }
}
