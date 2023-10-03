/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * Name: Chang Min Bark
 * Date: 9/26/23
 * Time: 7:04 PM
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: HRDBSystem
 * Description:
 * Represents a very basic HR database of employees
 * ****************************************
 */

package lab08;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * HRDBSystem - this is a class that represents the main
 * HR database system.
 */
public class HRDBSystem {

    /** The list of employees in the company */
    private ArrayList<Employee> employees;

    /**
     * A simple constructor that creates a new system.
     */
    public HRDBSystem() {
        this.employees = new ArrayList<>();
    }

    /**
     * Add a new {@link Employee} to the system
     *
     * @param emp - the employee to add
     */
    public void addNewEmployee(Employee emp) {
        this.employees.add(emp);
    }

    /**
     * @return the number of employees in the system
     */
    public int getNumEmployees() { return this.employees.size(); }

    /**
     * @return a {@link List} of {@link Employee} in
     *         the system
     */
    public List<Employee> getListOfEmployees() { return this.employees; }

    /**
     * Get Employee at index location
     */
    public Employee getEmployeeAt(int index) {
        return this.employees.get(index);
    }


    /**
     * Display a basic list of all of the employees in the system
     */
    public void displayEmployees() {
        for (Employee emp : employees) {
            System.out.println(emp.toSimpleString());
        }
    }

    /**
     * Display a list of all the managers in the system
     */
   public void displayManagers() {
       for (Employee emp : this.employees) {
           if (emp instanceof Manager) {
               Manager mgr = (Manager)emp;
               System.out.printf("Manager:     %s %s\n", mgr.getFirstName(),
                                 mgr.getLastName());
               System.out.printf("Department:  %s\n", mgr.getDeptName());
               System.out.printf("# Employees: %d\n", mgr.getListOfManagedEmps().size());
               for (Employee e : mgr.getListOfManagedEmps()) {
                   System.out.println(e.toSimpleString());
               }
           }
       }
   }

    // Members used in our main test program only
    private static HRDBSystem hrdb;
    private static Manager mgr;
    private static Contractor c1;
    private static Contractor c2;

    /**
     *  A Simple test method to try out some stuff
     */
    public static void main(String[] args) throws ManagerException {
        hrdb = new HRDBSystem();

        // Set up all my employees
        initEmployees(hrdb);

        initManagers(hrdb);

        initContractors();

        displayEveryone();

        createAndTestPayables();
    }

    /**
     * Creates and test payables interface-using classes
     */
    private static void createAndTestPayables() {
        // Create a new account object
        Account acc = new Account(4000.0);
        System.out.println(acc);

        // Test out a couple of payments, intentionally throwing an exception
        // with the second payment
        try {
            Employee emp = hrdb.getEmployeeAt(0);
            System.out.println("TEST: Printing a check to employee: " + emp.toSimpleString() + " with salary = $" + emp.getSalary());

            // 40 hrs + 10 hrs overtime
            acc.processCheck(emp, 50);
            System.out.println(acc.writeCheck());

            // Pay our first contractor for 35 hours
            System.out.println("TEST: Printing a check to contractor id: " + c1.getId() + " with wage $" + c1.getHourlyRate());
            acc.processCheck(c1, 35);
            System.out.println(acc.writeCheck());

            // Pay our second contractor for 50 hours - should trigger exception!
            System.out.println("TEST: Printing a check to contractor id: " + c2.getId() + " with wage $" + c2.getHourlyRate());
            acc.processCheck(c2, 100);
            System.out.println(acc.writeCheck());

        }
        catch (InsufficientFundsException e){
            System.out.println(e.getMessage());
        }
        finally {
            // Show account after processing checks
            System.out.println(acc);
        }
    }

