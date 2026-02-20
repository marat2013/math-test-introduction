package ru.mathtest.introduction.config;

import ru.mathtest.introduction.field.Field;
import ru.mathtest.introduction.field.FieldFactory;
import ru.mathtest.introduction.generator.GeneratorFactory;
import ru.mathtest.introduction.generator.layout.FieldLayoutGenerator;

import java.io.IOException;
import java.util.Arrays;

public class ApplicationRunner {

    private final Field usual;
    private final Field holdAndWin;
    private final String[] args;
    private static final String CHEAT_CODE_STRING = "cheat_code";
    public ApplicationRunner(String[] args) {
        this.args = args;
        GeneratorFactory generatorFactory = new GeneratorFactory();
        FieldLayoutGenerator fieldLayoutGenerator = generatorFactory.getRandomFieldLayoutGenerator();
        FieldFactory fieldFactory = new FieldFactory();
        usual = fieldFactory.getUsualField(fieldLayoutGenerator);
        holdAndWin = fieldFactory.getHoldAndWinField(fieldLayoutGenerator);
    }

    public void run() {

        boolean interactiveModeActivated = Arrays.stream(args)
                .anyMatch(arg -> arg.equals(CHEAT_CODE_STRING));
        if (interactiveModeActivated) {
            new SecretEntryPoint(usual, holdAndWin).start();
        } else {
            int iterations = Utils.readIntValue("Введите целое число итераций");
            new SimpleEntryPoint(usual, holdAndWin, iterations).start();
        }
    }

}
