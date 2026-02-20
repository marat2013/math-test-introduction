package ru.mathtest.introduction.handler;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.dto.SpinContext;
import ru.mathtest.introduction.field.GameMode;

import java.util.List;

public class ReelsHandlerManager extends AbstractReelsHandler {

    private final List<ReelsHandler> reelsHandlers;

    public ReelsHandlerManager(List<ReelsHandler> reelsHandlers) {
        this.reelsHandlers = reelsHandlers;
    }

    @Override
    public void handle(Cell[][] reels, SpinContext ctx) {
        ReelsHandler previous = null;
        ReelsHandler firstInChain = null;
        for (ReelsHandler discriminator : reelsHandlers) {
            if (previous != null) {
                previous.setNext(discriminator);
            } else {
                firstInChain = discriminator;
            }
            previous = discriminator;
        }
        firstInChain.handle(reels, ctx);
    }

    @Override
    public GameMode getGameMode() {
        return GameMode.USUAL;
    }
}
