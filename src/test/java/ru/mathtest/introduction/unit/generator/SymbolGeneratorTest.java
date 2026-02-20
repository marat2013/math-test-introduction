package ru.mathtest.introduction.unit.generator;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.symbol.Symbol;
import ru.mathtest.introduction.cell.symbol.SymbolRegistry;
import ru.mathtest.introduction.cell.symbol.SymbolType;
import ru.mathtest.introduction.dto.CellDto;
import ru.mathtest.introduction.dto.PositionDto;
import ru.mathtest.introduction.generator.SymbolGenerator;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SymbolGeneratorTest {

    static class StubSymbol implements Symbol {

        private final SymbolType type;

        StubSymbol(SymbolType type) {
            this.type = type;
        }

        @Override
        public SymbolType getSymbolType() {
            return type;
        }

        @Override
        public CellType getCellType() {
            return CellType.SYMBOL;
        }

        @Override
        public BigDecimal getWin(BigDecimal bet) {
            return null;
        }
    }

    static class StubSymbolRegistry extends SymbolRegistry {

        private final Map<SymbolType, Symbol> symbols =
                new EnumMap<>(SymbolType.class);

        StubSymbolRegistry() {
            for (SymbolType type : SymbolType.values()) {
                symbols.put(type, new StubSymbol(type));
            }
        }

        @Override
        public Symbol getSymbol(SymbolType type) {
            return symbols.get(type);
        }
    }

    @Test
    void generate_shouldRespectApproximateBaseProbabilities_withoutBoost() {

        StubSymbolRegistry registry = new StubSymbolRegistry();
        SymbolGenerator generator = new SymbolGenerator(registry);

        int iterations = 300_000;

        Map<SymbolType, Integer> counters =
                new EnumMap<>(SymbolType.class);

        Cell[][] current = new Cell[3][3];

        CellDto dto = CellDto.builder()
                .positionDto(PositionDto.builder()
                        .rowIndex(0)
                        .columnIndex(0)
                        .build())
                .build();

        for (int i = 0; i < iterations; i++) {

            Symbol symbol = generator.generate(
                    new Cell[3][3],
                    current,
                    dto
            );

            counters.merge(symbol.getSymbolType(), 1, Integer::sum);
        }

        double green  = counters.getOrDefault(SymbolType.GREEN_STONE, 0)  / (double) iterations;
        double purple = counters.getOrDefault(SymbolType.PURPLE_STONE, 0) / (double) iterations;
        double orange = counters.getOrDefault(SymbolType.ORANGE_STONE, 0) / (double) iterations;
        double red    = counters.getOrDefault(SymbolType.RED_STONE, 0)    / (double) iterations;
        double jug    = counters.getOrDefault(SymbolType.JUG, 0)          / (double) iterations;
        double eye    = counters.getOrDefault(SymbolType.EYE, 0)          / (double) iterations;
        double pyramid= counters.getOrDefault(SymbolType.PYRAMID, 0)      / (double) iterations;
        double wings  = counters.getOrDefault(SymbolType.WINGS, 0)        / (double) iterations;
        double wild   = counters.getOrDefault(SymbolType.WILD, 0)         / (double) iterations;

        assertTrue(green  > 0.12 && green  < 0.18);
        assertTrue(purple > 0.12 && purple < 0.18);
        assertTrue(orange > 0.12 && orange < 0.18);
        assertTrue(red    > 0.12 && red    < 0.18);

        assertTrue(jug > 0.07 && jug < 0.13);
        assertTrue(eye > 0.07 && eye < 0.13);

        assertTrue(pyramid > 0.03 && pyramid < 0.07);
        assertTrue(wings   > 0.03 && wings   < 0.07);

        assertTrue(wild > 0.07 && wild < 0.13);
    }

    @Test
    void getGeneratedCellType_shouldReturnSymbol() {
        SymbolGenerator generator =
                new SymbolGenerator(new StubSymbolRegistry());

        assertEquals(CellType.SYMBOL, generator.getGeneratedCellType());
    }
}