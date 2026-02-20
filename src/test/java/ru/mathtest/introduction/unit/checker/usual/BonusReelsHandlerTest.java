package ru.mathtest.introduction.unit.checker.usual;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.bonus.RaBonus;
import ru.mathtest.introduction.cell.bonus.UsualBonus;
import ru.mathtest.introduction.handler.usual.BonusReelsHandler;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BonusReelsHandlerTest {

    @Test
    public void handle_shouldActivateHoldAndWin_whenOneRaAndOneUsual() {
        BonusReelsHandler handler = new BonusReelsHandler();

        Cell[][] reels = new Cell[1][2];
        reels[0][0] = new RaBonus();
        reels[0][1] = new UsualBonus(BigDecimal.valueOf(100));

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .nextSpinGameMode(GameMode.USUAL)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(1)
                .build();

        handler.handle(reels, ctx);

        assertEquals(GameMode.HOLD_AND_WIN, ctx.getNextSpinGameMode());
    }

    @Test
    public void handle_shouldActivateHoldAndWin_whenThreeRaBonuses() {
        BonusReelsHandler handler = new BonusReelsHandler();

        Cell[][] reels = new Cell[1][3];
        reels[0][0] = new RaBonus();
        reels[0][1] = new RaBonus();
        reels[0][2] = new RaBonus();

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .nextSpinGameMode(GameMode.USUAL)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(1)
                .build();

        handler.handle(reels, ctx);

        assertEquals(GameMode.HOLD_AND_WIN, ctx.getNextSpinGameMode());
    }

    @Test
    public void handle_shouldNotChangeMode_whenNoBonuses() {
        BonusReelsHandler handler = new BonusReelsHandler();

        Cell[][] reels = new Cell[1][1];
        reels[0][0] = new DummyCell();

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .nextSpinGameMode(GameMode.USUAL)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(1)
                .build();

        handler.handle(reels, ctx);

        assertEquals(GameMode.USUAL, ctx.getNextSpinGameMode());
    }

    @Test
    public void getGameMode_shouldReturnUsual() {
        BonusReelsHandler handler = new BonusReelsHandler();
        assertEquals(GameMode.USUAL, handler.getGameMode());
    }

    static class DummyCell implements Cell {
        @Override
        public CellType getCellType() {
            return CellType.SYMBOL;
        }
    }
}