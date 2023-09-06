/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2023
* Instructor: Professor Stough
* Section: 02 10AM
* Lab Section: 10AM
*
* Name: Chang Min Bark
* Date: 09/04/2023
*
* Lab / Assignment: lab03
*
* Description: Create a program that determines whether generated data has a skew in its distribution.
*
* *****************************************/

package lab03;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


 /**
  * A simple enumerated type to help distinguish between skew types that the user 
  * might specify.
  */
enum SkewType {
    SKEW_LEFT,
    SKEW_RIGHT,
    SKEW_NONE
}

/** 
 * A class that encapsulate some methods to aid in generating a simulated dataset with
 * a specified skew, then reporting the results to see if the RNG really did properly
 * skew the data.
 */
public class Skewness {
    /** mean of data to be generate */
    public static final double DEF_MEAN = 10.0;

    /** stdev of data to be generated */
    public static final double DEF_STDEV = 2.0;

    /** Scanner to use for input */
    private static Scanner scnr;

    /** Initializing input variables */
    public static int inputNum = 0;
    public static int inputSkew = -1;
    public static SkewType skew = SkewType.SKEW_NONE;

    /**
     * This is an example main program you may use to complete this assignment.
     * 
     * @param args
     */
    public static void main(String[] args) {

        scnr = new Scanner(System.in);
        // Getting input from user
        getNumAndSkew();
        double[] output = generateSkewedData(inputNum, skew);
        Arrays.sort(output);

        // Calculates the mean
        double sum = 0;
        for (int i = 0; i < output.length; i++){
            sum += output[i];
        }
        double avg = sum/output.length;

        // Calculates the median
        double med = 0;
        // If array has even number of items
        if (output.length % 2 == 0){
            int smallerIdx = output.length / 2 - 1;
            int biggerIdx = output.length / 2;
            med = (output[smallerIdx] + output[biggerIdx]) / 2.0;
        } 
        // If array has odd number of items
        else {
            int midIdx = output.length / 2;
            med = output[midIdx];
        }
        
        
        // Calculates observed skew
        String skewOutput = "";
        if (Math.abs(avg - med) > 0.01 * avg){
            if (avg > med){
                skewOutput = "RIGHT";
            }
            else {
                skewOutput = "LEFT";
            }
        } else {
            skewOutput = "NONE";
        }

        // Final System output
        System.out.println("mean: " + avg);
        System.out.println("median: " + med);
        System.out.println("Observed skew: " + skewOutput);





    }


    /**
     * Get the size of the numbers the user wants to generate as well as the skew they want in their data.
     * Updates the global variables inputNum and skew.
     */
    public static void getNumAndSkew() {

        // Tests whether input number is valid
        while (!(inputNum > 0)) {
            System.out.println("How many numbers do you want to generate?");
            if(!(scnr.hasNextInt())){
                System.out.println("Please input a number");
            }
            else {
                inputNum = scnr.nextInt();
            }
            scnr.nextLine();
        }
        // Tests whether input skew is valid
        while (inputSkew < 0 || inputSkew > 2){
            System.out.println("Please choose one of the following");
            System.out.println("0 - NO skew");
            System.out.println("1 - LEFT skew");
            System.out.println("2 - RIGHT skew");
            if(!(scnr.hasNextInt())){
                System.out.println("Please input an integer number");
            }
            else {
                inputSkew = scnr.nextInt();
                if(inputSkew < 0 || inputSkew > 2) {
                    System.out.println("Please input one of the choices listed.");
                }
            }
            scnr.nextLine();
        }
        // Determines which skew type the user inputted.
        if (inputSkew == 1){
            skew = SkewType.SKEW_LEFT;
        }
        if (inputSkew == 2){
            skew = SkewType.SKEW_RIGHT;
        }
        if (inputSkew == 0){
            skew = SkewType.SKEW_NONE;
        }
    }

    /**
     * Create and initialize an array of <code>size</code> with
     * random integers between 0 and <code>MAX_INT</code> inclusive.
     *
     * @param sizeOfArray the size of the array to fill
     * @param skewType the specified {@link SkewType} 
     * @return an int array of size random numbers between 0 and MAX_INT
     */
    public static double[] generateSkewedData(int sizeOfArray, SkewType skewType) {
        double[] result = new double[sizeOfArray];

        Random rng = new Random(1000);

        // Fill in the array of random integers
        for (int i = 0; i < sizeOfArray; i++) {
            result[i] = rng.nextGaussian() * DEF_STDEV + DEF_MEAN;
        }

        // Let's create some intentional skew in our data if requested
        for(int i = 0; i < sizeOfArray/20; i++) {
            switch (skewType) {
                case SKEW_LEFT:
                    result[i] = (rng.nextGaussian() * DEF_STDEV + DEF_MEAN) * 0.25;
                    break;
                case SKEW_RIGHT:
                    result[i] = (rng.nextGaussian() * DEF_STDEV + DEF_MEAN) * 4;
                    break;
            }
        }

        return result;
    }

}
