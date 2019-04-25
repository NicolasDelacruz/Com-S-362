import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * Employee is the 'Information Expert' as it knows about Employee, the functions available to be done by department, and the Databases data
 */
public class HRDepartment implements DepartmentInterface {
    JobPostDatabase jpdb;
    String department;
    Employee tempEmp;
    EventDatabase ed;
    prevEmployeeDatabase ped;
    employeeDatabase empDB;
    int id;

    public HRDepartment(Employee e) throws FileNotFoundException {
        department = "Human Resources";
        tempEmp = e;
        id = e.getID();
        jpdb = new JobPostDatabase();
        ed = new EventDatabase();
        ped = new prevEmployeeDatabase();
        empDB = new employeeDatabase();
    }

    @Override
    public void doFunctions() throws IOException {
        printAvailableOptions();
        String command = "";

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        while (!command.equals("Q")){
            System.out.println("What action would you like to do? ");
            command = reader.nextLine();

            if(command.equals("EM")){
                doEmplFunctions();
            }
            else if(command.equals("HR")){
                doHRFunctions();
            }
            else if(command.equals("VF")){
                printAvailableOptions();
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
        System.out.println("Employee Functions [EM]");
        System.out.println("HR Functions [HR]");
        System.out.println("View Functions [VF], Quit [Q]");
    }

    public void doEmplFunctions() throws IOException {
        System.out.println("In Employee Screen");
        System.out.println("--------------------");
        System.out.println("--------------------");


        printEmplFunctions();
        String command = "";

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        while (!command.equals("Q")){
            System.out.println("What action would you like to do? ");
            command = reader.nextLine();

            if(command.equals("RC")){
                System.out.println("Please type the complaint below. This is anonymous.");
                String comp = reader.nextLine();
                Complaint c = new Complaint(comp);
                c.addComplaint();
                System.out.println("The complaint has been filed. Our team will look into this issue.");
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
            else if(command.equals("VI")) {
                insuracePlan d = new insuracePlan();
                d.viewInsurancePlan(id);
            }
            else if(command.equals("EI")) {
                insuracePlan e = new insuracePlan();
                System.out.println("Please type the Insurance Plan you want.");
                System.out.println("Please type 0 for Basic plan.");
                System.out.println("Please type 1 for Intermediate plan.");
                System.out.println("Please type 2 for Full coverage plan.");
                command = reader.nextLine();
                int plan_type = Integer.valueOf(command);
                System.out.println("Do you want single plan or family plan.");
                System.out.println("Family plan include maximun 5 people(3 children)");
                System.out.println("Please type 0 for Single plan.");
                System.out.println("Please type 1 for Family plan.");
                command = reader.nextLine();
                int single_family = Integer.valueOf(command);
                int add_children = 0;
                if(single_family == 1) {
                    System.out.println("Please type the number of children you want to add.");
                    command = reader.nextLine();
                    add_children = Integer.valueOf(command);
                }
                e.editInsurancePlan(id, plan_type, single_family, add_children);
                System.out.println("Your insurance plan has been updated!");
            }
            else if(command.equals("VE")) {
                ed.viewEvents();
            }
            else if(command.equals("RS")) {
                System.out.println("Enter event ID: ");
                int eid = reader.nextInt();
                String a = reader.nextLine();
                ed.writeRSVP(tempEmp, a, eid);
            }
            else if(command.equals("VR"))
            {
                RetirementDatabase rd = new RetirementDatabase();
                rd.viewEmpRetirements(tempEmp.getID());
            }
            // change %check between 5 and 30%
            else if(command.equals("CR"))
            {
                RetirementDatabase rd = new RetirementDatabase();
                int id = tempEmp.getID();

                if(!rd.emplExist(id)) {
                    rd.addEmpRetirement(tempEmp);
                }

                System.out.println("What % of your check would you like to add to retirement?");
                int rr = reader.nextInt();

                rd.changeRetirementRate(id, rr);
                reader.nextLine();
            }
            // change %checkira between 0 and 5%
            else if(command.equals("IRA"))
            {
                RetirementDatabase rd = new RetirementDatabase();
                int id = tempEmp.getID();

                if(!rd.emplExist(id)) {
                    rd.addEmpRetirement(tempEmp);
                }

                System.out.println("What % of your check would you like to add to retirement?");
                int ira = reader.nextInt();

                rd.changeIRARate(id, ira);
                reader.nextLine();
            }
            else if(command.equals("FE")){
                employeeDatabase temp = new employeeDatabase();
                System.out.println("Enter Employee's ID: ");
                int id = reader.nextInt();
                temp.printEmployeeInfo(id);
                reader.nextLine();
            }
            else if(command.equals("EAC")){
                employeeClaimDatabase c = new employeeClaimDatabase();
                System.out.println("Please type you will claim.");
                System.out.println("Please type 1 for gas reimbursement, 2 for travel reimbursement.");
                System.out.println("Please type 3 for medical reimbursement, 4 for food reimbursement, 5 for others.");
                command = reader.nextLine();
                int type_i = Integer.valueOf(command);
                String type="";
                switch(type_i){
                    case 1:
                        type = "gas";
                        break;
                    case 2:
                        type = "travel";
                        break;
                    case 3:
                        type = "medical";
                        break;
                    case 4:
                        type = "food";
                        break;
                    case 5:
                        type = "others";
                        break;
                }
                System.out.println("Please type the year.");
                command = reader.nextLine();
                int year = Integer.valueOf(command);
                System.out.println("Please type the month.");
                System.out.println("Please type 1 if it is January.");
                command = reader.nextLine();
                int month = Integer.valueOf(command);
                System.out.println("Please type the day.");
                command = reader.nextLine();
                int day= Integer.valueOf(command);
                System.out.println("Please type how much you will claim.");
                command = reader.nextLine();
                double spend= Double.valueOf(command);
                c.addClaim(id, year, month, day, spend, type);
                System.out.println("Your input has been updated!");
            }
            else if(command.equals("ECP")) {
                calculatePaystubDatabase a = new calculatePaystubDatabase();
                System.out.println("Please type the Begin Date.");
                System.out.println("For example: 3/20/2019");
                command = reader.nextLine();
                String beginDate = command;
                System.out.println("Please type the End Date.");
                command = reader.nextLine();
                String endDate = command;
                System.out.println("Please type the wage rate.");
                command = reader.nextLine();
                double rate = Double.valueOf(command);
                System.out.println("Please type the hours you worked.");
                command = reader.nextLine();
                double hours = Double.valueOf(command);
                System.out.println("Please type the Vacation Hours you take.");
                command = reader.nextLine();
                double vacationUsedHours = Double.valueOf(command);
                System.out.println("Please type the Tax Rate.");
                System.out.println("Please type 7.5 if the tax rate is 7.5%");
                command = reader.nextLine();
                double taxRate = Double.valueOf(command);
                System.out.println("Please type amount of employee paid benefits (in dollar).");
                command = reader.nextLine();
                double employeePaidBenefits = Double.valueOf(command);
                a.addPaystub(id, beginDate, endDate, rate, hours, vacationUsedHours, taxRate, employeePaidBenefits);
                System.out.println("Your input has been updated!");
            }
            else if(command.equals("ED")) {
                DirectDepositDatabase tempdd = new DirectDepositDatabase();
                DepositInfo e = tempdd.getDirectDeposit(tempEmp.getID());
                if(e==null) {
                    System.out.println("Enter Address: ");
                    String address = reader.nextLine();
                    System.out.println("Enter Social Security Number: ");
                    int ssn = reader.nextInt();
                    System.out.println("Enter Account Number: ");
                    int account = reader.nextInt();
                    System.out.println("Enter Routing Number: ");
                    int routing = reader.nextInt();
                    reader.nextLine();
                    DepositInfo e2 = new DepositInfo(tempEmp.getID(), address, ssn, account, routing);
                    tempdd.addDirectDeposit(e2);
                }
                else {
                    tempdd.removeDirectDeposit(e);
                    System.out.println("Choose which one to edit: ");
                    System.out.println("Address [ADD], Account Number [ACC], Routing Number [ROU]");
                    String b = reader.nextLine();
                    if (b.equals("ADD")) {
                        System.out.println("Enter Address: ");
                        String address = reader.nextLine();
                        DepositInfo e2 = new DepositInfo(tempEmp.getID(), address, e.getEmpID(), e.getAccount(), e.getRouting());
                    }
                    else if (b.equals("ACC")) {
                        System.out.println("Enter Account Number: ");
                        int account = reader.nextInt();
                        reader.nextLine();
                        DepositInfo e2 = new DepositInfo(tempEmp.getID(), e.getAddress(), e.getEmpID(), account, e.getRouting());
                    }
                    else if (b.equals("ROU")) {
                        System.out.println("Enter Routing Number: ");
                        int routing = reader.nextInt();
                        reader.nextLine();
                        DepositInfo e2 = new DepositInfo(tempEmp.getID(), e.getAddress(), e.getEmpID(), e.getAccount(), routing);
                    }
                }
            }
            else if(command.equals("VT")){
                TrainingDatabase td = new TrainingDatabase();
                System.out.println("new employee [NEW], general employee [GEN], HR Department [HR], Event Department [EV], All [ALL]");
                String t = reader.nextLine();
                if(!t.equals("ALL"))
                    td.printTrainingType(t);
                else
                    td.viewTrainings();
            }
            else if(command.equals("VF")){
                printEmplFunctions();
            }
            else if(command.equals("R")){
                System.out.println("Leaving Employee Screen..");
                return;
            }
        }
        System.out.println("Leaving Employee Screen..");
    }

    public void printEmplFunctions(){
        System.out.println("Employee Functions:");
        System.out.println("Find Employee [FE],  Report Complaints [RC], Edit Direct Deposit [ED]");
        System.out.println("------");
        System.out.println("Retirement and Insurance:");
        System.out.println("View Retirement Report [VR], Change Employee's Contribution to Retirement [CR]");
        System.out.println("Add / Change Employee's IRA Contribution [IRA]");
        System.out.println("View Company Insurance Plan [VI], Edit Company Insurance Plan [EI]");
        System.out.println("------");
        System.out.println("Vacation and Work Hours:");
        System.out.println("View Employee Vacation [VV]");
        System.out.println("Add Work Hours [AW], Show Work Hours [SW]");
        System.out.println("------");
        System.out.println("Employee Events:");
        System.out.println("View Events [VE], RSVP to an Event [RS]");
        System.out.println("Employee Benefits:");
        System.out.println("Employee Add Claim [EAC], Employee Calculate Paystub [ECP]");
        System.out.println("------");
        System.out.println("Employee Training:");
        System.out.println("View Training [VT]");
        System.out.println("------");
        System.out.println("------");
        System.out.println("View Functions [VF], Return to Main Menu [R] or [Q]");
    }

    public void doHRFunctions() throws IOException {
        System.out.println("In Human Resource Screen");
        System.out.println("--------------------");
        System.out.println("--------------------");


        printHRFunctions();
        String command = "";

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        while (!command.equals("Q")){
            System.out.println("What action would you like to do? ");
            command = reader.nextLine();

            if(command.equals("VC")){
                Complaint c = new Complaint("");
                c.viewComplaints();
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
            // Job Post / Applicants Commands
            // View Job Post
            else if(command.equals("VJP"))
            {
                jpdb.viewJobPosts();
            }
            // Add Job Post
            else if(command.equals("AJP"))
            {
                System.out.println("Enter Department ");
                String dept = reader.nextLine();
                System.out.println("Enter Post Date ");
                String postDate = reader.nextLine();
                System.out.println("Enter Job Title ");
                String title = reader.nextLine();
                System.out.println("Enter Job ID # ");
                String id = reader.nextLine();
                System.out.println("Enter Salary ");
                String salary = reader.nextLine();

                jpdb.addJobPost(new JobPost(dept, postDate, title, Integer.parseInt(id), Integer.parseInt(salary)));
            }
            // Select Job Post
            else if(command.equals("SJP"))
            {
                JobPost jp;
                int jpNum;
                System.out.println("Enter Job post number: ");
                jpNum = Integer.parseInt(reader.nextLine());
                jp = jpdb.getJobPost(jpNum);

                // Check to see if it's a real job post number
                if(jp != null)
                {
                    while(!command.equals("R"))
                    {
                        System.out.println("------");
                        System.out.println("Selected Job Post: " + jpNum);
                        System.out.println("Add Job Post Applicants [APA], View Job Post Applicants [VPA], Accept Job Applicant [AJA], Return [R]");
                        System.out.println("What action would you like to do? ");
                        command = reader.nextLine();

                        // Add
                        if(command.equals("APA")){
                            String newApplicant;
                            // Get Name and DOB of Applicant
                            System.out.println("Enter Applicants Name");
                            String appName = reader.nextLine();

                            System.out.println("Enter Applicants Date of Birth (DOB)");
                            String appDOB = reader.nextLine();

                            newApplicant = appName + ", " + appDOB + ", " + jp.getJobTitle() + ", " + jp.getID() + ", " + jp.getDepartment() + ", " + jp.getPostDate() + ", " + jp.getSalary();

                            jpdb.writeJobApplicant(newApplicant);
                        }
                        // View
                        else if(command.equals("VPA")){
                            jpdb.viewApplicants(jp);
                        }
                        // Accept Job Applicant
                        else if(command.equals("AJA"))
                        {
                            String appName;
                            System.out.println("Enter applicants name: ");
                            appName = reader.nextLine();

                            Employee emp = jpdb.acceptApplicant(jpdb.getJobPost(jpNum), appName);

                            employeeDatabase ed = new employeeDatabase();
                            ed.addEmployee(emp);
                        }
                    }
                }
                else
                {
                    // not real, print error
                    System.out.println("Failed to find Job Post ID: " + jpNum);
                }

            }
            // Accept Job Applicant
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
            else if(command.equals("CER")){
                Report r = new Report();
                if(r.createExpenseReport()){
                    System.out.println("Report was created and is in your downloads folder.");
                }
                else {
                    System.out.println("Report has previously been created and is in your downloads folder.");
                }
            }
            else if(command.equals("DSR")){
                Report r = new Report();
                if(r.createDepartmentReport()){
                    System.out.println("Report was created and is in your downloads folder.");
                }
                else {
                    System.out.println("Report has previously been created and is in your downloads folder.");
                }
            }
            else if(command.equals("EPR")){
                PerformanceReview pr = new PerformanceReview();
                pr.employeePerformanceReview();
            }
            // Fire Employee
            else if(command.equals("FE"))
            {
                System.out.println("What employee ID is being fired?");
                int empID = Integer.parseInt(reader.nextLine());

                System.out.println("Why is the employee being fired?");
                String reas = reader.nextLine();

                String date = new Date().toString();

                employeeDatabase ed = new employeeDatabase();
                Employee emp = ed.getEmployee(empID);
                ed.removeEmployee(emp);

                ped.addEmployee(emp, "Fired", reas, date);
            }
            // View Previous Employees
            else if(command.equals("VPE"))
            {
                ped.viewPrevEmployees();
            }
            // Review Employee
            else if(command.equals("RE"))
            {
                System.out.println("What employee ID is being reviewed?");
                int empID = Integer.parseInt(reader.nextLine());

                employeeDatabase ed = new employeeDatabase();
                ed.printEmployeeInfo(empID);

                System.out.println("Would you like to give this employee a raise or fire them?");
                String answer = reader.nextLine();

                if(answer.equals("raise") || answer.equals("Raise"))
                {
                    System.out.println("What percentage of a raise should this employee recieve?");
                    int raise = Integer.parseInt(reader.nextLine());

                    Employee emp = ed.getEmployee(empID);
                    ed.removeEmployee(emp);

                    emp.giveRaise(raise);

                    ed.addEmployee(emp);
                }
                else if(answer.equals("fire") || answer.equals("Fire"))
                {
                    System.out.println("Why is the employee being fired?");
                    String reas = reader.nextLine();

                    String date = new Date().toString();

                    Employee emp = ed.getEmployee(empID);
                    ed.removeEmployee(emp);

                    ped.addEmployee(emp, "Fired", reas, date);
                }
                else {
                    System.out.println("Incorrect input, please try again.");
                }

            }
            else if(command.equals("VEC")){
                employeeClaimDatabase c = new employeeClaimDatabase();
                c.viewClaim(id);
            }
            else if(command.equals("VEP")) {
                calculatePaystubDatabase a = new calculatePaystubDatabase();
                a.viewPlaystub(id);
            }
            else if(command.equals("CT")) {
                ArrayList<String> s = new ArrayList<String>();
                TrainingDatabase td = new TrainingDatabase();
                String t = "";
                System.out.println("Enter Event Title : ");
                String title = reader.nextLine();
                System.out.println("Enter new training ID : "); // This is just identification number for given strings
                int id = reader.nextInt();
                reader.nextLine();
                while(td.checkID(id)) {
                    System.out.println("Corresponding ID number already taken. Please enter new series of number");
                    id = reader.nextInt();
                    reader.nextLine();
                }
                // This can be used to tell who needs to complete which training
                System.out.println("Enter what type of Training it is : ");
                System.out.println("new employee [NEW], general employee [GEN], HR Department [HR], Event Department [EV]");
                String type = reader.nextLine();
                while(!type.equals("NEW")&&!type.equals("GEN")&&!type.equals("HR")&&!type.equals("EV")) {
                    type = reader.nextLine();
                }
                System.out.println("Enter Event Detail : (enter 'endoftraining' to finish) ");
                while(!t.contains("endoftraining")) {
                    s.add(t);
                    t = reader.nextLine();
                }
                td.addTraining(s, title, type, id);
            }
            else if(command.equals("VF")){
                printHRFunctions();
            }
            else if(command.equals("AEM")){
                System.out.println("Enter employee name:");
                String name = reader.nextLine();

                System.out.println("Enter employee department:");
                String department = reader.nextLine();

                System.out.println("Enter employee DOB (mm/dd/yyyy):");
                String dob = reader.nextLine();

                System.out.println("Enter employee hire date (mm/dd/yyyy):");
                String hiredate = reader.nextLine();

                System.out.println("Enter employee ID:");
                int id = reader.nextInt();

                System.out.println("Enter employee salary:");
                int salary = reader.nextInt();

                System.out.println("Enter employee retirement:");
                double retirement = reader.nextInt();

                Employee temp = new Employee(department, dob, hiredate, name, id, salary, retirement);
                empDB.addEmployee(temp);
                reader.nextLine();

            }
            else if(command.equals("R")){
                System.out.println("Leaving HR Screen.." );
                return;
            }
        }

        System.out.println("Leaving HR Screen.." );
    }

    public void printHRFunctions(){
        System.out.println("Employee Management:");
        System.out.println("View Complaints [VC], Transfer Employee [TR], Employee Performance Review [EPR]");
        System.out.println("View Previous Employees [VPE], Review Employee [RE], Fire Employee [FE]");
        System.out.println("Create Employee Training [CT], Add New User [AEM]");
        System.out.println("------");
        System.out.println("Employee Recruitment:");
        System.out.println("Add Job Post [AJP], View Job Post [VJP], Select Job Post[SJP]");
        System.out.println("------");
        System.out.println("View Employee Benefits:");
        System.out.println("View Employee Claims [VEC], View Employee Paystubs [VEP]");
        System.out.println("Report Generation:");
        System.out.println("Company Expense Report [CER], Department Statistics Report [DSR]");
        System.out.println("View Functions [VF], Return to Main Menu [R] or [Q]");
    }
}