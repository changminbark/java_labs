/**
 * Simple program to recursively compute factorial
 *
 * Date: 2023-fall
 * 
 * Updated 2022-fall to introduce BigInteger
 *
 * @author BRK
 */

package lab02;

import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {

    /**
     * Recursive function to compute the factorial of a number
     *
     * @param num - number to compute
     * @return the factorial of num
     */
    public static long fac(long num) {
        if (num == 0) {                 // Our base case: num = 0
            return 1;
        }
        return num * fac(num - 1);      // Our recursive call
    }

    /**
     * Recursive function to compute the factorial of a number with BigInteger
     * 
     * @param args
     */
    public static BigInteger facBigInteger(BigInteger num) {
        if (num.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        return num.multiply(facBigInteger(num.subtract(BigInteger.ONE)));
    }

    /**
     * A simple main program
     */
    public static void main(String[] args) {
        BigInteger bigInt;

        bigInt = new BigInteger("123");

        bigInt = BigInteger.valueOf(123);

        
        System.out.print("Please enter a number: ");
        Scanner in = new Scanner(System.in);
        long num = in.nextLong();

        // Compute results with a primitive long and with BigInteger
        long answer = fac(num);
        BigInteger bigIntAnswer = facBigInteger(BigInteger.valueOf(num));

        // Report results
        System.out.format("fac(%d) = %d%n", num, answer);
        System.out.format("facBigInteger(%d) = %s%n", num, bigIntAnswer);
        System.exit(0);
    }
}
