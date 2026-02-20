package ru.mathtest.introduction.cell.bonus;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class UsualBonus implements Bonus {
    private static final BigDecimal MIN_PRIZE = BigDecimal.valueOf(0.3);
    private static final BigDecimal RANGE_TO_MAX_PRIZE = BigDecimal.valueOf(4.2);
    private static final double BET_NORMALIZER = 5.0;

    private final BigDecimal prize;

    public UsualBonus(BigDecimal bet) {
        this.prize = countPrize(bet);
    }

    @Override
    public boolean isRaBonus() {
        return false;
    }

    public static BigDecimal countPrize(BigDecimal bet) {
        double baseLevel = 3.0;
        double weight = Math.min(bet.doubleValue() / BET_NORMALIZER, baseLevel);
        double randomNumber = ThreadLocalRandom.current().nextDouble();

        double power = 1.0 / (1.0 + weight);
        double proportion = Math.pow(randomNumber, power);
        return MIN_PRIZE.add(
                RANGE_TO_MAX_PRIZE.multiply(BigDecimal.valueOf(proportion))
        );
    }

    @Override
    public String toString() {
        return "UsualBonus";
    }

    @Override
    public BigDecimal getPrize() {
        return prize;
    }
}
