package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.SymbolType;
import ru.mathtest.introduction.cell.symbol.Wings;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class WingsTest {

    @Test
    public void getWin_shouldReturnFiveTimesBet() {
        Wings wings = new Wings();
        BigDecimal bet = BigDecimal.valueOf(100);

        BigDecimal win = wings.getWin(bet);

        assertEquals(BigDecimal.valueOf(500), win);
    }

    @Test
    public void getSymbolType_shouldReturnWings() {
        Wings wings = new Wings();

        Assertions.assertEquals(SymbolType.WINGS, wings.getSymbolType());
    }
}