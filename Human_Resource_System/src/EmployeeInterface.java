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
     * change % check to ira
     * @param ira - change % check to this
     */
    public void setIRA(double ira);
    
    /**
     * @return - % check to ira
     */
    public double getIRA();

    /**
     * give employee raise
     * @param r - raise % increase
     */
    public void giveRaise(int r);

    /**
     * does the work allocated to the employee's department
     * @throws IOException
     */
    void doAction() throws IOException;
}
