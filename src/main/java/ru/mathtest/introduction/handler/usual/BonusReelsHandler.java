package ru.mathtest.introduction.handler.usual;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.bonus.Bonus;
import ru.mathtest.introduction.cell.bonus.RaBonus;
import ru.mathtest.introduction.cell.bonus.UsualBonus;
import ru.mathtest.introduction.handler.AbstractReelsHandler;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BonusReelsHandler extends AbstractReelsHandler {

    @Override
    public void handle(Cell[][] reels, SpinContext ctx) {
        List<UsualBonus> usualBonuses = new ArrayList<>();
        List<RaBonus> raBonuses = new ArrayList<>();

        int rows = reels.length;
        int columns = reels[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = reels[i][j];
                if (cell.getCellType() == CellType.BONUS) {
                    if (((Bonus) cell).isRaBonus()) {
                        raBonuses.add((RaBonus) cell);
                    } else {
                        usualBonuses.add((UsualBonus) cell);
                    }
                }
            }
        }

        if (raBonuses.isEmpty() && !usualBonuses.isEmpty() || raBonuses.size() >= 1
                && raBonuses.size() <= 2 && usualBonuses.isEmpty()) {
            if (holdAndWinModeActivated(usualBonuses.size() + raBonuses.size())) {
                ctx.setNextSpinGameMode(GameMode.HOLD_AND_WIN);
                raBonuses.forEach(raBonus -> raBonus.setFrozen(true));
            }
        }
        if (raBonuses.size() == 1 && usualBonuses.size() == 1) {
            ctx.setNextSpinGameMode(GameMode.HOLD_AND_WIN);
            raBonuses.forEach(raBonus -> raBonus.setFrozen(true));
        }

        if (raBonuses.size() >= 2 && !usualBonuses.isEmpty()
                || !raBonuses.isEmpty() && usualBonuses.size() >= 2 || raBonuses.size() >= 3) {
            ctx.setNextSpinGameMode(GameMode.HOLD_AND_WIN);
            raBonuses.forEach(raBonus -> raBonus.setFrozen(true));
        }
        passToNext(reels, ctx);
    }

    @Override
    public GameMode getGameMode() {
        return GameMode.USUAL;
    }

    private boolean holdAndWinModeActivated(int amountOfCoins) {

        if (amountOfCoins < 1 || amountOfCoins > 9) {
            throw new IllegalArgumentException("Минимальное количество монет:1, максимальное: 9");
        }
        double chancePercent = amountOfCoins * 0.05;
        int randomValue = ThreadLocalRandom.current().nextInt(100);

        return randomValue < chancePercent;
    }
}
