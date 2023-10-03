/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/2/2023
 * Time: 4:58 PM
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: Contractor
 *
 * Description:
 * A class that represents a contractor that can be hired by the company
 * ****************************************
 */
package lab08;

import java.time.LocalDate;

public class Contractor implements Payable {

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getSsNum() {
        return ssNum;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * Integer that represents the contractor's id
     */
    private Integer id;

    /**
     * String that represents the contractor's first name
     */
    private String firstName;

    /**
     * String that represents the contractor's last name
     */
    private String lastName;

    /**
     * Integer that represents the contractor's SSN
     */
    private Integer ssNum;

    /**
     * Double that represents the contractor's hourly rate
     */
    private double hourlyRate;

    /** Date contractor was hired */
    private LocalDate hireDate;


    /**
     * @return who the payable person should be
     */
    @Override
    public String getPayTo() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * @return the string that will be on the check's memo
     */
    @Override
    public String getPayMemo() {
        return "Contractor ID: " + this.id + ", Pay Date: " + HRUtils.dateToStr(this.hireDate);
    }

    /**
     * @param numHrs the number of hours worked by the person
     * @return the number of dollars need to be paid to the person
     */
    @Override
    public double calculatePay(double numHrs) {
        return this.hourlyRate * numHrs;
    }


    /**
     * Generate a simple string representing the id and name of the contractor. The actual
     * class of the contractor (i.e. Contractor or any child of it) is printed using the
     * Java reflection API.
     *
     * @return the formatted string
     */
    public String toSimpleString() {
        String s = String.format("%4d: %s %s [%s]",
                this.getId(),
                this.getFirstName(),
                this.getLastName(),
                this.getClass().getSimpleName());
        return s;
    }

    /**
     * Generate a comma-separated-values (CSV) version of the contractor as
     * a String
     *
     * @return a CSV formatted string
     */
    public String toCSVString() {
        String s = this.id + "," + this.lastName + "," + this.firstName;
        s += String.format(",%09d", this.ssNum);
        s += "," + HRUtils.dateToStr(this.hireDate);
        s += String.format(",%.2f", this.hourlyRate);
        s += "," + this.getClass().getSimpleName();
        return s;
    }

    /**
     * The standard toString to show a string representation of a contractor
     *
     * @return a String representing this Employee
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "ID=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssNum=" + ssNum +
                ", hireDate=" + hireDate +
                ", hourlyRate=" + hourlyRate +
                '}';
    }

    /**
     * Constructs a contractor object
     * @param id - The ID of the contractor
     * @param firstName - The contractor's first name
     * @param lastName - The contractor's last name
     * @param ssNum - The contractor's SSN
     * @param hireDate - The hiring date of the contractor
     * @param hRate - The hourly rate of the contractor
     */
    public Contractor(int id, String firstName, String lastName, int ssNum, LocalDate hireDate, double hRate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssNum = ssNum;
        this.hireDate = hireDate;
        this.hourlyRate = hRate;
    }

}