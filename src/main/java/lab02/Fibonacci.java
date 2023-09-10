/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Your instructor
 * Section: List time of your lecture
 *
 * Name: Your Name
 * Date: xx/xx/2023
 *
 * Lab / Assignment: lab02
 *
 * Description:
 * An exercise to compare different algorithms to solve
 * a simple problem
 * *****************************************/

package lab02;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * A simple class designed to give the user an opportunity to test a recursive
 * and non-recursive version of fibonacci, as well as using BigInteger so you
 * can
 * compute very large values
 *
 * @author BRK 2023-spring
 */
public class Fibonacci {

    /**
     * Compute the nth fibonacci number recursively
     *
     * @param n - the nth number to find
     * @return the nth number in the fibonacci sequence
     */
    public static int recFib(int n) {
        // TODO - Complete this method
        if (n == 0){
            return 0;
        }
        else if (n == 1) {
            return 1;
        }
        else {
            return recFib(n-1) + recFib(n-2);
        }
        
    }

    /**
     * Compute the nth fibonacci number non-recursively, using a while loop.
     *
     * @param n - the nth number to find
     * @return the nth number in the fibonacci sequence
     */
    public static int nonRecFib(int n) {
        // TODO - Complete this method
        int prevPrevNum, prevNum = 0, curNum = 1;
        // Need to start with count = 1 as "1" is the second fibonacci number
        int count = 1;
        while (count < n) {
            prevPrevNum = prevNum;
            prevNum = curNum;
            curNum = prevPrevNum + prevNum;
            count++;
        }
        if (n == 0) {
            return 0;
        }
        return curNum;
        
    }

    /**
     * Compute the nth fibonacci number non-recursively, using BigInteger.
     * 
     * @param n    - the nth number to find
     */
    public static BigInteger nonRecFibBigInteger(int n) {

        BigInteger prevPrevNum;
        BigInteger prevNum = new BigInteger("0");
        BigInteger currNum = new BigInteger("1");
        int count = 1;
        while (count < n) {
            prevPrevNum = prevNum;
            prevNum = currNum;
            currNum = prevPrevNum.add(prevNum);
            count++;
        }
        // TODO - Complete this method
        if (n == 0) {
            return BigInteger.ZERO;
        }
        else {
            return currNum;
        }
        
    }

    /**
     * Main program to test all fibonacci methods
     */
    public static void main(String[] args) {
        final int MAX_RECURSIVE_N = 40;
        DecimalFormat numFormat = new DecimalFormat("#0.0000");

        System.out.println("Fibonacci number to compute:");
        Scanner scnr = new Scanner(System.in);
        int n = scnr.nextInt();

        // Store the result from the different ways to compute fib(n)
        int recResult = 0;

        // Recursive
        double startTime = System.nanoTime() / 1000000.0;
        if (n <= MAX_RECURSIVE_N) {
            recResult = recFib(n);
        }
        double endTime = System.nanoTime() / 1000000.0;
        double recDuration = endTime - startTime;

        // Non-recursive
        startTime = System.nanoTime() / 1000000.0;
        int nonRecResult = nonRecFib(n);
        endTime = System.nanoTime() / 1000000.0;
        double nonRecDuration = endTime - startTime;

        // Non-recursive using BigInt
        startTime = System.nanoTime() / 1000000.0;
        BigInteger nonRecFibBigIntegerResult = nonRecFibBigInteger(n);
        endTime = System.nanoTime() / 1000000.0;
        double nonRecBigDuration = endTime - startTime;

        // Print the results
        if (n <= MAX_RECURSIVE_N) {
            System.out.println("Recursive fib: " + recResult + " Time: " + numFormat.format(recDuration) + " ms");
        } 
        else {
            System.out.println("Recursive fib: COULD NOT COMPUTE");
        }
        System.out.println("Non-recursive fib: " + nonRecResult + " Time: " + numFormat.format(nonRecDuration) + " ms");
        System.out.println("Non-recursive fib with BigInteger: " + nonRecFibBigIntegerResult + " Time: " + numFormat.format(nonRecBigDuration) + " ms");
    }

}
