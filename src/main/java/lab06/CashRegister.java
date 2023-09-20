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
 * Class: CashRegister
 *
 * Description:
 *
 * ****************************************
 */
package lab06;

/**
 * The different states that a cash register can be in
 */
enum CashRegisterState {
    NOT_READY, // Not ready for a shift
    READY,  // Register ready to start a transaction
    SCANNING, // Processing a transaction
    RECEIVE_PMT // Receiving payment for a transaction
}

/**
 * A simple class to encapsulate a cash register
 */
public class CashRegister {

    /** The name of the cash register */
    private String registerName;

    /** The amount of cash the register is current holding */
    private double cashInDrawer;

    /** The current transaction, or null if there is no transaction taking place */
    private Transaction currentTransaction;

    /** The total amount of money collected toward the transaction */
    private double paymentReceived;

    /** The current state of the cash register */
    private CashRegisterState state;

    /**
     * Initialize a default, empty cash register
     */
    public CashRegister() {
        this.cashInDrawer = 0;
        this.currentTransaction = null;
        this.paymentReceived = 0;
        this.registerName = "Default";
        this.state = CashRegisterState.NOT_READY;
    }

    /**
     * Initialize an empty cash register with a name
     */
    public CashRegister(String name) {
        this.cashInDrawer = 0;
        this.currentTransaction = null;
        this.paymentReceived = 0;
        this.registerName = name;
        this.state = CashRegisterState.NOT_READY;
    }

    /** A getter that returns the register's name */
    public String getName() {
        return registerName;
    }

    /** A setter that sets the register's name */
    public void setName(String registerName) {
        this.registerName = registerName;
    }

    /** A getter that returns the register's state */
    public CashRegisterState getState() {
        return state;
    }

    /** A getter that returns the amount of cash in the drawer */
    public double getCashInDrawer() {
        return cashInDrawer;
    }

    /** A boolean method that checks if register's state is NOT READY */
    public boolean isNotReady(){
        if (this.state == CashRegisterState.NOT_READY) {
            return true;
        } else {
            return false;
        }
    }

    /** A boolean method that checks if register's state is READY */
    public boolean isReady(){
        if (this.state == CashRegisterState.READY) {
            return true;
        } else {
            return false;
        }
    }

    /** A boolean method that checks if register's state is SCANNING */
    public boolean isScanning(){
        if (this.state == CashRegisterState.SCANNING) {
            return true;
        } else {
            return false;
        }
    }

    /** A boolean method that checks if register's state is RECEIVE PMT */
    public boolean isReceivingPmt(){
        if (this.state == CashRegisterState.RECEIVE_PMT) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method that signs a person in if the register's state is NOT READY
     * @param initCash the initial amount of cash in the register
     * @return boolean value that signifies if person signed in
     */
    public boolean signIn(double initCash) {
        this.cashInDrawer = initCash;
        if (this.state == CashRegisterState.NOT_READY) {
            this.state = CashRegisterState.READY;
            return true;
        }
        return false;
    }

    /**
     * A method that returns the register's amount of money and signs the person out
     * @return
     */
    public double signOut() {
        if (this.state == CashRegisterState.READY) {
            double cashRemaining = this.cashInDrawer;
            this.cashInDrawer = 0;
            this.state = CashRegisterState.NOT_READY;
            return cashRemaining;
        } else {
            return -1.0;
        }
    }

    /**
     * A method that scans an item into the transaction (updates transaction values)
     * @param price the price of the item being added/scanned
     * @return a boolean value that represents if the item was successfully added
     */
    public boolean scanItem(double price) {
        if (this.state == CashRegisterState.READY) {
            this.state = CashRegisterState.SCANNING;
            this.currentTransaction = new Transaction();
            }
        if (this.state == CashRegisterState.SCANNING) {
            this.currentTransaction.addItem(price);
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method that collects the payment and calculates the change (if state is in RECEIVE PMT)
     * @param payment The payment made by the consumer
     * @return The amount of change the consumer needs to get
     */
    public double collectPayment(double payment) {
        if (this.state == CashRegisterState.SCANNING) {
            this.state = CashRegisterState.RECEIVE_PMT;
        }
        if (this.state == CashRegisterState.RECEIVE_PMT) {
            double amountDue = payment - this.currentTransaction.getTotalCost();
            if (amountDue >= 0){
                this.state = CashRegisterState.READY;
                this.cashInDrawer += this.currentTransaction.getTotalCost();
            } else {
                this.cashInDrawer += payment;
                this.currentTransaction.setTotalCost(this.currentTransaction.getTotalCost() - payment);
            }
            return amountDue;
        } else {
            return Double.MIN_VALUE;
        }
    }

    /**
     * A method that returns the amount owed in the current transaction
     * @return The amount owed in the current transaction
     */
    public double getAmountOwed() {
        if (this.state == CashRegisterState.SCANNING) {
            return this.currentTransaction.getTotalCost();
        } else {
            return 0.0;
        }
    }

    /**
     * A method that returns the values of the cashregister object's values
     * @return a string of the values
     */
    @Override
    public String toString() {
        return "CashRegister{" +
                "registerName='" + registerName + '\'' +
                ", cashInDrawer=" + cashInDrawer +
                ", currentTransaction=" + currentTransaction +
                ", paymentReceived=" + paymentReceived +
                ", state=" + state +
                '}';
    }
}