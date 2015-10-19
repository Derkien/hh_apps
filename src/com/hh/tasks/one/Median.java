package com.hh.tasks.one;

import com.hh.tasks.Utils;

import java.util.Arrays;
/**
 * Медиана
 * Даны два отсортированных числовых массива одинаковой длины N. Найдите медиану числового массива длины 2N,
 * содержащего все числа из двух данных массивов.
 * Пример входных данных:
 * 1 2 3 4
 * 1 4 5 6
 */
public class Median {
    public static void main(String[] args) {
        Utils.printMes("Задайте две отсортированные последовательности чисел.");
        Utils.printMes("Задайте элементы первого массива, например: 1 2 3 4");
        int[] numbers = Utils.readNum();
        Utils.printMes("Длина массива: " + numbers.length);
        Utils.printMes("Задайте элементы второго массива, например: 1 4 5 6");
        int[] numbers2 = Utils.readNum();
        if (numbers.length != numbers2.length) {
            Utils.printMes("Длины массивов не совпадают! Повторите ввод!");
            System.exit(0);
        }
        Utils.printMes("Начинаю считать медиану...");
        Utils.printMes("Медиана: " + Double.toString(getMedian(numbers, numbers2, numbers.length)));
    }

    /**
     * Медиана массива длиной 2n, состоящего из двух отсортированных массивов одинаковой длины n
     *
     * @param ar1 int array сортированный массив
     * @param ar2 int array сортированный массив
     * @param n   int длина массива
     * @return double
     */
    protected static double getMedian(int ar1[], int ar2[], int n) {
        double medianOne;
        double medianTwo;
        if (n <= 0) {
            return -1;
        }
        if (n == 1) {
            return (double) (ar1[0] + ar2[0]) / 2;
        }
        if (n == 2) {
            return (double) (getMax(ar1[0], ar2[0]) + getMin(ar1[1], ar2[1])) / 2;
        }
        medianOne = median(ar1, n);
        medianTwo = median(ar2, n);
        if (medianOne == medianTwo)
            return medianOne;
        if (medianOne < medianTwo) {
            if (n % 2 == 0)
                return getMedian(Arrays.copyOfRange(ar1, n / 2 - 1, n), Arrays.copyOfRange(ar2, 0, n / 2 + 1), n - n / 2 + 1);
            else
                return getMedian(Arrays.copyOfRange(ar1, n / 2, n), Arrays.copyOfRange(ar2, 0, n / 2 + 1), n - n / 2);
        } else {
            if (n % 2 == 0)
                return getMedian(Arrays.copyOfRange(ar2, n / 2 - 1, n), Arrays.copyOfRange(ar1, 0, n / 2 + 1), n - n / 2 + 1);
            else
                return getMedian(Arrays.copyOfRange(ar2, n / 2, n), Arrays.copyOfRange(ar1, 0, n / 2 + 1), n - n / 2);
        }
    }

    /**
     * Медиана сортированного массива
     *
     * @param arr int array
     * @param n   int
     * @return double
     */
    protected static double median(int arr[], int n) {
        if (n % 2 == 0)
            return (double) (arr[n / 2] + arr[n / 2 - 1]) / 2;
        else
            return (double) arr[n / 2];
    }

    /**
     * Максимум
     *
     * @param x int
     * @param y int
     * @return int
     */
    protected static int getMax(int x, int y) {
        return x > y ? x : y;
    }

    /**
     * Минимум
     *
     * @param x int
     * @param y int
     * @return int
     */
    protected static int getMin(int x, int y) {
        return x > y ? y : x;
    }
}
