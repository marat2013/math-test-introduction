package ru.mathtest.introduction.generator;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.bonus.Bonus;
import ru.mathtest.introduction.cell.bonus.RaBonus;
import ru.mathtest.introduction.cell.bonus.UsualBonus;
import ru.mathtest.introduction.dto.CellDto;


public class BonusGenerator implements CellGenerator<Bonus> {
    @Override
    public Bonus generate(Cell[][] prevCells, Cell[][] currentCells, CellDto cellDto) {

        int firstColumnIndex = 0, thirdColumnIndex = 2;
        if (cellDto.getPositionDto().getColumnIndex() == firstColumnIndex
                || cellDto.getPositionDto().getColumnIndex() == thirdColumnIndex) {

            return new UsualBonus(cellDto.getBet());
        }
        return new RaBonus();
    }

    @Override
    public CellType getGeneratedCellType() {
        return CellType.BONUS;
    }
}
