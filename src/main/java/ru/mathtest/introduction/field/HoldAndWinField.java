package ru.mathtest.introduction.field;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.bonus.Bonus;
import ru.mathtest.introduction.cell.bonus.RaBonus;
import ru.mathtest.introduction.handler.ReelsHandlerManager;
import ru.mathtest.introduction.dto.SpinContext;
import ru.mathtest.introduction.generator.layout.FieldLayoutGenerator;

public class HoldAndWinField extends AbstractField {
    private final FieldLayoutGenerator randomFieldLayoutGenerator;
    private final ReelsHandlerManager holdAndWinRealsHandlerManager;

    public HoldAndWinField(FieldLayoutGenerator randomFieldLayoutGenerator, ReelsHandlerManager holdAndWinRealsHandlerManager) {
        this.randomFieldLayoutGenerator = randomFieldLayoutGenerator;
        this.holdAndWinRealsHandlerManager = holdAndWinRealsHandlerManager;
    }

    @Override
    public Cell[][] spin(SpinContext ctx) {
        Cell[][] prevReels = getCurrentReels();
        Cell[][] reels = randomFieldLayoutGenerator.fill(prevReels, ctx);
        holdRaBonusInPlace(reels, prevReels);
        setCurrentReels(reels);
        return reels;
    }

    @Override
    public void checkResults(Cell[][] reels, SpinContext ctx) {
        holdAndWinRealsHandlerManager.handle(reels, ctx);
        int rows = reels.length;
        int columns = reels[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = reels[i][j];
                if (cell.getCellType() == CellType.BONUS && ((Bonus) cell).isRaBonus()) {
                    ((RaBonus) cell).collect(ctx.getTotalAward());
                }
            }
        }
    }

    private void holdRaBonusInPlace(Cell[][] newReels, Cell[][] prevReels) {
        if (prevReels == null) {
            return;
        }
        int rows = prevReels.length;
        int columns = prevReels[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = prevReels[i][j];
                if (cell.getCellType() == CellType.BONUS && ((Bonus) cell).isRaBonus()) {
                    newReels[i][j] = cell;
                }
            }
        }
    }
}
