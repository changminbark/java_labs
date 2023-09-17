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
        this.numerator = 0;
        this.denominator = 0;
        this.isValid = false;
        //TODO - FINISH ME!
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
        this.numerator = 0;
        this.denominator = 0;
        this.isValid = false;

        //TODO - FINISH ME!
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
        //TODO - FINISH ME!
        return new Fraction(0,0);
    }

    /**
     * @return a new Fraction representing the reciprocal of this fraction
     */
    public Fraction reciprocal() {
        //TODO - FINISH ME!
        return new Fraction(0,0);
    }

    /**
     * @return a new Fraction representing the negative of this fraction
     * (multiply the numerator or denominator by -1)
     */
    public Fraction negate() {
        //TODO - FINISH ME!
        return new Fraction(0,0);
    }

    /**
     * @return this fraction as a single floating point number (e.g. 1/2 ==> 0.5)
     */
    public double getDecimal() {
        //TODO - FINISH ME!
        return Double.MAX_VALUE;
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
        //TODO - FINISH ME!
        return new Fraction(0,0);
    }

    /**
     * @return the product of this fraction multiplied to {@code otherFrac}
     * as a new Fraction, simplified
     */
    public Fraction multiply(Fraction otherFrac) {
        //TODO - FINISH ME!
        return new Fraction(0,0);
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
        //TODO - FINISH ME!
        return new Fraction(0,0);
    }

    /**
     * Is this fraction greater than {@code otherFrac}?
     *
     * @param otherFrac the other Fraction to compare to
     * @return true if this fraction is greater than {@code otherFrac}
     */
    public boolean isGreaterThan(Fraction otherFrac) {
        //TODO - FINISH ME!
        return false;
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
        //TODO - FINISH ME!
        return false;
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
        // TODO - FINISH ME!
        return "?/?";
    }
}
