package ru.mathtest.introduction.cell.symbol;

import java.util.HashMap;
import java.util.Map;

public class SymbolRegistry {
    private Map<SymbolType, Symbol> symbols = new HashMap<>();

    public SymbolRegistry() {
        symbols.put(SymbolType.GREEN_STONE, new GreenStone());
        symbols.put(SymbolType.PURPLE_STONE, new PurpleStone());
        symbols.put(SymbolType.ORANGE_STONE, new OrangeStone());
        symbols.put(SymbolType.RED_STONE, new RedStone());
        symbols.put(SymbolType.JUG, new Jug());
        symbols.put(SymbolType.EYE, new Eye());
        symbols.put(SymbolType.PYRAMID, new Pyramid());
        symbols.put(SymbolType.WINGS, new Wings());
        symbols.put(SymbolType.WILD, new Wild());
    }

    public Symbol getSymbol(SymbolType symbolType) {
        return symbols.get(symbolType);
    }
}

