package ru.mathtest.introduction.cell.bonus;

import java.math.BigDecimal;

public class RaBonus implements Bonus {

    private BigDecimal collectedWin = BigDecimal.ZERO;
    private final BigDecimal prize = BigDecimal.ZERO;
    private boolean frozen = false;

    @Override
    public boolean isRaBonus() {
        return true;
    }

    public void collect(BigDecimal prize) {
        collectedWin = collectedWin.add(prize);
    }

    @Override
    public String toString() {
        return "RaBonus";
    }

    public boolean isFrozen() {
        return frozen;
    }

    @Override
    public BigDecimal getPrize() {
        return prize;
    }

    public BigDecimal getCollectedWin() {
        return collectedWin;
    }

    public void setCollectedWin(BigDecimal collectedWin) {
        this.collectedWin = collectedWin;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
