/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 *
 * Name: Chang Min Bark
 *
 * Project: csci205
 * Package: lab08
 * File: ManagerTest
 * Description:
 * Tests methods in the Manager class
 * ****************************************
 */

package lab08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Manager mgr;
    private Employee emp1;
    private Employee emp2;

    @BeforeEach
    void setUp() {
        mgr = new Manager(1, "Brad", "Putnam", 123456789, HRUtils.strToDate("2022-07-15"), 100000.0, "ENGINEERING");
        emp1 = new Employee(1, "Brian", "King", 123456789, HRUtils.strToDate("2010-08-20"), 70000);
        emp2 = new Employee(2, "Grace", "Hopper", 402040302, HRUtils.strToDate("2015-07-02"), 65000);
    }

    /**
     * Make sure that adding an {@link Employee} to the {@link Manager} indeed
     * does add the employee to the list
     */
    @Test
    @DisplayName("addEmployee() - adding employees to a manager")
    void addEmployee() throws ManagerException {
        // Set up a list of what we expect...
        ArrayList<Employee> expectedList = new ArrayList<>();

        // First, verify that both lists are empty
        assertEquals(expectedList, mgr.getListOfManagedEmps());

        // Add a couple employees to manager
        mgr.addEmployee(emp1);
        mgr.addEmployee(emp2);

        // Add the same employees to our own list copy
        expectedList.add(emp1);
        expectedList.add(emp2);

        // Both lists should still be equivalent
        assertEquals(expectedList, mgr.getListOfManagedEmps());

        // Tests for ManagerException being thrown when the employee is already in list
        assertThrows(ManagerException.class, () -> mgr.addEmployee(emp1));
    }

    /**
     * Make sure that removing an {@link Employee} to the {@link Manager} indeed
     * does remove the employee to the list
     */
    @Test
    void removeEmployee() throws ManagerException {
        // Set up a list of what we expect...
        ArrayList<Employee> expectedList = new ArrayList<>();

        // First, verify that both lists are empty
        assertEquals(expectedList, mgr.getListOfManagedEmps());

        // Add a couple employees to manager
        mgr.addEmployee(emp1);
        mgr.addEmployee(emp2);

        // Add the same employees to our own list copy
        expectedList.add(emp1);
        expectedList.add(emp2);

        // Both lists should still be equivalent
        assertEquals(expectedList, mgr.getListOfManagedEmps());

        // Remove emp2 from list copy
        expectedList.remove(emp2);

        // Remove emp2 from manager list
        mgr.removeEmployee(emp2);

        // Both lists should still be equivalent
        assertEquals(expectedList, mgr.getListOfManagedEmps());

        // Tests for ManagerException being thrown when the employee is not in list
        assertThrows(ManagerException.class, () -> mgr.removeEmployee(emp2));
    }
}