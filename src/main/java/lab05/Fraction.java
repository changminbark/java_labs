/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * Name: YOUR NAME
 * Date: 9/11/23
 * Time: 3:51 PM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: Fraction
 * Description:
 * A class that represent a Fraction of int values.
 * It also provides a collection of basic math operations
 * ****************************************
 */

package lab05;

import java.util.Scanner;

import static java.lang.Integer.*;

/**
 * This class encapsulates a single fraction in the form of
 * x/y, where x and y are restricted to integers
 */
class Fraction {
    /** The numerator of the fraction */
    private final int numerator;

    /** The denominator of the fraction */
    private final int denominator;

    /** Is this fraction valid? A valid fraction must have both numbers set properly and
     * can not have a 0 in the denominator */
    private final boolean isValid;



    /**
     * Construct a new Fraction from two integer values. If the denominator
     * is zero, the fraction is not considered valid (@code isValid} is
     * {@code false}
     *
     * @param numerator an {@code int} numerator of the fraction
     * @param denominator an {@code int} denominator of the fraction
     */
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        // If denom is 0, mark that it is not a valid fraction
        if (this.denominator == 0){
            this.isValid = false;
        } else {
            this.isValid = true;
        }

    }

    /**
     * Construct a new Fraction from a string in the form "a / b". There may or
     * may not be whitespace in between tokens in the string. If the string
     * could not be parsed, then set isValid to false and print an error message.
     * If it could be parsed, but the denominator is 0, just set isValid to false.
     * Otherwise isValid is true
     *
     * @param strFraction a String that should be of the form a/b. If there is
     *                    any problem parsing the fraction, then the fraction
     *                    defaults to 1/1 and a  message idisplayed
     */
    public Fraction(String strFraction) {

        // Checks whether string is a valid string representation of a fraction
        if (strFraction.contains("/") & !(strFraction.contains("."))){
            String[] fracList = strFraction.split("/");

            // Check if there are only two terms in the fraction
            if (fracList.length == 2){
                this.numerator = Integer.parseInt(fracList[0].trim());
                this.denominator = Integer.parseInt(fracList[1].trim());

                // Check if the denominator is 0
                if (denominator == 0) {
                    this.isValid = false;
                    System.out.println("WRONG INPUT: Denominator can't be 0!");
                } else {
                    this.isValid = true;
                }
            } else {
                this.numerator = 1;
                this.denominator = 1;
                this.isValid = false;
                System.out.println("WRONG INPUT: Fraction contains more than 2 terms!");
            }
        } else {
            this.numerator = 1;
            this.denominator = 1;
            this.isValid = false;
            System.out.println("WRONG INPUT: Incorrect format for fraction: " + strFraction);
        }

    }

    /** @return the numerator in the fraction */
    public int getNumerator() { return numerator; }

    /** @return the denominator in the fraction */
    public int getDenominator() { return denominator; }

    /** @return the state of the {@link #isValid} flag */
    public boolean isValid() { return this.isValid; }

    /**
     * Return a new copy of this fraction but in most simplified terms
     *
     * @return a new Fraction that represents this Fraction but in simplest terms.
     * For example, 3/6 would return a new Fraction 1/2. 1/2 would also return 1/2.
     */
    public Fraction getSimplifiedFraction() {

        // Find the greatest common divisor and then return the numer and denom divided by the GCD
        int greatestCD = euclideanAlgo(max(numerator, denominator), min(numerator, denominator));

        return new Fraction(numerator/greatestCD,denominator/greatestCD);
    }

    /**
     * Return the greatest common divisor of the numerator and denominator
     *
     * @return the greatest common divisor of the numerator and denominator
     */
    private int euclideanAlgo(int inputNum1, int inputNum2) {
        int mod = 1;
        int num1 = inputNum1;
        int num2 = inputNum2;
        // If either number is a 0 (if numerator is 0), then the GCD is 1
        if (num1 == 0 | num2 == 0) {
            return 1;
        }
        // This is the actual Euclidean Algorithm to find the GCD
        while (mod != 0) {
            mod = num1 % num2;
            num1 = num2;
            num2 = mod;
        }
        return num1;
    }

    /**
     * @return a new Fraction representing the reciprocal of this fraction
     */
    public Fraction reciprocal() {
        return new Fraction(denominator,numerator);
    }

    /**
     * @return a new Fraction representing the negative of this fraction
     * (multiply the numerator or denominator by -1)
     */
    public Fraction negate() {
        return new Fraction(numerator*-1,denominator);
    }

    /**
     * @return this fraction as a single floating point number (e.g. 1/2 ==> 0.5)
     */
    public double getDecimal() {
        double decimal = (double) numerator / denominator;
        return decimal;
    }

    /**
     * Perform simple Fraction addition, returning a new Fraction that represents
     * this added to {@code otherFrac} in simplified form
     *
     * @param otherFrac the other Fraction that will be added to this one
     * @return a new Fraction that represents the addition of this Fraction to
     *         otherFrac, simplified
     */
    public Fraction add(Fraction otherFrac) {
        // Multiplies each numerator with other fraction's denom, adds them, and divide them by the new denominator, which is then simplified
        int newNum1 = this.numerator * otherFrac.denominator;
        int newNum2 = otherFrac.numerator * this.denominator;
        int newNum = newNum1 + newNum2;
        int newDen = this.denominator * otherFrac.denominator;
        int greatestCD = euclideanAlgo(max(newNum, newDen), min(newNum, newDen));

        return new Fraction(newNum/greatestCD, newDen/greatestCD);
    }

    /**
     * @return the product of this fraction multiplied to {@code otherFrac}
     * as a new Fraction, simplified
     */
    public Fraction multiply(Fraction otherFrac) {
        return new Fraction(numerator* otherFrac.numerator,denominator* otherFrac.denominator);
    }

    /**
     * Multiply this fraction with a specified numerator and denominator
     *
     * @param numerator the numerator to multiply to this numerator
     * @param denominator the denominator to multiple to this denominator
     *
     * @return a new Fraction that is this fraction multiplied by a provided
     * numerator and denominator, in simplified form
     */
    public Fraction multiply(int numerator, int denominator) {
        return new Fraction(this.numerator*numerator,this.denominator*denominator);
    }

    /**
     * Is this fraction greater than {@code otherFrac}?
     *
     * @param otherFrac the other Fraction to compare to
     * @return true if this fraction is greater than {@code otherFrac}
     */
    public boolean isGreaterThan(Fraction otherFrac) {
        double frac1 = numerator/denominator;
        double frac2 = otherFrac.numerator/ otherFrac.denominator;
        if (frac1>frac2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Is this Fraction equal to {@code otherFrac}?
     * NOTE: 1/2 == 3/6 (i.e numerators do not need
     * to be the same)
     *
     * @return true if this fraction is equal to otherFrac,
     * false otherwise.
     */
    public boolean isEqualTo(Fraction otherFrac) {
        double frac1 = numerator/denominator;
        double frac2 = otherFrac.numerator/ otherFrac.denominator;
        if (frac1==frac2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return a String in the form of x/y, where x is the numerator and
     *         y is the denominator.
     *         However:
     *         - if the numerator is 0, then return "0".
     *         - if the denominator is 0, then return "ERROR - divide by 0"
     *         - if the numerator == denominator, then return "1"
     *         - otherwise, return numerator/denominator
     */
    @Override
    public String toString() {
        if (numerator == 0) {
            return "0";
        }
        if (denominator == 0) {
            return "ERROR - divide by 0";
        }
        if (numerator == denominator) {
            return "1";
        }
        return (Integer.toString(numerator) + "/" + Integer.toString(denominator));
    }
}
