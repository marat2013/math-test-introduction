package ru.mathtest.introduction.cell.symbol;

import java.math.BigDecimal;

public class OrangeStone implements Symbol {
    @Override
    public BigDecimal getWin(BigDecimal bet) {
        return bet.multiply(BigDecimal.valueOf(0.6));
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.ORANGE_STONE;
    }

    @Override
    public String toString() {
        return "OrangeStone";
    }
}
