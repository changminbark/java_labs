/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * Name: YOUR NAME
 * Date: 9/11/23
 * Time: 9:01 PM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: FractionCalc
 * Description:
 *
 * This is a test program provided for students to evaluate their
 * Fraction class
 * ****************************************
 */


 /******************************
  * DO NOT ALTER THIS PROGRAM! *
  ******************************/

package lab05;

import java.util.Scanner;

/**
 * This class encapsulates a set of class methods designed to test out the
 * {@link Fraction} class
 */
public class FractionCalc {

    /**
     * Add {@link Fraction} f1 and f2, returning the result Fraction in simplest terms
     * 
     * @param f1 One {@link Fraction} object
     * @param f2 Another {@link Fraction} object
     * @return A new Fraction representing f1 + f2
     */
    public static Fraction add(Fraction f1, Fraction f2) {
        return f1.add(f2).getSimplifiedFraction();
    }
    
    /**
     * Subtract {@link Fraction} f2 from f1, returning the result Fractin in simplest terms
     *
     * @param f1 One {@link Fraction} object
     * @param f2 Another {@link Fraction} object
     * @return A new Fraction representing f1 - f2
     */
    public static Fraction subtract(Fraction f1, Fraction f2) {
        return f1.add(f2.negate()).getSimplifiedFraction();
    }

    /**
     * Multiply {@link Fraction} f1 and f2, returning the result Fraction in simplest terms
     *
     * @param f1 One {@link Fraction} object
     * @param f2 Another {@link Fraction} object
     * @return A new Fraction representing f1 * f2
     */
    public static Fraction multiply(Fraction f1, Fraction f2) {
        return f1.multiply(f2).getSimplifiedFraction();
    }

    /**
     * Compute {@link Fraction} f1 / f2, returning the result Fraction in simplest terms
     *
     * @param f1 One {@link Fraction} object
     * @param f2 Another {@link Fraction} object
     * @return A new Fraction representing f1 / f2
     */
    public static Fraction divide(Fraction f1, Fraction f2) {
        return f1.multiply(f2.reciprocal()).getSimplifiedFraction();
    }

    /**
     * Ask the user whether they want to run the program in interactive mode, or
     * run the program as a series of interactive tests
     *
     * @param scnr
     * @return {@code true} if interactive move, {@code false} otherwise
     */
    private static boolean askUserForInteractiveMode(Scanner scnr) {
        boolean isDone = false;
        boolean isInteractive = false;
        do {
            System.out.println("Welcome to the fraction calculator!");
            System.out.println("Choose 1 for interactive mode or 2 to run tests:");
            String userInput = scnr.nextLine().strip();
            if (userInput.length() == 0) {
                System.out.println("No input! Try again!");
            }
            else if (userInput.charAt(0) == '1') {
                isInteractive = true;
                isDone = true;
            }
            else if (userInput.charAt(0) == '2') {
                isInteractive = false;
                isDone = true;
            }
            else {
                System.out.println("Bad choice! Try again!");
            }
        } while (!isDone);
        return isInteractive;
    }

    /**
     * Ask the user for a valid fraction using the supplied {@code prompt}.
     * Keep asking until a valid fraction is entered.
     *
     * @param prompt to be displayed when asking the user
     * @param scnr is the {@link Scanner} object to use
     *
     * @return A valid Fraction object read in by the user
     */
    private static Fraction askUserForFraction(String prompt, Scanner scnr) {
        Fraction frac1;
        do {
            System.out.println(prompt);
            frac1 = new Fraction(scnr.nextLine().strip());
            if (!frac1.isValid()) {
                System.out.println("Try again!");
                continue;
            }
        } while (!frac1.isValid());
        return frac1;
    }

    /**
     * A simple interactive program to ask the user for a couple of fractions, and then
     * report a series of fraction computations on it
     *
     * @param scnr
     */
    private static void runInteractiveMode(Scanner scnr) {
        Fraction frac2;
        Fraction frac1;
        boolean isDone = false;
        do {
            frac1 = askUserForFraction("Enter the FIRST fraction in the form x/y (x and y must be int values):", scnr);
            frac2 = askUserForFraction("Enter the SECOND fraction in the form x/y (x and y must be int values):", scnr);

            reportFractionCalculations(frac1, frac2);

            // Ask the user to try again?
            System.out.print("Try again? [y|n]: ");
            if (Character.toLowerCase(scnr.next().charAt(0)) != 'y')
                isDone = true;

            // Empty the buffer
            scnr.nextLine();

        } while (!isDone);
    }

