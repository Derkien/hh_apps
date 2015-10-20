package com.hh.tasks.two;

import com.hh.tasks.Utils;

/**
 * Дробь
 * Даны два числа: a и b (вводятся в десятичной системе счисления). Найдите значение числа a/b,
 * записанного в k-ичной системе счисления. Если a/b — периодическая дробь, то период следует заключить в скобки.
 * <p/>
 * Пример входных данных:
 * 1 2 8
 * 1 12 10
 * <p/>
 * Пример выходных данных:
 * 0.4
 * 0.08(3)
 */
public class Fractions {
    public static void main(String[] args) {
        System.out.println("Задайте числитель знаменатель основание, например: 4325326 97 10.");
        int[] numbers = Utils.readThreeInt();
        //максимальное число знаков после запятой в результате
        int scale = 100;
        String value = Division(numbers[0], numbers[1], numbers[2], scale);
        System.out.println(numbers[0] + "/" + numbers[1] + " = " + value + " (в " + numbers[2] + "-ичной системе счисления)");
    }

    /**
     * Выполнить деление
     *
     * @param a     числитель
     * @param b     знаменатель
     * @param base  основание (система счисления)
     * @param scale максимальное число знаков после запятой в результате
     * @return String
     */
    public static String Division(int a, int b, int base, int scale) {
        int sign = 1;
        if (a < 0) {
            sign = -1;
            a *= (-1);
        }
        if (b < 0) {
            sign = -1;
            b *= (-1);
        }
        int mod;
        // остаток
        mod = a % b;
        // целая часть в нужной системе счисления
        String result = Utils.convertToBase(a / b, base);
        // вернем знак
        if (sign < 0) result = "-" + result;
        // если остаток ноль, то вернем результат
        if (mod == 0) return result;

        // вычислим дробную часть
        int intDiv;
        result += ".";
        int step = 0;
        int pL = 0;
        while (mod > 0 && step <= (b + 1) * 2) {
            a = mod * base;
            intDiv = a / b;
            mod = a % b;
            result += Utils.valueToDigit(intDiv);
            step++;
            //к этому моменту период уже обязан начаться
            //найдем длину
            if (step == b + 1) {
                pL = getPeriodLength(mod, b, base);
            }
        }
        // если периода нет или он длиннее лимита дробной части...
        if (pL <= 0 || pL > scale) {
            // если длина дробной части не превышает установленный размер
            if ((result.length() - result.indexOf(".") + 1) < scale) {
                return result;
            }
            return result.substring(0, result.indexOf(".") + scale + 1);
        }
        // обернем период в скобки
        return putPeriodIntoBrackets(result, pL);
    }

    /**
     * Вычислить длину периода
     * Делим до тех пор, пока не наткнемся на остаток, который уже был / long division, получим длину периода
     *
     * @param mod  int остаток от деления
     * @param b    int знаменатель
     * @param base int основание
     * @return int
     */
    public static int getPeriodLength(int mod, int b, int base) {
        int tempMod = mod;
        int pL = 0;
        do {
            ++pL;
            tempMod = (tempMod * base) % b;
        }
        while (tempMod != mod);
        return pL;
    }

    /**
     * Выделяем период
     *
     * @param number  String дробь
     * @param pLength длина периода
     * @return String дробь с выделенным периодом
     */
    public static String putPeriodIntoBrackets(String number, int pLength) {
        int left = number.indexOf(".") + 1;
        int right = left + pLength;
        int left2, right2;
        //совпадения найдены
        boolean flag = false;
        while (right < number.length()) {
            left2 = right;
            right2 = left2 + pLength;
            while (right2 < number.length()) {
                flag = number.substring(left, right).equals(number.substring(left2, right2));
                if (!flag) {
                    break;
                }
                left2 = right2;
                right2 += pLength;
            }
            if (flag) {
                break;
            }
            left++;
            right++;
        }
        if (!flag) {
            return number;
        }
        return number.substring(0, left) + "(" + number.substring(left, right) + ")";
    }
}
