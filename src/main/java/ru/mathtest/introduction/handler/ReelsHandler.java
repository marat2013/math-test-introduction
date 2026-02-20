package ru.mathtest.introduction.handler;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

public interface ReelsHandler {
    void setNext(ReelsHandler reelsHandler);
    void handle(Cell[][] reels, SpinContext ctx);
    GameMode getGameMode();
}
