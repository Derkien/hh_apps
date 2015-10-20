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
        System.out.println("Input two sorted sequences of integer numbers");
        System.out.println("Input first, like: 1 2 3 4");
        int[] numbers = Utils.readNum();
        System.out.println("Array length is: " + numbers.length);
        System.out.println("Input second with length " + numbers.length + ", like: 1 4 5 6");
        int[] numbers2 = Utils.readNum();
        if (numbers.length != numbers2.length) {
            System.out.println("Length of inputted arrays mismatch. Exit.");
//            System.exit(0);
            throw new IllegalArgumentException("Wrong arguments passed! Exit!");
        }
        System.out.println("Looking fo Median...");
        System.out.println("Median is: " + Double.toString(getMedian(numbers, numbers2, numbers.length)));
    }

    /**
     * Find median of two n-size arrays
     *
     * @param ar1 int[] array sorted array
     * @param ar2 int[] array sorted array
     * @param n   int array length
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
            return (double) ( (ar1[0] > ar2[0] ? ar1[0] : ar2[0]) + (ar1[1] < ar2[1] ? ar1[1] : ar2[1])) / 2;
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
     * Median of sorted array
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
}
