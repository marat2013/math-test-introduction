package ru.mathtest.introduction.cell.prize;

import java.math.BigDecimal;

public class MinorPrize implements Prize {
    @Override
    public BigDecimal getAward() {
        return BigDecimal.valueOf(15);
    }

    @Override
    public PrizeType getPrizeType() {
        return PrizeType.MINOR;
    }

    @Override
    public String toString() {
        return "MinorPrize";
    }
}
