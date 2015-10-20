package com.hh.tasks;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    /**
     * Некорректный ввод
     *
     * @param wrongInput string
     * @param reason     void
     */
    public static void skipMes(String wrongInput, String reason) {
        System.out.println("Некорректный ввод '" + wrongInput + "': " + reason + "... пропускаем...");
    }

    /**
     * Считываем целые числа из консоли
     *
     * @return int[]
     */
    public static int[] readNum() {
        Scanner s = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<Integer>();
        Scanner numScanner = new Scanner(s.nextLine());
        for (; ; ) {
            if (numScanner.hasNext()) {
                if (!numScanner.hasNextInt()) {
                    System.out.println("некорректный ввод '" + numScanner.next() + "' пропускаем...");
                } else {
                    numbers.add(numScanner.nextInt());
                }
            } else {
                System.out.println("Ввод принят...");
                break;
            }
        }
        int[] ret = new int[numbers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = numbers.get(i);
        }
        return ret;
    }

    /**
     * Считываем целые числа из консоли
     *
     * @return int[]
     */
    public static int[] readThreeInt() {
        Scanner s = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<Integer>();
        Scanner numScanner = new Scanner(s.nextLine());
        for (int i = 0; i < 3; i++) {
            if (numScanner.hasNext()) {
                if (!numScanner.hasNextInt()) {
                    String n = numScanner.next();
                    if (n.contains(".")) {
                        Utils.skipMes(n, "дроби не поддерживаются");
                    } else {
                        Utils.skipMes(n, "требуется число");
                    }
                } else {
                    numbers.add(numScanner.nextInt());
                }
            }
        }
        if (numbers.size() % 3 > 0) {
            throw new IllegalArgumentException("Некорректные входные данные.");
        } else {
            int[] ret = new int[numbers.size()];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = numbers.get(i);
            }
            return ret;
        }
    }

    /**
     * Получить значение
     *
     * @param c int
     * @return char
     */
    public static char valueToDigit(int c) {
        if (c >= 0 && c <= 9) return (char) ((int) '0' + c);
        if (c >= 10 && c <= 15) return (char) (c - 10 + (int) 'A');
        return '?';
    }

    /**
     * Конвертация целого положительного числа в число в системе счисления base
     *
     * @param i    int
     * @param base int
     * @return String
     */
    public static String convertToBase(int i, int base) {
        String value = "";
        int mod;
        while (i >= base) {
            mod = i % base;
            i = i / base;
            value = valueToDigit(mod) + value;
        }
        value = valueToDigit(i) + value;
        return value;
    }
}
