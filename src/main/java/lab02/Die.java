/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2023
* Instructor: Professor Stough
* Section: 02 10AM
* Lab Section: 10AM
*
* Name: Chang Min Bark
* Date: 08/31/2023
*
* Lab / Assignment: lab02
*
* Description: I make a model of a single die simulatnig a fair die toss
*
* *****************************************/

package lab02;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Die {
    /** The minimum face value for our die */
    public static final int MIN_FACE_VALUE = 1;

    /** The maximum face value for our die */
    public static final int MAX_FACE_VALUE = 6;

    /** The number of times we want to roll for double dice */
    public static final int NUMBER_OF_ROLLS = 1000000;

    /** A reference to a random number generator to simulate rolling a die */
    private static Random rng = new Random(1234);

    /** Store the face value of the last roll */
    private int valueOfLastRoll;

    /**
     * Construct a new die
     */
    public Die() {
        this.valueOfLastRoll = 0;
    }

    /**
     * Return the last roll of the die
     */
    public int getValueOfLastRoll() { return this.valueOfLastRoll; }

    public int roll() {
        int spread = MAX_FACE_VALUE - MIN_FACE_VALUE + 1;
        this.valueOfLastRoll = Die.rng.nextInt(spread) + MIN_FACE_VALUE;
        return this.valueOfLastRoll;
    }

    // Used like __str__(self) method in Python where object is called with str() or print() methods. 
    @Override
    public String toString(){
        return "" + this.valueOfLastRoll;
    }

    /**
     * A simple main program to test out a Die object
     * 
     * @params args - program arguments (not used)
     */

    //  Scanner object
    private static Scanner scnr;

    // MAIN FUNCTION
    public static void main(String[] args) {
        Die die1 = new Die();
        Die die2 = new Die();
        scnr = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the dice simulator!");
        System.out.println("I'm going to roll 2 dice 1000000 times");

        // Get and check input from user.
        int diceSum = getDiceSumFromUser();

        // Get number of successful rolls that match diceSum
        double startTime = System.nanoTime()/1000000.0;
        int successfulRolls = simulateRolls(die1, die2, diceSum);
        double endTime = System.nanoTime()/1000000.0;

        // Calculate duration of time it took
        double duration = endTime - startTime;

        // Calculate percentage of successful rolls
        double percentOfRolls = (successfulRolls / 1.0) / NUMBER_OF_ROLLS * 100;

        // Decimal format for 3 decimal places
        DecimalFormat numFormat = new DecimalFormat("#.000");

        // Output message
        System.out.println("The roll value " + diceSum + " appeared " + successfulRolls + " times, or " + numFormat.format(percentOfRolls) + "% of all rolls.");
        System.out.println(NUMBER_OF_ROLLS + " rolls took " + numFormat.format(duration) + " ms.");
        System.out.println("Goodbye"); 
    }

    public static int getDiceSumFromUser(){
        int sum = -1;
        while (sum < 2 || sum > 12){
            System.out.println("What dice sum do you want to check for?");
            // Checks if scanner input is int
            if (!scnr.hasNextInt()){
                System.out.println("ERROR: Please enter a number!");
            }
            else {
                sum = scnr.nextInt();
                // Checks if scanner input is at least 2
                if (sum < 2) {
                    System.out.println("ERROR: Please enter an integer that is at least 2.");
                }
                // Checks if scanenr input is at most 12
                else if (sum > 12) {
                    System.out.println("ERROR: Please enter an integer that is at most 12.");
                }
            }
        // Refreshes the scanner's input buffer
        scnr.nextLine();
    }
        return sum;
    }

    public static int simulateRolls(Die firstDie, Die secondDie, int targetSum){
        // Initialize return value
        int ans = 0;

        for (int i = 0; i < NUMBER_OF_ROLLS; i++) {
        // Roll dice and find values of roll
            firstDie.roll();
            secondDie.roll();
            if (firstDie.valueOfLastRoll + secondDie.valueOfLastRoll == targetSum){
                ans++;
            }
        }

        return ans;
    }

}
