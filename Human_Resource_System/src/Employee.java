public class Employee implements EmployeeInterface {
    private String department, DOB, hireDate, name;
    int ID, salary;

    public Employee(String empDepartment, String empDOB, String empHireDate, String empName, int empID, int empSalary){
        department = empDepartment;
        DOB = empDOB;
        hireDate = empHireDate;
        name = empName;
        ID = empID;
        salary = empSalary;

    }


    public String getDepartment(){
        return department;
    }

    public String getDOB(){
        return DOB;
    }

    public String getHireDate(){
        return hireDate;
    }

    public String getEmplyeeName(){
        return name;
    }

    public int getID(){
        return ID;
    }

    @Override
    public int getSalary(){
        return salary;
    }
}
