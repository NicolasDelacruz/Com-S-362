import java.util.Scanner;

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

    public String getEmployeeName(){
        return name;
    }

    public int getID(){
        return ID;
    }

    @Override
    public int getSalary(){
        return salary;
    }

    public void doAction(){
        printAvailableActions();
        String command = "";

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        while (!command.equals("Q")){
            System.out.println("What action would you like to do? ");
            command = reader.nextLine();

            if(command.equals("C")){
                Complaint c = new Complaint("");
                c.viewComplaints();
            }
            else if(command.equals("RC")){
                System.out.println("Please type the complaint below. This is anonymous.");
                String comp = reader.nextLine();
                Complaint c = new Complaint(comp);
                c.addComplaint();
                System.out.println("The complaint has been filed. Our team will look into this issue.");
            }
        }

        System.out.println("Logging off account: " + name);

        //once finished
        reader.close();
    }

    public void printAvailableActions(){
        System.out.println("Hello, "+ name);
        System.out.println("View Complaints [C], Report Complaints [RC], Quit [Q]");
    }
}
