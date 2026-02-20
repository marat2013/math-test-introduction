package ru.mathtest.introduction.generator.layout;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.dto.CellDto;
import ru.mathtest.introduction.dto.PositionDto;
import ru.mathtest.introduction.dto.SpinContext;

public abstract class AbstractFieldLayoutGenerator implements FieldLayoutGenerator {

    @Override
    public Cell[][] fill(Cell[][] prevCells, SpinContext ctx) {
        int amountOfRows = prevCells.length;
        int amountOfColumns = prevCells[0].length;
        Cell[][] toReturn = new Cell[amountOfRows][amountOfColumns];

        for (int i = 0; i < amountOfRows; i++) {
            for (int j = 0; j < amountOfColumns; j++) {
                CellDto cellDto = CellDto.builder()
                        .positionDto(
                                PositionDto.builder()
                                        .rowIndex(i)
                                        .columnIndex(j)
                                        .build()
                        )
                        .bet(ctx.getBet())
                        .build();
                Cell cell = generateCell(prevCells, toReturn, cellDto);
                toReturn[i][j] = cell;
            }
        }
        return toReturn;
    }
    protected abstract Cell generateCell(
            Cell[][] prevCells, Cell[][] currentCells, CellDto dto
    );
}
