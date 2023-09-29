/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * Name: YOUR NAME
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
     * Display a list of all of the managers in the system
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

    /**
     *  A Simple test method to try out some stuff
     */
    public static void main(String[] args) throws ManagerException {
        HRDBSystem hrdb = new HRDBSystem();

        hrdb.addNewEmployee(new Employee(1, "Brian", "King", 123456789,
                                         HRUtils.strToDate("2010-08-20"), 60000));
        hrdb.addNewEmployee(new Employee(2, "Andrew", "Ng", 101010101,
                                         HRUtils.strToDate("2018-07-15"), 100000));
        hrdb.addNewEmployee(new Employee(200, "Florence", "Machine", 149285729,
                                         HRUtils.strToDate("2014-12-01"), 62500));
        hrdb.addNewEmployee(new Employee(10, "Grace", "Hopper", 122310291,
                                         HRUtils.strToDate("1971-05-25"), 250000));

       Manager mgr = new Manager(11, "Erin", "Jablonski", 867530909,
                                 HRUtils.strToDate("2010-02-19"), 200000, "ENGINEERING");
       hrdb.addNewEmployee(mgr);
       mgr.addEmployee(hrdb.getEmployeeAt(0));
       mgr.addEmployee(hrdb.getEmployeeAt(1));
       mgr.addEmployee(hrdb.getEmployeeAt(3));

        hrdb.addNewEmployee(new Employee(10, "Robert", "Randolph", 121212121, LocalDate.now(), 145000));
        hrdb.addNewEmployee(new Employee(10, "Jimi", "Hendrix", 000000001, LocalDate.now(), 250000));
        hrdb.addNewEmployee(new Employee(201, "Nancy", "Wilson", 111111111,
                                         HRUtils.strToDate("1989-02-10"), 125000));

       mgr = new Manager(-1, "John", "Bravman", 121230103,
                                 HRUtils.strToDate("2010-02-19"), 300000, "ADMIN");
       hrdb.addNewEmployee(mgr);
       mgr.addEmployee(hrdb.getEmployeeAt(2));
       mgr.addEmployee(hrdb.getEmployeeAt(5));
       mgr.addEmployee(hrdb.getEmployeeAt(6));

        System.out.println("*** LIST OF EMPLOYEES ***");
        hrdb.displayEmployees();
//        System.out.println("*** MANAGER INFO ***");
//        hrdb.displayManagers();
    }

}
