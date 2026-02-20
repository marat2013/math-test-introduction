package ru.mathtest.introduction.generator;

import ru.mathtest.introduction.cell.prize.PrizeRegistry;
import ru.mathtest.introduction.cell.symbol.SymbolRegistry;
import ru.mathtest.introduction.generator.layout.FieldLayoutGenerator;
import ru.mathtest.introduction.generator.layout.RandomFieldLayoutGenerator;

import java.util.ArrayList;
import java.util.List;

public class GeneratorFactory {
    public FieldLayoutGenerator getRandomFieldLayoutGenerator() {
        List<CellGenerator<?>> generators = new ArrayList<>();
        generators.add(new BonusGenerator());
        generators.add(new PrizeGenerator(new PrizeRegistry()));
        generators.add(new SymbolGenerator(new SymbolRegistry()));
        return new RandomFieldLayoutGenerator(
                new GeneratorRegistry(generators), new CellTypeDeterminator()
        );
    }
}
