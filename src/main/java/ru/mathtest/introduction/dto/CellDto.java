package ru.mathtest.introduction.dto;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.cell.CellType;

import java.math.BigDecimal;

public class CellDto {
    private Cell cell;
    private CellType cellType;
    private PositionDto positionDto;
    private BigDecimal bet;

    CellDto(Cell cell, CellType cellType, PositionDto positionDto, BigDecimal bet) {
        this.cell = cell;
        this.cellType = cellType;
        this.positionDto = positionDto;
        this.bet = bet;
    }

    public static CellDtoBuilder builder() {
        return new CellDtoBuilder();
    }

    public Cell getCell() {
        return this.cell;
    }

    public CellType getCellType() {
        return this.cellType;
    }

    public PositionDto getPositionDto() {
        return this.positionDto;
    }

    public BigDecimal getBet() {
        return this.bet;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public void setPositionDto(PositionDto positionDto) {
        this.positionDto = positionDto;
    }

    public void setBet(BigDecimal bet) {
        this.bet = bet;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CellDto)) return false;
        final CellDto other = (CellDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$cell = this.getCell();
        final Object other$cell = other.getCell();
        if (this$cell == null ? other$cell != null : !this$cell.equals(other$cell)) return false;
        final Object this$cellType = this.getCellType();
        final Object other$cellType = other.getCellType();
        if (this$cellType == null ? other$cellType != null : !this$cellType.equals(other$cellType)) return false;
        final Object this$positionDto = this.getPositionDto();
        final Object other$positionDto = other.getPositionDto();
        if (this$positionDto == null ? other$positionDto != null : !this$positionDto.equals(other$positionDto))
            return false;
        final Object this$bet = this.getBet();
        final Object other$bet = other.getBet();
        if (this$bet == null ? other$bet != null : !this$bet.equals(other$bet)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CellDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $cell = this.getCell();
        result = result * PRIME + ($cell == null ? 43 : $cell.hashCode());
        final Object $cellType = this.getCellType();
        result = result * PRIME + ($cellType == null ? 43 : $cellType.hashCode());
        final Object $positionDto = this.getPositionDto();
        result = result * PRIME + ($positionDto == null ? 43 : $positionDto.hashCode());
        final Object $bet = this.getBet();
        result = result * PRIME + ($bet == null ? 43 : $bet.hashCode());
        return result;
    }

    public String toString() {
        return "CellDto(cell=" + this.getCell() + ", cellType=" + this.getCellType() + ", positionDto=" + this.getPositionDto() + ", bet=" + this.getBet() + ")";
    }

    public static class CellDtoBuilder {
        private Cell cell;
        private CellType cellType;
        private PositionDto positionDto;
        private BigDecimal bet;

        CellDtoBuilder() {
        }

        public CellDtoBuilder cell(Cell cell) {
            this.cell = cell;
            return this;
        }

        public CellDtoBuilder cellType(CellType cellType) {
            this.cellType = cellType;
            return this;
        }

        public CellDtoBuilder positionDto(PositionDto positionDto) {
            this.positionDto = positionDto;
            return this;
        }

        public CellDtoBuilder bet(BigDecimal bet) {
            this.bet = bet;
            return this;
        }

        public CellDto build() {
            return new CellDto(this.cell, this.cellType, this.positionDto, this.bet);
        }

        public String toString() {
            return "CellDto.CellDtoBuilder(cell=" + this.cell + ", cellType=" + this.cellType + ", positionDto=" + this.positionDto + ", bet=" + this.bet + ")";
        }
    }
}
