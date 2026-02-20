package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.SymbolType;
import ru.mathtest.introduction.cell.symbol.Wild;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class WildTest {

    @Test
    public void getWin_shouldReturnTenTimesBet() {
        Wild wild = new Wild();
        BigDecimal bet = BigDecimal.valueOf(100);

        BigDecimal win = wild.getWin(bet);

        assertEquals(BigDecimal.valueOf(1000), win);
    }

    @Test
    public void getSymbolType_shouldReturnWild() {
        Wild wild = new Wild();

        Assertions.assertEquals(SymbolType.WILD, wild.getSymbolType());
    }
}