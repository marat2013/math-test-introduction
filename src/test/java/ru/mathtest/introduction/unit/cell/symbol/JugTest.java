package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.Jug;
import ru.mathtest.introduction.cell.symbol.SymbolType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class JugTest {

    @Test
    public void getWin_shouldReturnOnePointSixOfBet() {
        Jug jug = new Jug();
        BigDecimal bet = BigDecimal.valueOf(100);

        BigDecimal win = jug.getWin(bet);

        assertEquals(BigDecimal.valueOf(160.0), win);
    }

    @Test
    public void getSymbolType_shouldReturnJug() {
        Jug jug = new Jug();

        Assertions.assertEquals(SymbolType.JUG, jug.getSymbolType());
    }
}