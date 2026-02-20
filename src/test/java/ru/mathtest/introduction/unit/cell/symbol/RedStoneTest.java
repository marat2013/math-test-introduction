package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.RedStone;
import ru.mathtest.introduction.cell.symbol.SymbolType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RedStoneTest {

    @Test
    public void getWin_shouldReturnSameAsBet() {
        RedStone redStone = new RedStone();
        BigDecimal bet = BigDecimal.valueOf(100);

        BigDecimal win = redStone.getWin(bet);

        assertEquals(BigDecimal.valueOf(100), win);
    }

    @Test
    public void getSymbolType_shouldReturnRedStone() {
        RedStone redStone = new RedStone();

        Assertions.assertEquals(SymbolType.RED_STONE, redStone.getSymbolType());
    }
}