package ru.mathtest.introduction.unit.checker.usual;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.symbol.Eye;
import ru.mathtest.introduction.cell.symbol.Symbol;
import ru.mathtest.introduction.cell.symbol.SymbolType;
import ru.mathtest.introduction.cell.symbol.Wild;
import ru.mathtest.introduction.handler.usual.SymbolLineReelsHandler;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymbolLineReelsHandlerTest {

    @Test
    public void handle_shouldSumAwardsAcrossAllLines_withWilds() {
        SymbolLineReelsHandler handler = new SymbolLineReelsHandler();

        Cell[][] reels = buildReels(
                new Eye(), new Eye(), new Wild(),
                new Eye(), new Wild(), new Wild(),
                new Wild(), new Wild(), new Wild()
        );

        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.valueOf(100))
                .nextSpinGameMode(GameMode.USUAL)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(1)
                .build();

        handler.handle(reels, ctx);

        BigDecimal firstLine = BigDecimal.valueOf(200);
        BigDecimal secondLine = BigDecimal.valueOf(200);
        BigDecimal thirdLine = BigDecimal.valueOf(1000);
        BigDecimal fourthLine = BigDecimal.valueOf(200);
        BigDecimal fifthLine = BigDecimal.valueOf(200);
        BigDecimal sixthLine = BigDecimal.valueOf(1000);
        BigDecimal seventhLine = BigDecimal.valueOf(200);
        BigDecimal eightsLine = BigDecimal.valueOf(1000);

        BigDecimal expectedTotal =
                firstLine.add(secondLine).add(thirdLine).add(fourthLine)
                        .add(fifthLine).add(sixthLine).add(seventhLine).add(eightsLine);

        assertEquals(expectedTotal, ctx.getTotalAward());
    }

    private Cell[][] buildReels(Symbol... symbols) {
        Cell[][] reels = new Cell[3][3];
        int index = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (index < symbols.length) {
                    reels[i][j] = new DummySymbolCell(symbols[index++]);
                } else {
                    reels[i][j] = new DummySymbolCell(new Eye());
                }
            }
        }
        return reels;
    }

    static class DummySymbolCell implements Symbol {

        private final Symbol delegate;

        DummySymbolCell(Symbol delegate) {
            this.delegate = delegate;
        }

        @Override
        public CellType getCellType() {
            return CellType.SYMBOL;
        }

        @Override
        public BigDecimal getWin(BigDecimal bet) {
            return delegate.getWin(bet);
        }

        @Override
        public SymbolType getSymbolType() {
            return delegate.getSymbolType();
        }
    }
}