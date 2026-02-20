package ru.mathtest.introduction.handler;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.dto.SpinContext;

public abstract class AbstractReelsHandler implements ReelsHandler {
    private ReelsHandler next;

    @Override
    public void setNext(ReelsHandler reelsHandler) {
        this.next = reelsHandler;
    }

    protected void passToNext(Cell[][] reels, SpinContext ctx) {
        if (next != null) {
            next.handle(reels, ctx);
        }
    }

    @Override
    public abstract void handle(Cell[][] reels, SpinContext ctx);
}
