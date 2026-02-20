package ru.mathtest.introduction.dto;

import ru.mathtest.introduction.field.GameMode;

import java.math.BigDecimal;

public class SpinContext {
    private GameMode nextSpinGameMode;
    private int spinsLeft;
    private BigDecimal bet;
    private BigDecimal totalAward;

    SpinContext(GameMode nextSpinGameMode, int spinsLeft, BigDecimal bet, BigDecimal totalAward) {
        this.nextSpinGameMode = nextSpinGameMode;
        this.spinsLeft = spinsLeft;
        this.bet = bet;
        this.totalAward = totalAward;
    }

    public static SpinContextBuilder builder() {
        return new SpinContextBuilder();
    }

    public GameMode getNextSpinGameMode() {
        return this.nextSpinGameMode;
    }

    public int getSpinsLeft() {
        return this.spinsLeft;
    }

    public BigDecimal getBet() {
        return this.bet;
    }

    public BigDecimal getTotalAward() {
        return this.totalAward;
    }

    public void setNextSpinGameMode(GameMode nextSpinGameMode) {
        this.nextSpinGameMode = nextSpinGameMode;
    }

    public void setSpinsLeft(int spinsLeft) {
        this.spinsLeft = spinsLeft;
    }

    public void setBet(BigDecimal bet) {
        this.bet = bet;
    }

    public void setTotalAward(BigDecimal totalAward) {
        this.totalAward = totalAward;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SpinContext)) return false;
        final SpinContext other = (SpinContext) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$nextSpinGameMode = this.getNextSpinGameMode();
        final Object other$nextSpinGameMode = other.getNextSpinGameMode();
        if (this$nextSpinGameMode == null ? other$nextSpinGameMode != null : !this$nextSpinGameMode.equals(other$nextSpinGameMode))
            return false;
        if (this.getSpinsLeft() != other.getSpinsLeft()) return false;
        final Object this$bet = this.getBet();
        final Object other$bet = other.getBet();
        if (this$bet == null ? other$bet != null : !this$bet.equals(other$bet)) return false;
        final Object this$totalAward = this.getTotalAward();
        final Object other$totalAward = other.getTotalAward();
        if (this$totalAward == null ? other$totalAward != null : !this$totalAward.equals(other$totalAward))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SpinContext;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nextSpinGameMode = this.getNextSpinGameMode();
        result = result * PRIME + ($nextSpinGameMode == null ? 43 : $nextSpinGameMode.hashCode());
        result = result * PRIME + this.getSpinsLeft();
        final Object $bet = this.getBet();
        result = result * PRIME + ($bet == null ? 43 : $bet.hashCode());
        final Object $totalAward = this.getTotalAward();
        result = result * PRIME + ($totalAward == null ? 43 : $totalAward.hashCode());
        return result;
    }

    public String toString() {
        return "SpinContext(nextSpinGameMode=" + this.getNextSpinGameMode() + ", spinsLeft=" + this.getSpinsLeft() + ", bet=" + this.getBet() + ", totalAward=" + this.getTotalAward() + ")";
    }

    public static class SpinContextBuilder {
        private GameMode nextSpinGameMode;
        private int spinsLeft;
        private BigDecimal bet;
        private BigDecimal totalAward;

        SpinContextBuilder() {
        }

        public SpinContextBuilder nextSpinGameMode(GameMode nextSpinGameMode) {
            this.nextSpinGameMode = nextSpinGameMode;
            return this;
        }

        public SpinContextBuilder spinsLeft(int spinsLeft) {
            this.spinsLeft = spinsLeft;
            return this;
        }

        public SpinContextBuilder bet(BigDecimal bet) {
            this.bet = bet;
            return this;
        }

        public SpinContextBuilder totalAward(BigDecimal totalAward) {
            this.totalAward = totalAward;
            return this;
        }

        public SpinContext build() {
            return new SpinContext(this.nextSpinGameMode, this.spinsLeft, this.bet, this.totalAward);
        }

        public String toString() {
            return "SpinContext.SpinContextBuilder(nextSpinGameMode=" + this.nextSpinGameMode + ", spinsLeft=" + this.spinsLeft + ", bet=" + this.bet + ", totalAward=" + this.totalAward + ")";
        }
    }
}
