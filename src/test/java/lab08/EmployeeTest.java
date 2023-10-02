/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 *
 * Name: Chang Min Bark
 *
 * Project: csci205
 * Package: lab08
 * File: EmployeeTest
 * Description:
 * Tests methods in the Employee class
 * ****************************************
 */
package lab08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private static final double FLOAT_DELTA = 1.0E-10;
    private Employee emp;

    @BeforeEach
    void setUp(){
       // Instantiate a new Employee object for every test. Set up with
       // intentionally incorrect info
       emp = new Employee(10, "Change", "Mine", 123456788, LocalDate.parse("2022-09-18"), 70000);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * A simple test to verify that {@link Employee#changeName} indeed
     * works for both the first and last name
     */
    @Test
    void changeName() {
        assertEquals("Change", emp.getFirstName());
        assertEquals("Mine", emp.getLastName());
        emp.changeName("Chang", "Min");
        assertEquals("Chang", emp.getFirstName());
        assertEquals("Min", emp.getLastName());
    }

    /**
     * A simple test to verify that {@link Employee#raiseSalary(double)} indeed
     * works
     */
    @Test
    void raiseSalary() {
        assertEquals(70000, emp.getSalary());
        emp.raiseSalary(10000);
        assertEquals(80000, emp.getSalary());
    }

    /**
     * A simple test to verify that {@link Employee#equals(Object)} indeed
     * works (testing if two objects that have the same ssNum)
     */
    @Test
    void testEquals() {
        // Create a new employee with same ssNum and test whether they are equal
        Employee emp2 = new Employee(12, "Change", "Mine", 123456788, LocalDate.parse("2022-09-18"), 70000);
        assertEquals(emp, emp2);
    }

    /**
     * Let's test out IDFactory...
     */
    @Test
    void testIDFactory() {
        Employee emp1 = new Employee(100, "A", "B", 100000000, LocalDate.now(), 10000);
        Employee emp2 = new Employee(101, "A", "B", 100000000, LocalDate.now(), 10000);
        // Create a new employee with the same id as the previous
        Employee emp3 = new Employee(101, "A", "B", 100000000, LocalDate.now(), 10000);
        assertEquals(100, emp1.getEmpID());
        assertEquals(101, emp2.getEmpID());
        assertNotEquals(101, emp3.getEmpID());
    }

    /**
     * Tests whether the employee class properly implemented the Payable interface
     */
    @Test
    @DisplayName("Payable - test regular and overtime pay")
    void testPayable() {
        // Test for getPayTo method
        assertEquals(emp.getFirstName() + " " + emp.getLastName(), emp.getPayTo());
        double wage = emp.getSalary() / 52 / 40;
        double overtimeWage = wage * 1.5;

        // Test for standard pay
        assertEquals(wage * 30, emp.calculatePay(30), FLOAT_DELTA);

        // Test for overtime pay
        assertEquals(wage * 40 + overtimeWage * 10, emp.calculatePay(50), FLOAT_DELTA);
    }
}