import java.io.IOException;

/**
 *
 * @author Song
 *
 * Employee is the 'Creator' as it creates the instances of Department
 */
public class Employee implements EmployeeInterface {
    private String department, DOB, hireDate, name;
    private int ID, salary;
    private double retirement;

    public Employee(String empDepartment, String empDOB, String empHireDate, String empName, int empID, int empSalary, double empRetirement){
        department = empDepartment;
        DOB = empDOB;
        hireDate = empHireDate;
        name = empName;
        ID = empID;
        salary = empSalary;
        retirement = empRetirement;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public String getDOB() {
        return DOB;
    }

    @Override
    public String getHireDate() {
        return hireDate;
    }

    @Override
    public String getEmployeeName() {
        return name;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public int getSalary(){
        return salary;
    }

    @Override
    public double getRetirement() {
        return retirement;
    }

    @Override
    public void setDepartment(String dep) {
        department = dep;
    }

    @Override
    public void doAction() throws IOException {
        if(getDepartment().equals("Human Resources")){
            HRDepartment h = new HRDepartment(this);
            h.doFunctions();
        }
    }
}
