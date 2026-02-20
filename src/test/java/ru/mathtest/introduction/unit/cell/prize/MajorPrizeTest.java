package ru.mathtest.introduction.unit.cell.prize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.prize.MajorPrize;
import ru.mathtest.introduction.cell.prize.PrizeType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MajorPrizeTest {
    @Test
    public void getAward_shouldReturn300() {
        MajorPrize prize = new MajorPrize();
        assertEquals(BigDecimal.valueOf(45), prize.getAward());
    }

    @Test
    public void getPrizeType_shouldReturnGRAND() {
        MajorPrize prize = new MajorPrize();
        Assertions.assertEquals(PrizeType.MAJOR, prize.getPrizeType());
    }
}
