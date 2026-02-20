package ru.mathtest.introduction.unit.checker.holdandwin;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.bonus.Bonus;
import ru.mathtest.introduction.cell.bonus.RaBonus;
import ru.mathtest.introduction.handler.holdandwin.HoldAndWinBonusReelsHandler;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class HoldAndWinBonusReelsHandlerTest {

    @Test
    public void handle_shouldUpdateTotalAwardAndSpinsLeft_whenBonusExists() {
        HoldAndWinBonusReelsHandler handler = new HoldAndWinBonusReelsHandler();

        Cell[][] reels = new Cell[2][2];
        reels[0][0] = new RaBonus();
        reels[0][1] = new RaBonus();
        reels[1][0] = new DummyCell();
        reels[1][1] = new DummyCell();

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .nextSpinGameMode(GameMode.HOLD_AND_WIN)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(0)
                .build();

        handler.handle(reels, ctx);

        assertEquals(3, ctx.getSpinsLeft());

        BigDecimal expectedAward = BigDecimal.ZERO
                .add(((Bonus) reels[0][0]).getPrize())
                .add(((Bonus) reels[0][1]).getPrize());
        assertEquals(expectedAward, ctx.getTotalAward());

        assertEquals(GameMode.HOLD_AND_WIN, ctx.getNextSpinGameMode());
    }

    @Test
    public void getGameMode_shouldReturnHoldAndWin() {
        HoldAndWinBonusReelsHandler handler = new HoldAndWinBonusReelsHandler();
        assertEquals(GameMode.HOLD_AND_WIN, handler.getGameMode());
    }

    static class DummyCell implements Cell {
        @Override
        public CellType getCellType() {
            return CellType.SYMBOL;
        }
    }
}