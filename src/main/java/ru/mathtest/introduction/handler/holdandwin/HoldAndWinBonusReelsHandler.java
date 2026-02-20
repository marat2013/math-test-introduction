package ru.mathtest.introduction.handler.holdandwin;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.bonus.Bonus;
import ru.mathtest.introduction.cell.bonus.RaBonus;
import ru.mathtest.introduction.handler.AbstractReelsHandler;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

public class HoldAndWinBonusReelsHandler extends AbstractReelsHandler {
    private static final int SPINS_AMOUNT_DURING_HOLD_AND_WIN_MODE = 3;

    @Override
    public void handle(Cell[][] reels, SpinContext ctx) {
        int rows = reels.length;
        int columns = reels[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (reels[i][j].getCellType() == CellType.BONUS) {
                    Bonus bonus = (Bonus) reels[i][j];
                        ctx.setTotalAward(
                                ctx.getTotalAward().add(bonus.getPrize())
                        );
                    if (bonus.isRaBonus() && !((RaBonus) bonus).isFrozen()) {
                        ctx.setSpinsLeft(SPINS_AMOUNT_DURING_HOLD_AND_WIN_MODE);
                    }
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
