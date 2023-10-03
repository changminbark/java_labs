/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/2/2023
 * Time: 12:27 AM
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: Account
 *
 * Description:
 *
 * ****************************************
 */
package lab08;

import java.text.DecimalFormat;

/**
 * An exception that is thrown when there is not enough funds to pay the person using the HRDBSystem.
 * The message thrown will show how much is being withdrawn and how much is available in the account.
 */
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}

public class Account {

    /**
     * Decimal formatter
     */
    private static final DecimalFormat decfor = new DecimalFormat("0.00");


    /**
     * the current balance of the account
     */
    private double balance;
    /**
     * the last Payable object that was paid
     */
    private Payable lastPayee;
    /**
     * the last amount that was paid to the lastPayee
     */
    private double lastAmountPaid;

    /**
     * A constructor that initializes the values of the account
     * @param initialBal - The initial balance in the account
     */
    public Account(double initialBal) {
        this.balance = initialBal;
        this.lastPayee = null;
        this.lastAmountPaid = 0;
    }

    /**
     * Gets the balance of the account
     * @return the double value of the account's balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Credit {@code amount} money into the account
     *
     * @param amount - The amount to be added to the account
     */
    public void credit(double amount) {
        this.balance += amount;
    }

    /**
     * Debit the account with {@code amount} money
     *
     * @param amount - the amount of money to remove from account
     * @throws InsufficientFundsException if funds were not available to debit from the balance
     */
    public void debit(double amount) throws InsufficientFundsException {
        if (this.balance < amount) {
            throw new InsufficientFundsException((String.format("INSUFFICIENT FUNDS! Required: $%.2f, Available: $%.2f", amount, this.balance)));
        }
        this.balance -= amount;
    }

    /**
     * We actually debit the account based on how much the payee needs to be paid
     *
     * @param payee - The person getting paid
     * @param hoursBilled - The number of hours the person getting paid worked for
     */
    public void processCheck(Payable payee, double hoursBilled) throws InsufficientFundsException {
        // Amount payee needs to get paid
        double amt = payee.calculatePay(hoursBilled);
        debit(amt);
        this.lastPayee = payee;
        this.lastAmountPaid = amt;
    }

    /**
     * This calculates the last amount of check that was paid.
     * @return The amount of the last check that was paid.
     */
    public double getCheckAmount() {
        return this.lastAmountPaid;
    }

    /**
     * Prints the contents of the check
     * @return a string representing the contents of the check
     */
    public String writeCheck() {
        if (this.lastPayee == null) {
            return "";
        }
        return "Pay to:      " + lastPayee.getPayTo() + "\n" + "Pay memo:      " + lastPayee.getPayMemo() + "\n" + "Pay amount:      $" + decfor.format(this.lastAmountPaid) + "\n";
    }

    /**
     * Print out the current account balance
     * @return the current account balance
     */
    @Override
    public String toString() {
        return "Account Balance: $" + decfor.format(this.balance) + "\n";
    }
}

