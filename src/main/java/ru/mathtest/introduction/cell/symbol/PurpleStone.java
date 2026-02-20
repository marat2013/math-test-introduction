package ru.mathtest.introduction.cell.symbol;

import java.math.BigDecimal;

public class PurpleStone implements Symbol {
    @Override
    public BigDecimal getWin(BigDecimal bet) {
        return bet.multiply(BigDecimal.valueOf(0.4));
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.PURPLE_STONE;
    }

    @Override
    public String toString() {
        return "PurpleStone";
    }
}
