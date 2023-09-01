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

import java.util.Random;

public class Die {
    /** The minimum face value for our die */
    public static final int MIN_FACE_VALUE = 1;

    /** The maximum face value for our die */
    public static final int MAX_FACE_VALUE = 6;

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

     public static void main(String[] args) {
        Die die = new Die();

        // Roll the die and print it to the display
        die.roll();
        System.out.println("I rolled: " + die);

        System.out.println("Goodbye!");
     }

}
