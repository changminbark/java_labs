/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 9/10/2023
 * Time: 1:43 PM
 *
 * Project: csci205_labs
 * Package: lab04
 * Class: HelloIntelliJ
 *
 * Description:
 *
 * ****************************************
 */
package lab04;

import java.util.Scanner;

/**
 * A simple program to help us become familiar with IntelliJ
 *
 * @author Chang Min
 */
public class HelloIntelliJ {

    private static String sFirst;
    private static String sLast;
    private static String sFullName;

    /**
     * Our main program
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        askUserForName(scnr);
        int countVowels = getCountOfVowels(sFullName);
        printResults(countVowels);
    }

    /**
     * Print the results of our vowel analysis to {@link System#out}
     *
     * @param countVowels
     */
    private static void printResults(int countVowels) {
        // Report the results of our vowel analysis
        System.out.printf("You have %d letters in your name.%n", sFullName.length()-1);
        System.out.printf("You have %d vowels.%n", countVowels);
        System.out.printf("%.1f%% of your name consists of vowels.%n", countVowels * 100.0/(sFullName.length()-1));
    }

    /**
     * Count the number of vowels in {@code str}
     * @param str to be analyzed for vowel count
     * @return the count of vowels in {@code str}
     */
    private static int getCountOfVowels(String str) {
        // Counting voewls in the name
        int countVowels = 0;
        for (char ch : str.toLowerCase().toCharArray()) {
            switch (ch) {
                case 'a', 'e', 'i', 'o', 'u' -> countVowels++;
            }
        }
        return countVowels;
    }

    /**
     * Ask the user for their first and last name. The results are stored in class
     * varialbes {@link #sFirst} and {@link #sLast}. The complete name with a space
     * between the first and last name will be stored in {@link #sFullName}
     *
     * @param scnr the {@link Scanner} object used to read input from the user
     */
    private static void askUserForName(Scanner scnr) {
        // Ask the user for their first name
        System.out.print("Please enter your first name:");
        sFirst = scnr.nextLine().strip();

        // Ask the user for their last name
        System.out.print("Please enter your last name:");
        sLast = scnr.nextLine().strip();

        // Combine them into a complete name
        sFullName = sFirst + " " + sLast;
        System.out.println("Your full name is: " + sFullName);
    }
}