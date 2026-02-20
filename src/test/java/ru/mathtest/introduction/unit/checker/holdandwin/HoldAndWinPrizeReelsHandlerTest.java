package ru.mathtest.introduction.unit.checker.holdandwin;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.prize.Prize;
import ru.mathtest.introduction.cell.prize.PrizeType;
import ru.mathtest.introduction.handler.holdandwin.HoldAndWinPrizeReelsHandler;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class HoldAndWinPrizeReelsHandlerTest {

    @Test
    public void handle_shouldAddPrizeAwardToTotalAward() {
        HoldAndWinPrizeReelsHandler handler = new HoldAndWinPrizeReelsHandler();

        Cell[][] reels = new Cell[2][2];
        reels[0][0] = new DummyPrize(BigDecimal.valueOf(100));
        reels[0][1] = new DummyPrize(BigDecimal.valueOf(200));
        reels[1][0] = new DummyCell();
        reels[1][1] = new DummyCell();

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .nextSpinGameMode(GameMode.HOLD_AND_WIN)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(1)
                .build();

        handler.handle(reels, ctx);

        assertEquals(BigDecimal.valueOf(300), ctx.getTotalAward());
        assertEquals(GameMode.HOLD_AND_WIN, ctx.getNextSpinGameMode());
    }

    @Test
    public void handle_shouldSwitchToUsual_whenNoSpinsLeft() {
        HoldAndWinPrizeReelsHandler handler = new HoldAndWinPrizeReelsHandler();

        Cell[][] reels = new Cell[1][1];
        reels[0][0] = new DummyCell();

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .nextSpinGameMode(GameMode.HOLD_AND_WIN)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(0)
                .build();

        handler.handle(reels, ctx);

        assertEquals(GameMode.USUAL, ctx.getNextSpinGameMode());
    }

    @Test
    public void getGameMode_shouldReturnHoldAndWin() {
        HoldAndWinPrizeReelsHandler handler = new HoldAndWinPrizeReelsHandler();
        assertEquals(GameMode.HOLD_AND_WIN, handler.getGameMode());
    }

    static class DummyPrize implements Prize, Cell {
        private final BigDecimal award;

        DummyPrize(BigDecimal award) {
            this.award = award;
        }

        @Override
        public BigDecimal getAward() {
            return award;
        }

        @Override
        public CellType getCellType() {
            return CellType.PRIZE;
        }

        @Override
        public PrizeType getPrizeType() {
            return null;
        }
    }

    static class DummyCell implements Cell {
        @Override
        public CellType getCellType() {
            return CellType.SYMBOL;
        }
    }
}