/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * TODO - Enter your name!
 * Name: YOUR NAME
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: Manager
 * Description:
 * A very simple class representing Manager, a specific type of Employee
 * ****************************************
 */

package lab08;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Checked exception representing any issues that might arise from the Manager
 * class
 */
class ManagerException extends Exception {
    public ManagerException(String message) {
        super(message);
    }
}

public class Manager extends Employee {

    // TODO - ERASE ME AND FOLLOW THE LAB INSTRUCTIONS
    /**
     * Department name that the manager is in charge of
     */
    private String deptName;

    /**
     * List of employees that the manager supervises
     */
    private List<Employee> listOfManagedEmps;

    /**
     * Construct a new Manager of a specified department
     * @param empID The ID of the employee (manager)
     * @param firstName The first name of the employee (manager)
     * @param lastName The last name of the employee (manager)
     * @param ssNum The SSN of the employee (manager)
     * @param hireDate The hiring date of the employee (manager)
     * @param salary The salary of the employee (manager)
     * @param deptName The department the employee (manager) belongs to
     */
    public Manager(int empID, String firstName, String lastName, int ssNum, LocalDate hireDate, double salary, String deptName) {
        super(empID, firstName, lastName, ssNum, hireDate, salary);
        this.deptName = deptName;
        this.listOfManagedEmps = new LinkedList<>();
    }

    public String getDeptName() {
        return deptName;
    }

    public List<Employee> getListOfManagedEmps() {
        return listOfManagedEmps;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * Adds a new employee emp to the manager's list of employees
     * @param emp Employee being added
     * @throws ManagerException An exception thrown when employee already exists in the list
     */
    public void addEmployee(Employee emp) throws ManagerException {
        if (this.listOfManagedEmps.contains(emp)) {
            throw new ManagerException("Employee already exists!");
        } else {
            this.listOfManagedEmps.add(emp);
        }
    }

    /**
     * Removes a new employee emp to the manager's list of employees
     * @param emp Employee being added
     * @throws ManagerException An exception thrown when employee is not in the list
     */
    public void removeEmployee(Employee emp) throws ManagerException {
        if (!this.listOfManagedEmps.contains(emp)) {
            throw new ManagerException("Employee is not in the list!");
        } else {
            this.listOfManagedEmps.remove(emp);
        }
    }

    /**
     * Overrides the toSimpleString method to add the dept name.
     * @return A string that has the name of the manager and the dept name.
     */
    @Override
    public String toSimpleString() {
        return super.toSimpleString() + " of " + this.deptName;
    }
}
