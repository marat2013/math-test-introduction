package ru.mathtest.introduction.unit.cell.bonus;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.bonus.RaBonus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RaBonusTest {

    @Test
    public void isRaBonus_shouldReturnTrue() {
        RaBonus raBonus = new RaBonus();
        assertTrue(raBonus.isRaBonus(), "RaBonus должен возвращать true для isRaBonus()");
    }

    @Test
    public void initialCollectedWin_shouldBeZero() {
        RaBonus raBonus = new RaBonus();
        assertEquals(BigDecimal.ZERO, raBonus.getCollectedWin(), "Начальный collectedWin должен быть 0");
    }

    @Test
    public void collect_shouldIncreaseCollectedWin() {
        RaBonus raBonus = new RaBonus();

        raBonus.collect(BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(100), raBonus.getCollectedWin(), "collectedWin должен увеличиться после collect");
    }
}
