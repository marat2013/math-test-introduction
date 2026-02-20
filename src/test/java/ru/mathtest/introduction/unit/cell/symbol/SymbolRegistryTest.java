package ru.mathtest.introduction.unit.cell.symbol;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.symbol.Eye;
import ru.mathtest.introduction.cell.symbol.GreenStone;
import ru.mathtest.introduction.cell.symbol.Jug;
import ru.mathtest.introduction.cell.symbol.OrangeStone;
import ru.mathtest.introduction.cell.symbol.PurpleStone;
import ru.mathtest.introduction.cell.symbol.Pyramid;
import ru.mathtest.introduction.cell.symbol.RedStone;
import ru.mathtest.introduction.cell.symbol.SymbolRegistry;
import ru.mathtest.introduction.cell.symbol.SymbolType;
import ru.mathtest.introduction.cell.symbol.Wild;
import ru.mathtest.introduction.cell.symbol.Wings;

import static org.junit.jupiter.api.Assertions.*;

public class SymbolRegistryTest {

    @Test
    public void getSymbol_shouldReturnCorrectInstances() {
        SymbolRegistry registry = new SymbolRegistry();

        assertTrue(registry.getSymbol(SymbolType.GREEN_STONE) instanceof GreenStone);
        assertTrue(registry.getSymbol(SymbolType.PURPLE_STONE) instanceof PurpleStone);
        assertTrue(registry.getSymbol(SymbolType.ORANGE_STONE) instanceof OrangeStone);
        assertTrue(registry.getSymbol(SymbolType.RED_STONE) instanceof RedStone);
        assertTrue(registry.getSymbol(SymbolType.JUG) instanceof Jug);
        assertTrue(registry.getSymbol(SymbolType.EYE) instanceof Eye);
        assertTrue(registry.getSymbol(SymbolType.PYRAMID) instanceof Pyramid);
        assertTrue(registry.getSymbol(SymbolType.WINGS) instanceof Wings);
        assertTrue(registry.getSymbol(SymbolType.WILD) instanceof Wild);
    }

    @Test
    public void getSymbol_shouldNotReturnNull_forExistingTypes() {
        SymbolRegistry registry = new SymbolRegistry();

        for (SymbolType type : SymbolType.values()) {
            assertNotNull(registry.getSymbol(type));
        }
    }
}