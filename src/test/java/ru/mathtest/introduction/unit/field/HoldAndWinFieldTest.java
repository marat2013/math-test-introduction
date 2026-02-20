package ru.mathtest.introduction.unit.field;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.bonus.RaBonus;
import ru.mathtest.introduction.handler.ReelsHandlerManager;
import ru.mathtest.introduction.dto.SpinContext;
import ru.mathtest.introduction.field.HoldAndWinField;
import ru.mathtest.introduction.generator.layout.RandomFieldLayoutGenerator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class HoldAndWinFieldTest {

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

    static class StubRaBonus extends RaBonus {

        boolean collected = false;
        BigDecimal collectedAmount = BigDecimal.ZERO;

        @Override
        public void collect(BigDecimal amount) {
            collected = true;
            collectedAmount = amount;
        }

        @Override
        public boolean isRaBonus() {
            return true;
        }

        @Override
        public BigDecimal getPrize() {
            return BigDecimal.ZERO;
        }

        @Override
        public CellType getCellType() {
            return CellType.BONUS;
        }
    }

    @Test
    public void spin_shouldHoldRaBonusInPlace() {
        StubRaBonus raBonus = new StubRaBonus();

        Cell[][] prev = new Cell[1][1];
        prev[0][0] = raBonus;

        Cell[][] generated = new Cell[1][1];
        generated[0][0] = new Cell() {
            @Override
            public CellType getCellType() {
                return CellType.SYMBOL;
            }
        };

        StubGenerator generator = new StubGenerator(generated);
        StubHandlerManager manager = new StubHandlerManager();

        HoldAndWinField field = new HoldAndWinField(generator, manager);
        field.setCurrentReels(prev);

        Cell[][] result = field.spin(SpinContext.builder()
                .bet(BigDecimal.TEN)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(1)
                .build());

        assertSame(raBonus, result[0][0]);
    }

    @Test
    public void checkResults_shouldCallHandlerAndCollectRaBonus() {
        StubRaBonus raBonus = new StubRaBonus();

        Cell[][] reels = new Cell[1][1];
        reels[0][0] = raBonus;

        StubGenerator generator = new StubGenerator(reels);
        StubHandlerManager manager = new StubHandlerManager();

        HoldAndWinField field = new HoldAndWinField(generator, manager);

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.TEN)
                .totalAward(BigDecimal.valueOf(500))
                .spinsLeft(1)
                .build();

        field.checkResults(reels, ctx);

        assertTrue(manager.handled);
        assertTrue(raBonus.collected);
        assertEquals(BigDecimal.valueOf(500), raBonus.collectedAmount);
    }
}