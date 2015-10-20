package com.hh.tasks;

import com.hh.tasks.one.Median;
import com.hh.tasks.two.Fractions;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * runApp just for run :D
 */
public class runApp {
    public static void main(String[] args) {
        System.out.println("Hello User!!! ");
        int choice;
        do {
            showMenu();
            Scanner s = new Scanner(System.in);
            try {
                choice = s.nextInt();
            } catch (InputMismatchException e) {
                choice = 4;
            }
            switch (choice) {
                case 1:
                    System.out.println("Starting Median program");
                    try {
                        (new Median()).main(args);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Incorrect input!!!");
                    }
                    break;
                case 2:
                    System.out.println("Starting Fraction program");
                    try {
                        (new Fractions()).main(args);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Incorrect input!!!");
                    }
                    break;
                case 3:
                    System.out.println("Good bye!!!");
                    break;
                default:
                    System.out.println("Wrong choice! Choose another option!");
                    break;
            }
        } while (choice != 3);
    }

    /**
     * simple menu
     */
    protected static void showMenu() {
        System.out.println("-----MENU-----");
        System.out.println("1. Median");
        System.out.println("2. Fraction");
        System.out.println("3. Exit");
        System.out.print("Choice: ");
    }
}
