package ru.mathtest.introduction.dto;

public class PositionDto {
    private int rowIndex;
    private int columnIndex;

    PositionDto(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public static PositionDtoBuilder builder() {
        return new PositionDtoBuilder();
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColumnIndex() {
        return this.columnIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PositionDto)) return false;
        final PositionDto other = (PositionDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getRowIndex() != other.getRowIndex()) return false;
        if (this.getColumnIndex() != other.getColumnIndex()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PositionDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getRowIndex();
        result = result * PRIME + this.getColumnIndex();
        return result;
    }

    public String toString() {
        return "PositionDto(rowIndex=" + this.getRowIndex() + ", columnIndex=" + this.getColumnIndex() + ")";
    }

    public static class PositionDtoBuilder {
        private int rowIndex;
        private int columnIndex;

        PositionDtoBuilder() {
        }

        public PositionDtoBuilder rowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
            return this;
        }

        public PositionDtoBuilder columnIndex(int columnIndex) {
            this.columnIndex = columnIndex;
            return this;
        }

        public PositionDto build() {
            return new PositionDto(this.rowIndex, this.columnIndex);
        }

        public String toString() {
            return "PositionDto.PositionDtoBuilder(rowIndex=" + this.rowIndex + ", columnIndex=" + this.columnIndex + ")";
        }
    }
}
