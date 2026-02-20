package ru.mathtest.introduction.handler.holdandwin;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.prize.Prize;
import ru.mathtest.introduction.handler.AbstractReelsHandler;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

public class HoldAndWinPrizeReelsHandler extends AbstractReelsHandler {
    @Override
    public void handle(Cell[][] reels, SpinContext ctx) {
        int rows = reels.length;
        int columns = reels[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (reels[i][j].getCellType() == CellType.PRIZE) {
                    Prize prize = (Prize) reels[i][j];
                    ctx.setTotalAward(
                            ctx.getTotalAward().add(prize.getAward())
                    );
                }
            }
        }
        if (ctx.getSpinsLeft() <= 0) {
            ctx.setNextSpinGameMode(GameMode.USUAL);
        }
        passToNext(reels, ctx);
    }

    @Override
    public GameMode getGameMode() {
        return GameMode.HOLD_AND_WIN;
    }
}
