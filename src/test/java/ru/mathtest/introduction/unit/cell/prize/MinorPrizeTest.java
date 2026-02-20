package ru.mathtest.introduction.unit.cell.prize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.prize.MinorPrize;
import ru.mathtest.introduction.cell.prize.PrizeType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinorPrizeTest {
    @Test
    public void getAward_shouldReturn300() {
        MinorPrize prize = new MinorPrize();
        assertEquals(BigDecimal.valueOf(15), prize.getAward());
    }

    @Test
    public void getPrizeType_shouldReturnGRAND() {
        MinorPrize prize = new MinorPrize();
        Assertions.assertEquals(PrizeType.MINOR, prize.getPrizeType());
    }
}
