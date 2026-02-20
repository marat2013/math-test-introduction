package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.Pyramid;
import ru.mathtest.introduction.cell.symbol.SymbolType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PyramidTest {

    @Test
    public void getWin_shouldReturnTripleBet() {
        Pyramid pyramid = new Pyramid();
        BigDecimal bet = BigDecimal.valueOf(100);

        BigDecimal win = pyramid.getWin(bet);

        assertEquals(BigDecimal.valueOf(300), win);
    }

    @Test
    public void getSymbolType_shouldReturnPyramid() {
        Pyramid pyramid = new Pyramid();

        Assertions.assertEquals(SymbolType.PYRAMID, pyramid.getSymbolType());
    }
}