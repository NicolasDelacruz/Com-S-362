import java.util.Scanner;

public class HRDepartment implements DepartmentInterface {
    String department;
    String employeeName;

    public HRDepartment(Employee e){
        department = "Human Resources";
        employeeName = e.getEmployeeName();
    }

    @Override
    public void doFunctions() {
        printAvailableOptions();
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

        System.out.println("Logging off account: " );

        //once finished
        reader.close();
    }

    @Override
    public String getDepartmentName() {
        return department;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("Hello, "+ employeeName);
        System.out.println("View Complaints [C], Report Complaints [RC], Quit [Q]");
    }
}
