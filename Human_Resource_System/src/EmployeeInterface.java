import java.io.IOException;

public interface EmployeeInterface {
    /**
     * @return department
     */
    String getDepartment();

    /**
     * @return date of birth
     */
    String getDOB();
    /**
     * @return hired date
     */
    String getHireDate();

    /**
     * @return name
     */
    String getEmployeeName();

    /**
     * @return ID
     */
    int getID();

    /**
     * @return ID
     */
    int getSalary();

    /**
     * @return retirement savings
     */
    double getRetirement();

    /**
     * change department
     * @param dep - change department to this
     */
    void setDepartment(String dep);

    /**
     * does the work allocated to the employee's department
     * @throws IOException
     */
    void doAction() throws IOException;
}
