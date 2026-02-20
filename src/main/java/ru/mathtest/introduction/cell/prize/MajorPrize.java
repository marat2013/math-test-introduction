package ru.mathtest.introduction.cell.prize;

import java.math.BigDecimal;

public class MajorPrize implements Prize {
    @Override
    public BigDecimal getAward() {
        return BigDecimal.valueOf(45);
    }

    @Override
    public PrizeType getPrizeType() {
        return PrizeType.MAJOR;
    }

    @Override
    public String toString() {
        return "MajorPrize";
    }
}
