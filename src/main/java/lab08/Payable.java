/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 *
 * Name: Chang Min Bark
 *
 * Project: csci205
 * Package: lab08
 * File: Payable
 * Description:
 * Has an interface class for payable objects
 * ****************************************
 */
package lab08;

/**
 * The Payable interface. Any entity that implements this class
 * provides a uniform interface so that an account can be used
 * to make payments to Payable object.
 */
public interface Payable {
    /**
     * @return who the payable person should be
     */
    public String getPayTo();

    /**
     * @return the string that will be on the check's memo
     */
    public String getPayMemo();

    /**
     * @param numHrs the number of hours worked by the person
     * @return the number of dollars need to be paid to the person
     */
    public double calculatePay(double numHrs);
}
