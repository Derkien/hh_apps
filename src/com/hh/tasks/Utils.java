package com.hh.tasks;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    /**
     * Skip input message
     *
     * @param wrongInput string
     * @param reason     void
     */
    public static void skipMes(String wrongInput, String reason) {
        System.out.println("Wrong input '" + wrongInput + "': " + reason + "... skip...");
    }

    /**
     * Read from console
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
                    String n = numScanner.next();
                    if (n.contains(".")) {
                        Utils.skipMes(n, "fractions are not allowed");
                    } else {
                        Utils.skipMes(n, "need int");
                    }
                } else {
                    numbers.add(numScanner.nextInt());
                }
            } else {
                break;
            }
        }
        if (numbers.size() == 0) {
            throw new IllegalArgumentException("Wrong arguments passed! Exit!");
        } else {
            System.out.println("Input accepted...");
            int[] ret = new int[numbers.size()];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = numbers.get(i);
            }
            return ret;
        }
    }

    /**
     * Read three int from console
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
                        Utils.skipMes(n, "fractions are not allowed");
                    } else {
                        Utils.skipMes(n, "need int");
                    }
                } else {
                    numbers.add(numScanner.nextInt());
                }
            }
        }
        if (numbers.size() != 3) {
            throw new IllegalArgumentException("Wrong arguments passed! Exit!");
        } else {
            System.out.println("Input accepted...");
            int[] ret = new int[numbers.size()];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = numbers.get(i);
            }
            return ret;
        }
    }

    /**
     * Get char
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
     * Convert given positive int i to base 'base'
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
