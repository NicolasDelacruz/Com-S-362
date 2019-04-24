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
    private double retirement, ira;

    public Employee(String empDepartment, String empDOB, String empHireDate, String empName, int empID, int empSalary, double empRetirement){
        department = empDepartment;
        DOB = empDOB;
        hireDate = empHireDate;
        name = empName;
        ID = empID;
        salary = empSalary;
        retirement = empRetirement;
        ira = 0;
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

    public void setSalary(int newSalary){
        salary = newSalary;
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
    public void setIRA(double ira)
    {
    	this.ira = ira;
    }
    
    @Override
    public double getIRA()
    {
    	return ira;
    }

    public void giveRaise(int p)
    {
        salary += salary * p * .01;
    }

    @Override
    public void doAction() throws IOException {
        employeeDatabase e = new employeeDatabase();
        if(e.getEmployee(this.getID()) == null) {
            e.addEmployee(this);
        }
        if(getDepartment().equals("Human Resources")){
            HRDepartment hrEmployee = new HRDepartment(this);
            hrEmployee.doFunctions();
        }
        else if(getDepartment().equals("Event Management")){
            EventDepartment eventEmployee = new EventDepartment(this);
            eventEmployee.doFunctions();
        }
    }
}
