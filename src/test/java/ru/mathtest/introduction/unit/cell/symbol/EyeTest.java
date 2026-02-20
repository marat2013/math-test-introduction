package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.Eye;
import ru.mathtest.introduction.cell.symbol.SymbolType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class EyeTest {

    @Test
    public void getWin_shouldReturnDoubleBet() {
        Eye eye = new Eye();
        BigDecimal bet = BigDecimal.valueOf(100);

        BigDecimal win = eye.getWin(bet);

        assertEquals(BigDecimal.valueOf(200), win);
    }

    @Test
    public void getSymbolType_shouldReturnEye() {
        Eye eye = new Eye();

        Assertions.assertEquals(SymbolType.EYE, eye.getSymbolType());
    }
}