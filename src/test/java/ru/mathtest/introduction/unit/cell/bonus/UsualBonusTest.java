package ru.mathtest.introduction.unit.cell.bonus;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.bonus.UsualBonus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class UsualBonusTest {

    @Test
    public void isRaBonus_shouldReturnFalse() {
        UsualBonus bonus = new UsualBonus(BigDecimal.valueOf(10));
        assertFalse(bonus.isRaBonus());
    }

    @Test
    public void prize_shouldBeInExpectedRange() {
        BigDecimal bet = BigDecimal.valueOf(10);
        UsualBonus bonus = new UsualBonus(bet);

        BigDecimal min = BigDecimal.valueOf(0.3);
        BigDecimal max = BigDecimal.valueOf(4.5);

        assertTrue(bonus.getPrize().compareTo(min) >= 0 && bonus.getPrize().compareTo(max) <= 0,
                "Prize должен быть в допустимом диапазоне");
    }

    @Test
    public void countPrize_shouldReturnBigDecimal() {
        BigDecimal bet = BigDecimal.valueOf(10);
        BigDecimal prize = UsualBonus.countPrize(bet);
        assertNotNull(prize);
        assertTrue(prize.compareTo(BigDecimal.ZERO) > 0);
    }
}