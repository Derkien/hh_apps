package com.hh.tasks.two;

import com.hh.tasks.Utils;

/**
 * Дробь
 * Даны два числа: a и b (вводятся в десятичной системе счисления). Найдите значение числа a/b,
 * записанного в k-ичной системе счисления. Если a/b — периодическая дробь, то период следует заключить в скобки.
 * Пример входных данных:
 * 1 2 8
 * 1 12 10
 * Пример выходных данных:
 * 0.4
 * 0.08(3)
 */
public class Fractions {

    public void main(String[] args) {
        System.out.println("Input numerator, denominator and base, like: 17 983 10");
        int[] numbers = Utils.readThreeInt();
        // length of remainder
        int scale = 30000;
        String value = Division(numbers[0], numbers[1], numbers[2], scale);
        System.out.println("Result: " + numbers[0] + "/" + numbers[1] + " = " + value + " (" + numbers[2] + "-based numeration system)");
    }

    /**
     * Start operation
     *
     * @param a     numerator
     * @param b     denominator
     * @param base  base (numeration system)
     * @param scale length of remainder
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
        // remainder
        mod = a % b;
        
        // integer part converted to base
        String result = Utils.convertToBase(a / b, base); //Character.forDigit ...
        // sign
        if (sign < 0) result = "-" + result;
        // if remainder is zero
        if (mod == 0) return result;

        // get the remainder
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
            //period must start from that point... find length
            if (step == b + 1) {
                pL = getPeriodLength(mod, b, base);
            }
        }
        // no period or it exceed limits
        if (pL <= 0 || pL > scale) {
            // if remainder is not exceed
            if ((result.length() - result.indexOf(".") + 1) < scale) {
                return result;
            }
            return result.substring(0, result.indexOf(".") + scale + 1);
        }
        // put in brackets
        return bracketPeriod(result, pL);
    }

    /**
     * Period length
     * Dividing remainder multiplied by base by denominator until get result that we have had before
     * Делим до тех пор, пока не наткнемся на остаток, который уже был / long division, получим длину периода
     *
     * @param mod  int test remainder
     * @param b    int denominator
     * @param base int base (numeration system)
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
     * Brackets
     *
     * @param number  String дробь
     * @param pLength period length
     * @return String result string
     */
    public static String bracketPeriod(String number, int pLength) {
        int left = number.indexOf(".") + 1;
        int right = left + pLength;
        int left2, right2;
        // ok!
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
