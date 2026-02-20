package ru.mathtest.introduction.unit.cell.prize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.prize.MiniPrize;
import ru.mathtest.introduction.cell.prize.PrizeType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiniPrizeTest {
    @Test
    public void getAward_shouldReturn300() {
        MiniPrize prize = new MiniPrize();
        assertEquals(BigDecimal.valueOf(7.5), prize.getAward());
    }

    @Test
    public void getPrizeType_shouldReturnGRAND() {
        MiniPrize prize = new MiniPrize();
        Assertions.assertEquals(PrizeType.MINI, prize.getPrizeType());
    }
}
