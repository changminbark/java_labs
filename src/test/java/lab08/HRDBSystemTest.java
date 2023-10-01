/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 *
 * Name: Chang Min Bark
 *
 * Project: csci205
 * Package: lab08
 * File: HRDBSystemTest
 * Description:
 * Tests methods in the HRDBSystem class
 * ****************************************
 */
package lab08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HRDBSystemTest {

    private HRDBSystem testSystem;
    private Employee emp1;
    private Employee emp2;

    /**
     * Make sure that adding an {@link Employee} to the {@link HRDBSystem} indeed
     * does add the employee to the list
     */
    @Test
    void addNewEmployee() {
        // Create a new HRDBSystem object
        testSystem = new HRDBSystem();

        // Tests the new object has no employees
        assertEquals(0,testSystem.getNumEmployees());

        // Create two new employee objects to add to the testSystem
        emp1 = new Employee(1, "Brian", "King", 123456789, HRUtils.strToDate("2010-08-20"), 70000);
        emp2 = new Employee(2, "Grace", "Hopper", 402040302, HRUtils.strToDate("2015-07-02"), 65000);

        // Add employees to HRDBSystem object
        testSystem.addNewEmployee(emp1);
        testSystem.addNewEmployee(emp2);

        // Tests the new object has 2 employees
        assertEquals(2,testSystem.getNumEmployees());
    }
}