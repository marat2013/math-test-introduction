package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.GreenStone;
import ru.mathtest.introduction.cell.symbol.SymbolType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class GreenStoneTest {

    @Test
    public void getWin_shouldReturnTwentyPercentOfBet() {
        GreenStone greenStone = new GreenStone();
        BigDecimal bet = BigDecimal.valueOf(100);

        BigDecimal win = greenStone.getWin(bet);

        assertEquals(BigDecimal.valueOf(20.0), win);
    }

    @Test
    public void getSymbolType_shouldReturnGreenStone() {
        GreenStone greenStone = new GreenStone();

        Assertions.assertEquals(SymbolType.GREEN_STONE, greenStone.getSymbolType());
    }
}