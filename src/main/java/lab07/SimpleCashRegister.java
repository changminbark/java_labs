/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * Name: YOUR NAME
 * Date: 9/20/23
 * Time: 2:40 PM
 *
 * Project: csci205_labs
 * Package: lab07
 * Class: SimpleCashRegister
 * Description:
 * This file is part of a lab exercise to help students learn about exception
 * handling and unit testing. This was partly designed by a former Bucknell CS
 * Professor Rick Zaccone.
 * ****************************************
 */

package lab07;

import java.util.LinkedList;
import java.util.List;

/**
 * The <code>CashRegister</code> class models a very simple cash register. The
 * cash register assumes that it has an unlimited supply of money in its drawer,
 * and thus this is not modeled. It handles the management of one transaction of
 * a time, where a transaction consists of a list of items purchased. This
 * register only logs the amount of each purchase in a single transaction.
 *
 * @author Prof. Rick Zaccone and Brian King
 */

/**
 * A custom type of {@link Exception} used if the {@link SimpleCashRegister} tried
 * to give chance before enough money is collected from the customer
 */
class ChangeException extends Exception {
    public ChangeException(String message) {
        super(message);
    }
}

public class SimpleCashRegister {

    /**
     * Maximum price allowed on an item
     */
    private static final double MAX_ITEM_PRICE = 1000.0;

    /**
     * The total amount of the current transaction
     */
    private double totalTransaction;

    /**
     * Payment collected from the customer so far
     */
    private double paymentCollected;

    /**
     * List of purchases in the current transaction
     */
    private final LinkedList<Double> listOfItemPrices;

    /**
     * Constructs a new cash register
     */
    public SimpleCashRegister() {
        totalTransaction = 0;
        paymentCollected = 0;
        listOfItemPrices = new LinkedList<Double>();
    }

    /**
     * @return the number of purchases in the current transaction
     */
    public int getPurchaseCount() {
        return listOfItemPrices.size();
    }

    /**
     * @return the list of purchases in the current transaction
     */
    public List<Double> getListOfPurchases() {
        return listOfItemPrices;
    }

    /**
     * @return get the total amount of the current transaction
     */
    public double getTransactionTotal() {
        return totalTransaction;
    }

    /**
     * @return the total amount of payment collected for the current transaction
     */
    public double getPaymentCollected() {
        return paymentCollected;
    }

    /**
     * Records the sale of an item in a transaction.
     *
     * @param price the price of the item. Precondition: price >= 0
     * @throws IllegalArgumentException if an invalid price is scanned
     */
    public void scanItem(double price) {
        // Check for a valid price
        if (price < 0.0 || price > MAX_ITEM_PRICE) {
            String msg = String.format("scanItem: Bad Price: $%.2f", price);
            throw new IllegalArgumentException(msg);
        }
        // If this is the first purchase in the transaction, then clear out the
        // list of purchases
        if (totalTransaction == 0) {
            listOfItemPrices.clear();
        }

        listOfItemPrices.add(price);
        totalTransaction += price;
    }

    /**
     * Enters the payment received from the customer; should be called once for
     * each monetary unit moneyType
     *
     * @param moneyType the moneyType of the monetary units in the payment
     * @param unitCount the number of monetary units
     * @throws IllegalArgumentException if unitCount is less than 0
     */
    public void collectPayment(Money moneyType, int unitCount) {
        if (unitCount <= 0) {
            String msg = String.format("scanItem: Bad number of units: %d", unitCount);
            throw new IllegalArgumentException(msg);
        }
        paymentCollected += unitCount * moneyType.getValue();
    }

    /**
     * Computes the change due and resets the machine for the next customer,
     * only if enough money was collected.
     *
     * @return the change due to the customer
     * @throws ChangeException if insufficient payment was collected to cover the transaction
     */
    public double giveChange() throws ChangeException {
        if (paymentCollected < totalTransaction) {
            String msg = String.format("INSUFFICIENT PAYMENT Collected: $%.2f, transaction = $%.2f", paymentCollected, totalTransaction);
            throw new ChangeException(msg);
        }
        double change = paymentCollected - totalTransaction;
        totalTransaction = 0;
        paymentCollected = 0;
        return change;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SimpleCashRegister register))
            return false;

        SimpleCashRegister registerTest = (SimpleCashRegister) o;

        if (Double.compare(registerTest.totalTransaction, register.totalTransaction) != 0)
            return false;
        if (Double.compare(registerTest.getPaymentCollected(), register.getPaymentCollected()) != 0)
            return false;
        return listOfItemPrices.equals(register.listOfItemPrices);
    }

    public static void main(String[] args) {
        SimpleCashRegister myRegister = new SimpleCashRegister();
        myRegister.scanItem(0.55);
        myRegister.scanItem(1.27);
        System.out.println("Purchases: " + myRegister.getListOfPurchases());
        System.out.println("Expected: [0.55, 1.27]");
        myRegister.collectPayment(Money.DOLLAR, 1);
        myRegister.collectPayment(Money.QUARTER, 3);
        myRegister.collectPayment(Money.NICKEL, 2);
        System.out.println("Payment made: " + myRegister.getPaymentCollected());
        System.out.println("Expected: 1.85");

        try {
            double myChange = myRegister.giveChange();
            System.out.println("Change: " + myChange);
            System.out.println("Expected: 0.03");
        } catch (ChangeException e) {
            System.err.println(e.getMessage());
        }

        // Check for an exception to bethrown for an invalid price scanned
        // myRegister.scanItem(-0.50);

        // Check for an exception to be thrown for a bad unitCount in collectPayment
        // myRegister.collectPayment(Money.DIME, -1);
    }

}