    /**
     * Prints and displays information about everyone
     */
    private static void displayEveryone() {
        System.out.println("\n*** LIST OF EMPLOYEES ***");
        hrdb.displayEmployees();
        System.out.println("\n*** MANAGER INFO ***");
        hrdb.displayManagers();

        System.out.println("\n*** CONTRACTORS ***");
        System.out.println(c1);
        System.out.println(c2);
        // IF WE WANT THE OUTPUT TO BE NEAT LIKE THE EMPLOYEE SECTION, WE NEED IT TO HAVE ANOTHER METHOD IN HRDB

        // Sorting employees by ID
        System.out.println("\n*** Sorting employees by ID ***");
        hrdb.sortEmployees(Comparator.comparing(Employee -> Employee.getEmpID()));
        hrdb.displayEmployees();

        // Sorting employees by last name (EITHER WAY AS SHOWN ABOVE AND BELOW WORKS FOR COMPARATOR)
        System.out.println("\n*** Sorting employees by Last Name ***");
        hrdb.sortEmployees(Comparator.comparing(Employee::getLastName));
        hrdb.displayEmployees();
    }

    /**
     * Initializes all contractors
     */
    private static void initContractors() {
        c1 = new Contractor(73, "Builder", "Bob", 342942039, HRUtils.strToDate("2010-02-19"), 50.0);
        c2 = new Contractor(77, "Casper", "Ghost", 343824039, HRUtils.strToDate("2010-06-19"), 55.0);
    }

    /**
     * Initializes managers and adds employees to them
     * @param hrdb The database system that contains employee data
     * @throws ManagerException An exception that is thrown when the ID of the manager is invalid
     */
    private static void initManagers(HRDBSystem hrdb) throws ManagerException {
        mgr = new Manager(11, "Erin", "Jablonski", 867530909,
                                 HRUtils.strToDate("2010-02-19"), 200000, "ENGINEERING");
        hrdb.addNewEmployee(mgr);
        mgr.addEmployee(hrdb.getEmployeeAt(0));
        mgr.addEmployee(hrdb.getEmployeeAt(1));
        mgr.addEmployee(hrdb.getEmployeeAt(3));

        mgr = new Manager(-1, "John", "Bravman", 121230103,
                                  HRUtils.strToDate("2010-02-19"), 300000, "ADMIN");
        hrdb.addNewEmployee(mgr);
        mgr.addEmployee(hrdb.getEmployeeAt(2));
        mgr.addEmployee(hrdb.getEmployeeAt(5));
        mgr.addEmployee(hrdb.getEmployeeAt(6));
    }

    /**
     * Initializes employees
     * @param hrdb The database system that contains employee data
     */
    private static void initEmployees(HRDBSystem hrdb) {
        hrdb.addNewEmployee(new Employee(1, "Brian", "King", 123456789,
                                         HRUtils.strToDate("2010-08-20"), 60000));
        hrdb.addNewEmployee(new Employee(2, "Andrew", "Ng", 101010101,
                                         HRUtils.strToDate("2018-07-15"), 100000));
        hrdb.addNewEmployee(new Employee(200, "Florence", "Machine", 149285729,
                                         HRUtils.strToDate("2014-12-01"), 62500));
        hrdb.addNewEmployee(new Employee(10, "Grace", "Hopper", 122310291,
                                         HRUtils.strToDate("1971-05-25"), 250000));
        hrdb.addNewEmployee(new Employee(10, "Robert", "Randolph", 121212121, LocalDate.now(), 145000));
        hrdb.addNewEmployee(new Employee(10, "Jimi", "Hendrix", 000000001, LocalDate.now(), 250000));
        hrdb.addNewEmployee(new Employee(201, "Nancy", "Wilson", 111111111,
                HRUtils.strToDate("1989-02-10"), 125000));
    }

    /**
     * A simple method to sort the internal list of employees according to the specified
     * {@link Comparator}
     *
     * @param comparator to specify how employees should be arranged
     */
    public void sortEmployees(Comparator<Employee> comparator) {
        employees.sort(comparator);
    }

}
