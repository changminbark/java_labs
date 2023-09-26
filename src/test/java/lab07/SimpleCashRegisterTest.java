package lab07;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCashRegisterTest {
    /**
     * Delta constant for checking floating point assertions
     */
    private static final double FLOAT_DELTA = 1.0E-12;

    /**
     * Cash register used in every test
     */
    private SimpleCashRegister register;

    @BeforeEach
    void setUp() {
        this.register = new SimpleCashRegister();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests if register keeps track of no. of items properly
     */
    @Test
    void getPurchaseCount() {
        int result = register.getPurchaseCount();
        // A new register should have no purchases
        assertEquals(0, result);

        // Purchase two items, and verify it to be correct
        register.scanItem(0.55);
        register.scanItem(1.27);
        assertEquals(2, register.getPurchaseCount());
    }

    /**
     * Test if register keeps a list of prices of items scanned properly
     */
    @Test
    void getListOfPurchases() {
        // Create a linkedlist of double values that is identical with register's items
        LinkedList<Double> priceList = new LinkedList<Double>();
        priceList.add(0.55);
        priceList.add(1.27);
        // Purchase two items and verify list of prices to be correct
        register.scanItem(0.55);
        register.scanItem(1.27);
        assertEquals(register.getListOfPurchases(), priceList);
    }

    /**
     * Tests if register properly calculates total price of scanned items
     */
    @Test
    void getTransactionTotal() {
        // Make sure the transaction total is 0
        assertEquals(0.0, register.getTransactionTotal(), FLOAT_DELTA);

        // Scan 2 items and check the transaction total
        register.scanItem(0.55);
        register.scanItem(1.27);
        assertEquals(1.82, register.getTransactionTotal(), FLOAT_DELTA);
    }

    /**
     * Test if register collects the payment properly
     */
    @Test
    void getPaymentCollected() {
        // Make sure initial payment collected is 0
        assertEquals(0.0, register.getPaymentCollected(), FLOAT_DELTA);

        // Make sure payment collected is ten dollars
        register.collectPayment(Money.TEN,1);
        assertEquals(10.0, register.getPaymentCollected(), FLOAT_DELTA);
    }


    /**
     * A test to make sure exceptions are thrown if a bad scan value is passed
     * to the register, and NOT thrown as long as we scan valid items
     */
    @Test
    @DisplayName("scanItem() - exception for bad scan value")
    void scanItemException() {
        // Scan for a negative price
        assertThrows(IllegalArgumentException.class, () -> register.scanItem(-0.5));

        // Scan for a ridiculously large price
        assertThrows(IllegalArgumentException.class, () -> register.scanItem(10000.0));

        // Make sure a good scan does NOT throw any exception
        assertDoesNotThrow(() -> register.scanItem(10.0));
    }

    /**
     * Tests whether register collects payment properly
     */
    @Test
    void collectPayment() {
        // Scans two items and checks whether payment collected is same as five dollars
        register.scanItem(0.55);
        register.scanItem(1.27);
        register.collectPayment(Money.FIVE, 1);
        assertEquals(register.getPaymentCollected(), 5.0, FLOAT_DELTA);
    }

    /**
     * Tests for collectPayment's IllegalArgumentException when unit count is less than 0.
     */
    @Test
    void collectPaymentException() {
        // Scans for two items and checks for IllegalArgumentException where payment unit is negative
        register.scanItem(0.55);
        register.scanItem(1.27);
        assertThrows(IllegalArgumentException.class, () -> register.collectPayment(Money.FIVE, -1));
        // Make sure a good payment does NOT throw any exception
        assertDoesNotThrow(() -> register.collectPayment(Money.TEN,2));
    }

    /**
     * Tests whether the register can give back the proper change to the customer
     * @throws ChangeException if insufficient payment was collected to cover the transaction
     */
    @Test
    void giveChange() throws ChangeException {
        // Scans for two items and checks whether correct change of 3.18 is returned
        collectPayment();
        assertEquals(register.giveChange(),3.18,  FLOAT_DELTA);

    }

    /**
     * Tests for giveChange's ChangeException when payment is less than total cost
     */
    @Test
    void giveChangeException() {
        // Scans for two items and checks whether ChangeException will be thrown as payment is less than total cost
        register.scanItem(0.55);
        register.scanItem(1.27);
        register.collectPayment(Money.DOLLAR, 1);
        assertThrows(ChangeException.class, () -> register.giveChange());
        // Make sure a good giveChange does NOT throw any exception
        register.collectPayment(Money.PENNY, 82);
        assertDoesNotThrow(() -> register.giveChange());
    }
}