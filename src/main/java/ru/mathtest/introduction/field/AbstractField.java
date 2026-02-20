package ru.mathtest.introduction.field;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.dto.SpinContext;

import java.util.Arrays;

public abstract class AbstractField implements Field {
    private static final int FIELD_HEIGHT = 3;
    private static final int FIELD_WIDTH = 3;

    private Cell[][] currentReels = new Cell[FIELD_HEIGHT][FIELD_WIDTH];

    @Override
    public abstract Cell[][] spin(SpinContext ctx);

    @Override
    public abstract void checkResults(Cell[][] reels, SpinContext ctx);

    @Override
    public void transferCurrentReelsFrom(Field field) {
        Cell[][] otherFieldReels = field.getCurrentReels();
        int rows = otherFieldReels.length;
        int columns = otherFieldReels.length;
        Cell[][] newReels = new Cell[rows][];
        for (int i = 0; i < rows; i++) {
            newReels[i] = Arrays.copyOf(otherFieldReels[i], columns);
        }
        this.currentReels = newReels;
    }

    public Cell[][] getCurrentReels() {
        return this.currentReels;
    }

    public void setCurrentReels(Cell[][] currentReels) {
        this.currentReels = currentReels;
    }
}
