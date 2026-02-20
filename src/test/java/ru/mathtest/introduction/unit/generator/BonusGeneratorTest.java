package ru.mathtest.introduction.unit.generator;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;
import ru.mathtest.introduction.cell.bonus.Bonus;
import ru.mathtest.introduction.cell.bonus.RaBonus;
import ru.mathtest.introduction.cell.bonus.UsualBonus;
import ru.mathtest.introduction.dto.CellDto;
import ru.mathtest.introduction.dto.PositionDto;
import ru.mathtest.introduction.generator.BonusGenerator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BonusGeneratorTest {

    @Test
    public void generate_shouldReturnUsualBonus_whenColumnIsFirst() {
        BonusGenerator generator = new BonusGenerator();

        CellDto dto = buildDto(0, BigDecimal.TEN);

        Bonus result = generator.generate(new Cell[3][3], new Cell[3][3], dto);

        assertTrue(result instanceof UsualBonus);
    }

    @Test
    public void generate_shouldReturnUsualBonus_whenColumnIsThird() {
        BonusGenerator generator = new BonusGenerator();

        CellDto dto = buildDto(2, BigDecimal.TEN);

        Bonus result = generator.generate(new Cell[3][3], new Cell[3][3], dto);

        assertTrue(result instanceof UsualBonus);
    }

    @Test
    public void generate_shouldReturnRaBonus_whenColumnIsMiddle() {
        BonusGenerator generator = new BonusGenerator();

        CellDto dto = buildDto(1, BigDecimal.TEN);

        Bonus result = generator.generate(new Cell[3][3], new Cell[3][3], dto);

        assertTrue(result instanceof RaBonus);
    }

    @Test
    public void getGeneratedCellType_shouldReturnBonus() {
        BonusGenerator generator = new BonusGenerator();
        assertEquals(CellType.BONUS, generator.getGeneratedCellType());
    }

    private CellDto buildDto(int columnIndex, BigDecimal bet) {
        PositionDto positionDto = PositionDto.builder()
                .rowIndex(0)
                .columnIndex(columnIndex)
                .build();

        CellDto dto = CellDto.builder()
                .positionDto(positionDto)
                .bet(bet)
                .build();
        return dto;
    }
}