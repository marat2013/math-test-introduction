package ru.mathtest.introduction.config;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.dto.SpinContext;
import ru.mathtest.introduction.field.Field;
import ru.mathtest.introduction.field.GameMode;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleEntryPoint implements EntryPoint {
    private final Field usual;
    private final Field holdAndWin;
    private final int iterations;

    public SimpleEntryPoint(Field usual, Field holdAndWin, int iterations) {
        this.iterations = iterations;
        this.usual = usual;
        this.holdAndWin = holdAndWin;
    }

    @Override
    public void start() {
        Field currentField = usual;
        BigDecimal bet = BigDecimal.valueOf(5);
        BigDecimal totalWin = BigDecimal.ZERO;
        int hitCount = 0;

        for (int i = 0; i < iterations; i++) {
            System.out.println("Итерация " + i + " завершена");
            SpinContext ctx = SpinContext.builder()
                    .bet(bet)
                    .totalAward(BigDecimal.ZERO)
                    .spinsLeft(1)
                    .build();
            if (currentField == holdAndWin) {
                ctx.setNextSpinGameMode(GameMode.HOLD_AND_WIN);
            } else {
                ctx.setNextSpinGameMode(GameMode.USUAL);
            }

            while (ctx.getSpinsLeft() > 0) {
                ctx.setSpinsLeft(ctx.getSpinsLeft() - 1);
                ctx.setTotalAward(BigDecimal.ZERO);
                Cell[][] reels = currentField.spin(ctx);
                currentField.checkResults(reels, ctx);

                if (ctx.getTotalAward().compareTo(BigDecimal.ZERO) > 0) {
                    hitCount++;
                }
                totalWin = totalWin.add(ctx.getTotalAward());

                if (ctx.getNextSpinGameMode() == GameMode.HOLD_AND_WIN) {
                    currentField = holdAndWin;
                    holdAndWin.transferCurrentReelsFrom(usual);
                } else {
                    currentField = usual;
                    usual.transferCurrentReelsFrom(holdAndWin);
                }

            }

        }
        BigDecimal totalBet = bet.multiply(BigDecimal.valueOf(iterations));
        BigDecimal hitRate = BigDecimal.valueOf(hitCount * 100.0 / iterations)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal rtp = totalWin.multiply(BigDecimal.valueOf(100))
                .divide(totalBet, 2, RoundingMode.HALF_UP);

        System.out.println("Total Bet: " + totalBet.intValue());
        System.out.println("Total Win: " + totalWin.intValue());
        System.out.println("Total Hit Rate: " + hitRate + "%");
        System.out.println("Return to Player (RTP): " + rtp + "%");
    }
}
