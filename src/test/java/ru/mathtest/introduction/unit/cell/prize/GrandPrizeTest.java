package ru.mathtest.introduction.unit.cell.prize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.prize.GrandPrize;
import ru.mathtest.introduction.cell.prize.PrizeType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class GrandPrizeTest {

    @Test
    public void getAward_shouldReturn300() {
        GrandPrize prize = new GrandPrize();
        assertEquals(BigDecimal.valueOf(300), prize.getAward());
    }

    @Test
    public void getPrizeType_shouldReturnGRAND() {
        GrandPrize prize = new GrandPrize();
        Assertions.assertEquals(PrizeType.GRAND, prize.getPrizeType());
    }
}
