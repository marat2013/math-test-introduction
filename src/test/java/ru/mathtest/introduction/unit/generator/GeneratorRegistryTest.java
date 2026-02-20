package ru.mathtest.introduction.unit.generator;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.dto.CellDto;
import ru.mathtest.introduction.generator.CellGenerator;
import ru.mathtest.introduction.generator.GeneratorRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorRegistryTest {

    static class StubGenerator implements CellGenerator<Cell> {

        private final CellType type;

        StubGenerator(CellType type) {
            this.type = type;
        }

        @Override
        public Cell generate(Cell[][] prevCells, Cell[][] currentCells, CellDto dto) {
            return null;
        }

        @Override
        public CellType getGeneratedCellType() {
            return type;
        }
    }

    @Test
    void getGenerator_shouldReturnRegisteredGenerator() {
        StubGenerator bonusGen = new StubGenerator(CellType.BONUS);
        StubGenerator symbolGen = new StubGenerator(CellType.SYMBOL);
        List<CellGenerator<?>> generators = new ArrayList<>();
        generators.add(bonusGen);
        generators.add(symbolGen);
        GeneratorRegistry registry = new GeneratorRegistry(generators);

        assertSame(bonusGen, registry.getGenerator(CellType.BONUS));
        assertSame(symbolGen, registry.getGenerator(CellType.SYMBOL));
    }

    @Test
    void getGenerator_shouldReturnNull_whenTypeNotRegistered() {
        List<CellGenerator<?>> generators = new ArrayList<>();
        GeneratorRegistry registry = new GeneratorRegistry(generators);

        assertNull(registry.getGenerator(CellType.PRIZE));
    }

    @Test
    void constructor_shouldOverrideGenerator_whenDuplicateType() {
        StubGenerator first = new StubGenerator(CellType.BONUS);
        StubGenerator second = new StubGenerator(CellType.BONUS);
        List<CellGenerator<?>> generators = new ArrayList<>();
        generators.add(first );
        generators.add(second );
        GeneratorRegistry registry = new GeneratorRegistry(generators);

        assertSame(second, registry.getGenerator(CellType.BONUS));
    }
}