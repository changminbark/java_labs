/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * TODO - Enter your name!
 * Name: TODO
 *
 * Project: csci205
 * Package: lab08
 * File: Employee
 * Description:
 * A very basic abstraction for an employee in a simple HR system
 * ****************************************
 */
package lab08;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * A basic representation for an Employee to be stored in an HR database system
 *
 * @author Brian King
 */
public class Employee {

    /** Collection of unique Employee IDs generated or assigned */
    private static HashSet<Integer> setOfAssignedIDs = new HashSet<>();

    /** Employee id */
    private int empID;

    /** First name */
    private String firstName;

    /** Last name */
    private String lastName;

    /** Social Security number */
    private int ssNum;

    /** Date employee was hired */
    private LocalDate hireDate;

    /** Current salary of the employee */
    private double salary;

    /**
     * Explicit constructor to create new employee
     *
     * @param empID     Employee id
     * @param firstName First name
     * @param lastName  Last name
     * @param ssNum     Social Security Number
     * @param hireDate  Hire date (as {@link LocalDate} object
     * @param salary    Current employee salary
     */
    public Employee(int empID, String firstName, String lastName, int ssNum, LocalDate hireDate, double salary) {
        if (empID <= 0 || setOfAssignedIDs.contains(empID)) {
            Integer genID = generateID();
            setOfAssignedIDs.add(genID);
            this.empID = genID;
        } else {
            this.empID = empID;
            setOfAssignedIDs.add(empID);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssNum = ssNum;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    /**
     * @return the employee id
     */
    public int getEmpID() { return empID; }

    /**
     * @return employee first name
     */
    public String getFirstName() { return firstName; }

    /**
     * @return Last name
     */
    public String getLastName() { return lastName; }

    /**
     * @return Social Security number
     */
    public int getSsNum() {
        return ssNum;
    }

    /**
     * @return {@link LocalDate} employee was hired
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * @return current employee salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Change the name of the employee
     *
     * @param first - New first name
     * @param last - New last name
     */
    public void changeName(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }

    /**
     * Raise the salary by <code>salaryAdj</code> dollars.
     *
     * @param salaryAdj - amount to add to the current salary
     * @return the new salary
     */
    public double raiseSalary(double salaryAdj) {
        this.salary += salaryAdj;
        return this.salary;
    }

    /**
     * Internal helper class method to generate a new ID that does not exist in our
     * set of IDs
     *
     * @return a new ID as an {@link Integer}
     */
    private static Integer generateID() {
        int i = 1;
        while (setOfAssignedIDs.contains(i)) {
            i += 1;
        }
        return Integer.valueOf(i);
    }

    /**
     * Generate a simple string representing the id and name of the employee. The actual
     * class of the employee (i.e. Employee or any child of it) is printed using the
     * Java reflection API.
     *
     * @return the formatted string
     */
    public String toSimpleString() {
        String s = String.format("%4d: %s %s [%s]",
                                 this.getEmpID(),
                                 this.getFirstName(),
                                 this.getLastName(),
                                 this.getClass().getSimpleName());
        return s;
    }

    /**
     * Generate a comma-separated-values (CSV) version of the employee as
     * a String
     *
     * @return a CSV formatted string
     */
    public String toCSVString() {
        String s = this.empID + "," + this.lastName + "," + this.firstName;
        s += String.format(",%09d", this.ssNum);
        s += "," + HRUtils.dateToStr(this.hireDate);
        s += String.format(",%.2f", this.salary);
        s += "," + this.getClass().getSimpleName();
        return s;
    }

    /**
     * The standard toString to show a string representation of an Employee
     *
     * @return a String representing this Employee
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "empID=" + empID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssNum=" + ssNum +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                '}';
    }

    /**
     * Standard method to compare this object to any other arbitrary {@link Object}
     * 
     * @param obj the other {@link Object} to compare
     * @return True if this Employee has the same SS# as another employee
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Employee))
            return false;

        Employee employee = (Employee) obj;

        // if (getEmpID() != employee.getEmpID())
        //     return false;

        return getSsNum() == employee.getSsNum();
    }


}

