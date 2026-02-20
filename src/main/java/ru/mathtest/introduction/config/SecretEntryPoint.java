package ru.mathtest.introduction.config;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.dto.SpinContext;
import ru.mathtest.introduction.field.Field;
import ru.mathtest.introduction.field.GameMode;

import java.math.BigDecimal;

public class SecretEntryPoint implements EntryPoint {
    private final Field usual;
    private final Field holdAndWin;

    private BigDecimal bank;

    public SecretEntryPoint(Field usual, Field holdAndWin) {
        this.usual = usual;
        this.holdAndWin = holdAndWin;
    }

    @Override
    public void start() {
        bank = BigDecimal.valueOf(Utils.readIntValue("Введите ваше количество денег :)"));
        Field currentField = usual;
        System.out.println("Игра началась! Введите ставку или 'q' для выхода.");

        while (bank.compareTo(BigDecimal.ZERO) > 0) {

            String input = Utils.readStringValue("Ваш банк: " + bank + ". Введите ставку:");
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Вы вышли из игры. Ваш остаток: " + bank);
                break;
            }
            if (!betIsValid(input)) {
                continue;
            }
            BigDecimal bet = new BigDecimal(input);

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

                printSpinResults(reels);
                boolean gameOver = handleBetResults(ctx, bet);
                if (bank.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Банк опустел. Игра окончена.");
                    break;
                }
                if (gameOver) {
                    break;
                }

                if (ctx.getNextSpinGameMode() == GameMode.HOLD_AND_WIN) {
                    currentField = holdAndWin;
                    holdAndWin.transferCurrentReelsFrom(usual);
                } else {
                    currentField = usual;
                    usual.transferCurrentReelsFrom(holdAndWin);
                }
            }
        }
    }

    private void printSpinResults(Cell[][] reels) {
        System.out.println("Результат спина:");
        for (Cell[] row : reels) {
            for (Cell cell : row) {
                System.out.print(String.format("%-15s", cell.toString()));
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean betIsValid(String input) {
        boolean valid = true;
        BigDecimal bet = null;
        try {
            bet = new BigDecimal(input);
        } catch (NumberFormatException e) {
            System.out.println("Некорректная ставка. Попробуйте снова.");
            valid = false;
        }

        if (bet.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Ставка должна быть больше нуля.");
            valid = false;
        }

        if (bet.compareTo(bank) > 0) {
            System.out.println("Недостаточно средств. Попробуйте меньшую ставку.");
            valid = false;
        }
        return valid;
    }

    private boolean handleBetResults(SpinContext ctx, BigDecimal bet) {
        BigDecimal win = ctx.getTotalAward();
        if (win.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Вы выиграли: " + win);
        } else {
            System.out.println("Вы проиграли эту ставку.");
        }

        bank = bank.subtract(bet).add(win);
        System.out.println("Текущий банк: " + bank);
        return false;
    }
}
