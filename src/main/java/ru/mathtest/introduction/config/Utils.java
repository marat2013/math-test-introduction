package ru.mathtest.introduction.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {
    public static int readIntValue(String greetMessage) {
        System.out.println(greetMessage);
        int value;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                value = Integer.parseInt(bufferedReader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Введеная строка не является целым числом");
            }
        }
        return value;
    }

    public static String readStringValue(String greetMessage) {
        System.out.println(greetMessage);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String toReturn;
        try {
             toReturn = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }
}
