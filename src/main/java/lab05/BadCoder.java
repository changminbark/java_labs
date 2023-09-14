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
    public static void main(String[] args) {
        // Greet the user and ask for their name
        Scanner scnr = new Scanner(System.in);
        System.out.println("Greetings. What is your name?");
        String name = scnr.next();

        // Let's fill up an array with random integers
        Random rand = new Random();
        int[] x = new int[10];
        for (int i = 0; i < 10; i++){
            x[i] = rand.nextInt(100);
        }
        System.out.println(name + ", our array is: " + Arrays.toString(x));
    }
}