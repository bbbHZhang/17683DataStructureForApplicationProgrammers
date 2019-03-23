package lab3;
/**
 * 17683 Data Structures for Application Programmers.
 * Lab 3 Simple Sorting and Stability
 *
 * Comparison of Selection sort and Insertion sort Employee class
 * @author Terry Lee
 */
public class Employee {
    /**
     * first name field.
     */
    private String firstName;
    /**
     * last name field.
     */
    private String lastName;
    /**
     * zip code field.
     */
    private int zipCode;

    /**
     * Constructor with parameters.
     * @param fName first name of employee
     * @param lName last name of employee
     * @param zCode zip code of employee
     */
    public Employee(String fName, String lName, int zCode) {
        firstName = fName;
        lastName = lName;
        zipCode = zCode;
    }

    /**
     * Returns last name of employee.
     * @return string value of last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns zip code of employee.
     * @return string value of zip code
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Returns string representation of employee.
     * @return String value of employee
     */
    @Override
    public String toString() {
        return lastName + ", " + firstName + ", " + zipCode;
    }

}
