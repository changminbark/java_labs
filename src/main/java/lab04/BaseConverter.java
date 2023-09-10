/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 9/10/2023
 * Time: 2:52 PM
 *
 * Project: csci205_labs
 * Package: lab04
 * Class: BaseConverter
 *
 * Description:
 *
 * ****************************************
 */
package lab04;

import java.util.Scanner;

/**
 * A program that helps us convert a number from base-3 to decimal (base-10)
 */
public class BaseConverter {

    private static String inputNumString;
    private static boolean isDoneInput;
    private static boolean isDone;

    /**
     * A program that converts a number from base-3 to decimal
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        isDone = false;
        isDoneInput = false;

        while (!isDone) {
            getInputFromUser(isDoneInput, scnr);
            int decimal = base3StringToDecimal(inputNumString);
            printResults(decimal, scnr);
        }
    }


    /**
     * Prints the decimal number as well as ask the user if they want to try again
     * @param decimal The decimal number to be printed (converted from base-3)
     * @param scnr The scanner object required for user input
     */
    private static void printResults(int decimal, Scanner scnr) {
        // Print result and asks the user if they want to try again
        System.out.println(decimal);
        System.out.print("Try again? [Y|N] ");
        if (scnr.nextLine().strip().equalsIgnoreCase("n")){
            isDone = true;
        }
        // Resetting the boolean variable to get/validate user input
        isDoneInput = false;
    }

    /**
     * Gets and validates a base-3 number string from the user while also removing leading 0's
     * @param isDoneInput A boolean variable that determines whether a valid input has been received
     * @param scnr The scanner object required for user input
     */
    private static void getInputFromUser(boolean isDoneInput, Scanner scnr) {
        while (!isDoneInput) {
            // Asking user for input number in base 3
            System.out.println("Please input a base-3 number that contains only 0,1 or 2:");
            inputNumString = scnr.nextLine().strip();

            // Checking whether the input string is a valid base-3 number using Regex
            if (inputNumString.matches("^[0-2]+$")) {
                isDoneInput = true;
            } else {
                System.out.println("Try again");
            }
        }
        // Remove leading 0s
        int i = 0;
        while (i < inputNumString.length() && inputNumString.charAt(i) == '0') {
            i++;
        }
        inputNumString = inputNumString.substring(i);
    }

    /**
     * A method that converts a string of numbers in base 3 to decimal
     * @param inputNumString - The input string of numbers that is in base 3
     * @return An integer in base-10 (decimal)
     */
    private static int base3StringToDecimal(String inputNumString) {
        // Convert the base-3 number to decimal
        int result = 0;
        for (int j = 0; j < inputNumString.length(); j++) {
            double digit = Character.getNumericValue(inputNumString.charAt(j));
            result += (int)digit * Math.pow(3.0, inputNumString.length() - 1 - j);
        }
        return result;
    }


}