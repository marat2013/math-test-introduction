package ru.mathtest.introduction.cell.prize;

import java.math.BigDecimal;

public class GrandPrize implements Prize {
    @Override
    public BigDecimal getAward() {
        return BigDecimal.valueOf(300);
    }

    @Override
    public PrizeType getPrizeType() {
        return PrizeType.GRAND;
    }

    @Override
    public String toString() {
        return "GrandPrize";
    }
}
