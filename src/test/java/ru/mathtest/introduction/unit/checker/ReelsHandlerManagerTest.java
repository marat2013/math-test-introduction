package ru.mathtest.introduction.unit.checker;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.handler.ReelsHandler;
import ru.mathtest.introduction.handler.ReelsHandlerManager;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReelsHandlerManagerTest {

    static class DummyHandler implements ReelsHandler {

        boolean handled = false;

        private ReelsHandler next;

        @Override
        public void handle(Cell[][] reels, SpinContext ctx) {
            handled = true;
            if (next != null) next.handle(reels, ctx);
        }

        @Override
        public void setNext(ReelsHandler next) {
            this.next = next;
        }

        @Override
        public GameMode getGameMode() {
            return GameMode.USUAL;
        }
    }

    @Test
    public void handle_shouldCallAllHandlersInChain() {
        DummyHandler h1 = new DummyHandler();
        DummyHandler h2 = new DummyHandler();
        DummyHandler h3 = new DummyHandler();

        List<ReelsHandler> handlers = new ArrayList<>();
        handlers.add(h1);
        handlers.add(h2);
        handlers.add(h3);

        ReelsHandlerManager manager = new ReelsHandlerManager(handlers);

        Cell[][] reels = new Cell[1][1];
        reels[0][0] = new Cell() {
            @Override
            public CellType getCellType() {
                return CellType.SYMBOL;
            }
        };

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .nextSpinGameMode(GameMode.USUAL)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(1)
                .build();

        manager.handle(reels, ctx);

        assertTrue(h1.handled);
        assertTrue(h2.handled);
        assertTrue(h3.handled);
    }

    @Test
    public void getGameMode_shouldReturnUsual() {
        ReelsHandlerManager manager = new ReelsHandlerManager(new ArrayList<>());
        assertTrue(manager.getGameMode() == GameMode.USUAL);
    }
}