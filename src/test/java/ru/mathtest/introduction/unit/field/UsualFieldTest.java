package ru.mathtest.introduction.unit.field;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.handler.ReelsHandlerManager;
import ru.mathtest.introduction.dto.SpinContext;
import ru.mathtest.introduction.field.UsualField;
import ru.mathtest.introduction.generator.layout.RandomFieldLayoutGenerator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertSame;

public class UsualFieldTest {

    static class StubGenerator extends RandomFieldLayoutGenerator {
        private final Cell[][] toReturn;

        public StubGenerator(Cell[][] toReturn) {
            super(null, null);
            this.toReturn = toReturn;
        }

        @Override
        public Cell[][] fill(Cell[][] prevReels, SpinContext ctx) {
            return toReturn;
        }
    }

    static class StubHandlerManager extends ReelsHandlerManager {
        boolean handled = false;

        public StubHandlerManager() {
            super(null);
        }

        @Override
        public void handle(Cell[][] reels, SpinContext ctx) {
            handled = true;
        }
    }

    @Test
    public void spin_shouldUpdateCurrentReels() {
        Cell[][] fakeReels = new Cell[1][1];
        fakeReels[0][0] = new Cell() {
            @Override
            public CellType getCellType() {
                return CellType.SYMBOL;
            }
        };

        StubGenerator generator = new StubGenerator(fakeReels);
        StubHandlerManager handlerManager = new StubHandlerManager();

        UsualField field = new UsualField(generator, handlerManager);

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .spinsLeft(1)
                .totalAward(BigDecimal.ZERO)
                .build();

        Cell[][] result = field.spin(ctx);

        assertSame(fakeReels, result);
        assertSame(fakeReels, field.getCurrentReels());
    }

    @Test
    public void checkResults_shouldCallHandlerManager() {
        Cell[][] reels = new Cell[1][1];
        reels[0][0] = new Cell() {
            @Override
            public CellType getCellType() {
                return CellType.SYMBOL;
            }
        };

        StubGenerator generator = new StubGenerator(reels);
        StubHandlerManager handlerManager = new StubHandlerManager();

        UsualField field = new UsualField(generator, handlerManager);

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .spinsLeft(1)
                .totalAward(BigDecimal.ZERO)
                .build();

        field.checkResults(reels, ctx);

        assert(handlerManager.handled);
    }
}