    /**
     * Handle the dirty work of running an automated test on some hard coded
     * fractions. This is horrible, but ok until we get to JUnit unit testing.
     */
    private static void runAutomatedTest() {
        Fraction frac1, frac2;
        boolean allTestsPassed = true;

        frac1 = new Fraction(2, 0);
        if (frac1.isValid()) {
            System.out.println("ERROR: 2/0 is not a valid fraction!");
            allTestsPassed = false;
        }
        if (!frac1.toString().equals("ERROR - divide by 0")) {
            System.out.println("ERROR - 2/0 should output string \"ERROR - divide by 0\"");
            allTestsPassed = false;
        }
        frac1 = new Fraction("2/0");
        if (frac1.isValid()) {
            System.out.println("ERROR String \"2/0\" is not a valid fraction!");
            allTestsPassed = false;
        }

        // Let's use some valid fractions now
        frac1 = new Fraction(2,3);
        frac2 = new Fraction("20 / 30");

        // Print out the calculations first, so you can see expected results
        reportFractionCalculations(frac1, frac2);

        if (frac2.getDecimal() != 2.0/3.0) {
            System.out.printf("%s - getDecimal - FAIL! - should be %.3f%n", frac2, 2.0 / 3.0);
            allTestsPassed = false;
        }
        if (frac2.negate().getDecimal() != -2.0/3.0) {
            System.out.printf("%s - negate getDecimal - FAIL! - should be %.3f%n", frac2, -2.0 / 3.0);
            allTestsPassed = false;
        }
        if (!frac2.reciprocal().isEqualTo(new Fraction(30,20))) {
            System.out.printf("%s - reciprocal - FAIL! - should be equivalent to 30/20", frac2);
            allTestsPassed = false;
        }
        if (frac2.reciprocal().getDecimal() != 3.0/2.0) {
            System.out.printf("%s - reciprocal getDecimal - FAIL! - should be %.3f%n", frac2, 3.0 / 2.0);
            allTestsPassed = false;
        }
        if (!add(frac1,frac2).isEqualTo(new Fraction(4,3))) {
            System.out.printf("ADD: FAIL! - should be 4/3");
            allTestsPassed = false;
        }
        if (subtract(frac1,frac2).getDecimal() != 0) {
            System.out.println("SUBTRACT: FAIL! - should be 0");
            allTestsPassed = false;
        }
        if (!subtract(frac1,frac2).toString().equals("0")) {
            System.out.println("SUBTRACT: FAIL on toString() - should be \"0\"");
            allTestsPassed = false;
        }
        if (!multiply(frac1,frac2).isEqualTo(new Fraction(4,9))) {
            System.out.println("MULTIPLY: FAIL! - should be 4/9");
            allTestsPassed = false;
        }
        if (divide(frac1,frac2).getDecimal() != 1) {
            System.out.println("DIVIDE: FAIL! - should be 1");
            allTestsPassed = false;
        }
        if (!divide(frac1,frac2).toString().equals("1")) {
            System.out.println("DIVIDE: FAIL on toString() - should be \"1\"");
            allTestsPassed = false;
        }

        if (allTestsPassed)
            System.out.println("CONGRATULATIONS! ALL TESTS PASSED!");
        else
            System.out.println("INCOMPLETE! ONE OR MORE TEST FAILED!");
    }

    /**
     * This handles the work of reporting a series of fraction calculations
     * based on the methods in {@link Fraction}
     *
     * @param frac1
     * @param frac2
     */
    private static void reportFractionCalculations(Fraction frac1, Fraction frac2) {
        System.out.println(frac1 + " + " + frac2 + " = " + add(frac1, frac2));
        System.out.println(frac1 + " - " + frac2 + " = " + subtract(frac1, frac2));
        System.out.println(frac1 + " * " + frac2 + " = " + multiply(frac1, frac2));
        System.out.println(frac1 + " / " + frac2 + " = " + divide(frac1, frac2));
        System.out.printf("%s in decimal form is %.3f%n", frac1, frac1.getDecimal());
        System.out.printf("%s in decimal form is %.3f%n", frac2, frac2.getDecimal());
        System.out.printf("%s == %s --> %s%n", frac1, frac2, frac1.isEqualTo(frac2));
        System.out.printf("%s > %s --> %s%n", frac1, frac2, frac1.isGreaterThan(frac2));
    }

    /**
     * Our main program, designed to test out the Fraction class
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner scnr = new Scanner(System.in);
        Fraction frac1, frac2;

        // Ask the user if they want interactive mode where they can enter fractions,
        // or just run a quick test on a couple of fractions
        boolean isInteractive = askUserForInteractiveMode(scnr);

        if (isInteractive) {
            runInteractiveMode(scnr);
        }
        else {
            runAutomatedTest();
        }
    }


}
