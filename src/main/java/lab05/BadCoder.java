/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 9/14/2023
 * Time: 4:32 PM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: BadCoder
 *
 * Description:
 * A test on IntelliJ's debugger feature
 * ****************************************
 */
package lab05;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BadCoder {

    public static final int NUM_INTS = 10;
    public static final int MAX_RANDOM_INT = 100;

    public static void main(String[] args) {

        String name = greetUserAndGetName();
        int[] x = generateArrayOfRandomInts(MAX_RANDOM_INT);

        System.out.println(name + ", our array is: " + Arrays.toString(x));
    }

    /**
     * Generate an array of random integers
     *
     * @param bound the maximum value int to randomly generate (exclusive, meaning all integers
     *              from 0 (inclusive) to {@code bound} (exclusive).
     * @return the array filled with integers
     */
    private static int[] generateArrayOfRandomInts(int bound) {
        // Let's fill up an array with random integers
        Random rand = new Random();
        int[] x = new int[NUM_INTS];
        for (int i = 0; i < NUM_INTS; i++){
            x[i] = rand.nextInt(bound);
        }
        return x;
    }

    /**
     * Greets user and asks them for their name as an input
     *
     * @return the string of their name
     */
    private static String greetUserAndGetName() {
        // Greet the user and ask for their name
        Scanner scnr = new Scanner(System.in);
        System.out.println("Greetings. What is your name?");
        String name = scnr.next();
        return name;
    }
}