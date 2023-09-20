/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 9/19/2023
 * Time: 11:38 PM
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: Transaction
 *
 * Description:
 *
 * ****************************************
 */
package lab06;

/**
 * A clas that encapsulates one transaction. It contains the number of items
 * purchased ni the transaction, and the total cost
 */
public class Transaction {
    /** Number of items in the transaction */
    private int numItems;

    /** Transaction total */
    private double totalCost;

    /**
     * A new transaction will have 0 items and 0 totalCost
     */
    public Transaction() {
        this.numItems = 0;
        this.totalCost = 0;
    }

    /**
     * A method that adds the item's price to the total and increases the number of items.
     * @param price The price of the item being added
     */
    public void addItem(double price){
        this.numItems++;
        this.totalCost += price;
    }

    /** A getter that returns the total cost of the transaction */
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /** A getter that returns the total number of items of the transaction */
    public int getNumItems() {
        return numItems;
    }

    /**
     * A method that returns the values of the transaction object's values
     * @return a string of the values
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "numItems=" + numItems +
                ", totalCost=" + totalCost +
                '}';
    }
}