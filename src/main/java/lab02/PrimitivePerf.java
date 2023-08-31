/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2023
* Instructor: Professor Stough
* Section: 02 10AM
* Lab Section: 10AM
*
* Name: Chang Min Bark
* Date: 08/30/2023
*
* Lab / Assignment: lab02
*
* Description: We learn about types, including Java wrapper types. We also work with BigInt as well as computational performance based on design.
*
* *****************************************/




package lab02;

import java.text.DecimalFormat;

public class PrimitivePerf {

    /** The quantity of numbers to compute a sum for */
    private static final int MAX_NUMBERS = 10000000;

    /** MAIN PROGRAM */
    public static void main(String[] args) {
        // Our time durations we will use to store our results
        long primitiveDuration = 0L;
        long wrappedDuration = 0L;

        // Evaluate the test with primitive types
        long startTime = System.nanoTime();
        long primitiveResult = testPrimitive();
        primitiveDuration = System.nanoTime() - startTime;

        // Now, evaluate the test with the wrapper class type
        startTime = System.nanoTime();
        Long wrappedResult = testWrapped();
        wrappedDuration = System.nanoTime() - startTime;

        // TODO - Print results, timing, and pct difference between times
        double percentDiff = (primitiveDuration / 1.0) / wrappedDuration * 100;
        DecimalFormat numFormat = new DecimalFormat("#.0");

        System.out.println("Primitive: ");
        System.out.println("Answer = " + primitiveResult + ". Time = " + primitiveDuration);
        System.out.println();
        System.out.println("Wrapped: ");
        System.out.println("Answer = " + wrappedResult + ". Time = " + wrappedDuration);
        System.out.println();
        System.out.println("Primitive types used " + numFormat.format(percentDiff) + "% of the time of wrapper objects.");
    }

    /**
     * Simple function to evaluate the speed of adding numbers that are
     * primitive types
     */
    public static long testPrimitive() {
        // Store the result as a primitive type
        long sum = 0L;
        for (int i = 0; i < MAX_NUMBERS; i++) {
            sum = sum + i;
        }

        return sum;
    }

    /**
     * Simple function to evaluate the speed of adding numbers that are stored
     * as an object.
     */
    public static Long testWrapped() {
        // Store the result as an object Long, not the primitive type!
        Long objSum = 0L;
        for (int i = 0; i < MAX_NUMBERS; i++) {
            objSum = objSum + i;
        }

        return objSum;
    }
}
