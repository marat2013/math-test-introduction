package ru.mathtest.introduction.handler.usual;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.symbol.Symbol;
import ru.mathtest.introduction.cell.symbol.SymbolType;
import ru.mathtest.introduction.handler.AbstractReelsHandler;
import ru.mathtest.introduction.handler.LineLayouts;
import ru.mathtest.introduction.field.GameMode;
import ru.mathtest.introduction.dto.SpinContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SymbolLineReelsHandler extends AbstractReelsHandler {

    @Override
    public void handle(Cell[][] reels, SpinContext ctx) {
        List<int[][]> lines = LineLayouts.LINES;

        List<BigDecimal> awards = new ArrayList<>();
        lines.forEach(line -> awards.add(
                        countAward(gatherTargetCells(line, reels), ctx.getBet())
                )
        );
        ctx.setTotalAward(awards.stream()
                .reduce(BigDecimal.ZERO, (number1, number2) -> number1.add(number2))
        );
        passToNext(reels, ctx);
    }

    @Override
    public GameMode getGameMode() {
        return GameMode.USUAL;
    }

    private BigDecimal countAward(List<Cell> cells, BigDecimal bet) {
        List<Symbol> symbols = cells.stream()
                .filter(cell -> cell.getCellType() == CellType.SYMBOL)
                .map(cell -> (Symbol) cell)
                .collect(Collectors.toList());

        if (symbols.size() != 3) {
            return BigDecimal.ZERO;
        }

        Symbol baseSymbol = symbols.stream()
                .filter(symbol -> symbol.getSymbolType() != SymbolType.WILD)
                .findFirst()
                .orElse(symbols.get(0));

        Symbol first = symbols.get(0);

        boolean lineIsFull = symbols.stream()
                .allMatch(symbol -> symbol.getSymbolType() == baseSymbol.getSymbolType()
                        || symbol.getSymbolType() == SymbolType.WILD
                );

        if (!lineIsFull) {
            return BigDecimal.ZERO;
        }
        return symbols.stream()
                .filter(symbol -> symbol.getSymbolType() != SymbolType.WILD)
                .findAny()
                .orElse(first)
                .getWin(bet);
    }

    private List<Cell> gatherTargetCells(int[][] line, Cell[][] reels) {
        int rows = reels.length;
        int columns = reels[0].length;
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (line[i][j] == 1) {
                    cells.add(reels[i][j]);
                }
            }
        }
        return cells;
    }

}
