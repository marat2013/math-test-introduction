package ru.mathtest.introduction.unit.generator.layout;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.dto.CellDto;
import ru.mathtest.introduction.dto.SpinContext;
import ru.mathtest.introduction.generator.CellGenerator;
import ru.mathtest.introduction.generator.CellTypeDeterminator;
import ru.mathtest.introduction.generator.GeneratorRegistry;
import ru.mathtest.introduction.generator.layout.RandomFieldLayoutGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomFieldLayoutGeneratorTest {

    static class StubCell implements Cell {
        private final CellType type;

        StubCell(CellType type) {
            this.type = type;
        }

        @Override
        public CellType getCellType() {
            return type;
        }
    }

    static class StubDeterminator extends CellTypeDeterminator {
        private final CellType toReturn;

        StubDeterminator(CellType toReturn) {
            this.toReturn = toReturn;
        }

        @Override
        public CellType determinate(Cell[][] prev, Cell[][] current, CellDto dto) {
            return toReturn;
        }
    }

    static class StubGenerator implements CellGenerator<StubCell> {
        private final CellType type;

        StubGenerator(CellType type) {
            this.type = type;
        }

        @Override
        public StubCell generate(Cell[][] prevCells, Cell[][] currentCells, CellDto dto) {
            return new StubCell(type);
        }

        @Override
        public CellType getGeneratedCellType() {
            return type;
        }
    }

    @Test
    void fill_shouldGenerateAllCellsUsingDeterminatorsAndGenerators() {
        StubGenerator generator = new StubGenerator(CellType.SYMBOL);
        List<CellGenerator<?>> generators = new ArrayList<>();
        generators.add(generator);
        GeneratorRegistry registry = new GeneratorRegistry(generators);
        StubDeterminator determinator = new StubDeterminator(CellType.SYMBOL);

        RandomFieldLayoutGenerator layoutGenerator =
                new RandomFieldLayoutGenerator(registry, determinator);

        Cell[][] prevCells = new Cell[2][3];
        SpinContext ctx = SpinContext.builder()
                .bet(BigDecimal.TEN)
                .totalAward(BigDecimal.ZERO)
                .spinsLeft(1)
                .build();

        Cell[][] result = layoutGenerator.fill(prevCells, ctx);

        assertEquals(2, result.length);
        assertEquals(3, result[0].length);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                assertNotNull(result[i][j]);
                assertEquals(CellType.SYMBOL, result[i][j].getCellType());
            }
        }
    }

    @Test
    void generateCell_shouldReturnCellFromRegistry() {
        StubGenerator generator = new StubGenerator(CellType.PRIZE);
        List<CellGenerator<?>> generators = new ArrayList<>();
        generators.add(generator);
        GeneratorRegistry registry = new GeneratorRegistry(generators);
        StubDeterminator determinator = new StubDeterminator(CellType.PRIZE);

        RandomFieldLayoutGenerator layoutGenerator =
                new RandomFieldLayoutGenerator(registry, determinator);

        Cell[][] prev = new Cell[1][1];
        Cell[][] current = new Cell[1][1];

        CellDto dto = CellDto.builder().build();

        Cell cell = layoutGenerator.generateCell(prev, current, dto);
        assertNotNull(cell);
        assertEquals(CellType.PRIZE, cell.getCellType());
    }
}