package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.OrangeStone;
import ru.mathtest.introduction.cell.symbol.SymbolType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class OrangeStoneTest {

    @Test
    public void getWin_shouldReturnSixtyPercentOfBet() {
        OrangeStone orangeStone = new OrangeStone();
        BigDecimal bet = BigDecimal.valueOf(100);

        BigDecimal win = orangeStone.getWin(bet);

        assertEquals(BigDecimal.valueOf(60.0), win);
    }

    @Test
    public void getSymbolType_shouldReturnOrangeStone() {
        OrangeStone orangeStone = new OrangeStone();

        Assertions.assertEquals(SymbolType.ORANGE_STONE, orangeStone.getSymbolType());
    }
}