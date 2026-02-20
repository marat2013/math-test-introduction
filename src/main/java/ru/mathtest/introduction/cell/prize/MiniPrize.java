package ru.mathtest.introduction.cell.prize;

import java.math.BigDecimal;

public class MiniPrize implements Prize {
    @Override
    public BigDecimal getAward() {
        return BigDecimal.valueOf(7.5);
    }

    @Override
    public PrizeType getPrizeType() {
        return PrizeType.MINI;
    }

    @Override
    public String toString() {
        return "MiniPrize";
    }
}
