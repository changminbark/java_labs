/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 *
 * Name: Chang Min Bark
 *
 * Project: csci205
 * Package: lab08
 * File: AccountTest
 * Description:
 * Tests methods in the Account class
 * ****************************************
 */

package lab08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    /**
     * Threshold for checking double values for equality
     */
    private static final double FLOAT_DELTA = 1.0E-10;

    /**
     * Initial deposit
     */
    private static final double INIT_DEPOSIT = 2000.0;

    /**
     * Initial hourly wage of employee
     */
    private static final double INIT_HOURLY_WAGE = 30;

    /**
     * A common Account object to use for all tests
     */
    private Account acct;

    /**
     * A common employee for dealing with an example Payable
     */
    private Employee emp;

    @BeforeEach
    void setUp() {
        // Set up an account to have 2000 initial balance
        this.acct = new Account(INIT_DEPOSIT);

        // Set up the test employee to be paid $50 / hour
        this.emp = new Employee(1, "Brian", "King", 1233459876, LocalDate.now(), INIT_HOURLY_WAGE * 40 * 52);

    }

    /**
     * Checks whether the account is being credited properly.
     */
    @Test
    void credit() {
        assertEquals(INIT_DEPOSIT, acct.getBalance(), FLOAT_DELTA);
        this.acct.credit(1000);
        assertEquals(INIT_DEPOSIT + 1000, acct.getBalance(), FLOAT_DELTA);
    }

    /**
     * Checks whether the account is being debited properly
     */
    @Test
    void debit() throws InsufficientFundsException {
        assertEquals(INIT_DEPOSIT, acct.getBalance(), FLOAT_DELTA);
        this.acct.debit(250.0);
        assertEquals(INIT_DEPOSIT - 250.0, acct.getBalance(), FLOAT_DELTA);
    }

    /**
     * Checks whether {@link InsufficientFundsException}  is thrown when a bad debit occurs
     */
    @Test
    @DisplayName("InsufficientFundsException from a bad debit")
    void debitException() {
        assertThrows(InsufficientFundsException.class, () -> acct.debit(INIT_DEPOSIT + 1));
    }

    /**
     * Verify that the check writing interface for {@link Payable} objects
     * is working. This will also verify the {@link Account#getBalance()} method
     */
    @Test
    void processCheck() throws InsufficientFundsException {
        int hoursWorked = 40;
        acct.processCheck(emp, hoursWorked);
        double expected = INIT_DEPOSIT - (INIT_HOURLY_WAGE * hoursWorked);
        assertEquals(expected, acct.getBalance(), FLOAT_DELTA);

        // Let's verify the check amount here
        assertEquals(INIT_HOURLY_WAGE * hoursWorked, acct.getCheckAmount(), FLOAT_DELTA);
    }

    /**
     * Checks whether the writeCheck() method properly prints the information in a check
     */
    @Test
    void writeCheck() throws InsufficientFundsException {
        acct.processCheck(emp, 40);
        String sResult = acct.writeCheck();
        assertTrue(sResult != null);
        assertFalse(sResult.equals(""));
        System.out.println(acct.writeCheck());
    }
}