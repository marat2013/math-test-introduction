package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.PurpleStone;
import ru.mathtest.introduction.cell.symbol.SymbolType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PurpleStoneTest {

    @Test
    public void getWin_shouldReturnFortyPercentOfBet() {
        PurpleStone purpleStone = new PurpleStone();
        BigDecimal bet = BigDecimal.valueOf(100);

        BigDecimal win = purpleStone.getWin(bet);

        assertEquals(BigDecimal.valueOf(40.0), win);
    }

    @Test
    public void getSymbolType_shouldReturnPurpleStone() {
        PurpleStone purpleStone = new PurpleStone();

        Assertions.assertEquals(SymbolType.PURPLE_STONE, purpleStone.getSymbolType());
    }
}