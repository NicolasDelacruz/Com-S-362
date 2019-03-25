import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * Employee is the 'Information Expert' as it knows about Employee, the functions available to be done by department, and the Databases data
 */
public class HRDepartment implements DepartmentInterface {
    JobPostDatabase jpdb;
    String department;
    Employee tempEmp;
    int id;

    public HRDepartment(@NotNull Employee e){
        department = "Human Resources";
        tempEmp = e;
        id = e.getID();
        jpdb = new JobPostDatabase();
    }

    @Override
    public void doFunctions() throws IOException {
        printAvailableOptions();
        String command = "";

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        while (!command.equals("Q")){
            System.out.println("What action would you like to do? ");
            command = reader.nextLine();

            if(command.equals("VC")){
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
            // Use case for calculate retirement below here
            else if(command.equals("VR")){
                double retirement = tempEmp.getRetirement();
                int salary = tempEmp.getSalary();
                System.out.println("Retirement to date: "+ retirement);

                retirement = retirement + (salary*0.1);
                System.out.println("Retirement at end of the year: "+ retirement);
            }
            else if(command.equals("TR")){
                //ASK USER FOR WHO NEEDS TO BE TRANSFERRED
                System.out.println("What employee ID is being transferred?");
                int empID = Integer.parseInt(reader.nextLine());

                System.out.println("What department is employee ID is being transferred?");
                String newDepartment = reader.nextLine();

                employeeDatabase ed = new employeeDatabase();
                if(ed.getEmployee(empID) != null) {
                    Employee emp = ed.getEmployee(empID);
                    ed.removeEmployee(emp);
                    emp.setDepartment(newDepartment);
                    ed.addEmployee(emp);
                    System.out.println(emp.getEmployeeName() + " was transferred from " + tempEmp.getDepartment() +" to "+ newDepartment);
                }
                else {
                    System.out.println("Employee not found.");
                }
            }
            else if(command.equals("AV")){
                EmployeeVacation e = new EmployeeVacation();
                e.addVacation(id);
            }
            else if(command.equals("VV")){
                EmployeeVacation c = new EmployeeVacation();
                c.viewVacation(tempEmp);
            }
            else if(command.equals("AW")){
                WorkHours wh = new WorkHours();
                wh.addWorkHours(id);
            }
            else if(command.equals("SW")) {
                WorkHours a = new WorkHours();
                a.viewWorkHours(tempEmp);
            }
            // Job Post / Applicants Commands
            else if(command.equals("VJP"))
            {
                jpdb.viewJobPosts();
            }
            else if(command.equals("AJP"))
            {
                System.out.println("Enter Department ");
                String dept = reader.nextLine();
                System.out.println("Enter Description ");
                String desc = reader.nextLine();
                System.out.println("Enter Post Date ");
                String postDate = reader.nextLine();
                System.out.println("Enter Job Title ");
                String title = reader.nextLine();
                System.out.println("Enter Job ID # ");
                String id = reader.nextLine();
                System.out.println("Enter Salary ");
                String salary = reader.nextLine();

                JobPost jp = new JobPost(dept, desc, postDate, title, Integer.parseInt(id), Integer.parseInt(salary));

                jpdb.addJobPost(jp);
            }
            else if(command.equals("SJP"))
            {
                JobPost jp;
                int jpNum;
                System.out.println("Enter Job post number: ");
                jpNum = Integer.parseInt(reader.nextLine());

                jp = jpdb.getJobPost(jpNum);
                if(jp != null) {
                    jp.doAction(jp);
                }
                else {
                    System.out.println("Job post not found.");
                }
            }
            else if(command.equals("AJA"))
            {
                int jpNum;
                System.out.println("Enter Job post number: ");
                jpNum = Integer.parseInt(reader.nextLine());

                String appName;
                System.out.println("Enter applicants name: ");
                appName = reader.nextLine();

                jpdb.acceptApplicant(jpdb.getJobPost(jpNum), appName);
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
        String employeeName = tempEmp.getEmployeeName();
        System.out.println("Hello, "+ employeeName);
        System.out.println("Employee Functions:");
        System.out.println("View Retirement Report [VR], Add Employee Vacation[AV], View Employee Vacation [VV], Add Work Hours [AW], Show Work Hours [SW], Report Complaints [RC]");
        System.out.println("------");
        System.out.println("Employee Management:");
        System.out.println("Report Complaints [RC], Transfer Employee [TR]");
        System.out.println("------");
        System.out.println("Employee Recruitment:");
        System.out.println("Add Job Post [AJP], View Job Post [VJP], Accept Job Application [AJA], ???[SJP]");
    }
}
