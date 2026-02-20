package ru.mathtest.introduction.field;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.dto.SpinContext;

public interface Field {
    Cell[][] spin(SpinContext ctx);
    void checkResults(Cell[][] reels, SpinContext ctx);
    void transferCurrentReelsFrom(Field field);
    Cell[][] getCurrentReels();
    void setCurrentReels(Cell[][] currentReels);
}
