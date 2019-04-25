import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EventDepartment implements DepartmentInterface {
	String department;
	Employee tempEmp;
    int id;
    EventDatabase ed;
	
	public EventDepartment(Employee e) throws FileNotFoundException {
		department = "Event Management";
		tempEmp = e;
        id = e.getID();
        ed = new EventDatabase();
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
            else if(command.equals("EV")){
                doEventFunctions();
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
        System.out.println("Event Management Functions [EV]");
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
                System.out.println("Please type the Employee Paid Benefit (dollar).");
                command = reader.nextLine();
                double employeePaidBenefits = Double.valueOf(command);
                a.addPaystub(id, beginDate, endDate, rate, hours, vacationUsedHours, taxRate, employeePaidBenefits);
                System.out.println("Your input has been updated!");
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
        System.out.println("Find Employee [FE],  Report Complaints [RC]");
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
        System.out.println("------");
        System.out.println("View Functions [VF], Return to Main Menu [R] or [Q]");
    }

    public void  printEventFunctions(){
        System.out.println("Event Management Functions:");
        System.out.println("Create Event[CE]");
        System.out.println("------");
        System.out.println("------");
        System.out.println("View Functions [VF], Return to Main Menu [R] or [Q]");
    }

    public void doEventFunctions(){
        System.out.println("In Employee Screen");
        System.out.println("--------------------");
        System.out.println("--------------------");


        printEventFunctions();
        String command = "";

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        while (!command.equals("Q")){
            System.out.println("What action would you like to do? ");
            command = reader.nextLine();

            if(command.equals("CE")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate localDate = LocalDate.now();
                System.out.println("Enter Event Title : ");
                String title = reader.nextLine();
                System.out.println("Type Start Date in MM/dd/yyyy format: ");
                String start = reader.nextLine();
                System.out.println("Type End Date in MM/dd/yyyy format: ");
                String end = reader.nextLine();
                System.out.println("Enter Cost of Event : ");
                int cost = reader.nextInt();
                System.out.println("Give Event ID : ");
                int id = reader.nextInt();
                System.out.println("Enter Event Detail : ");
                reader.nextLine();
                String s = reader.nextLine();
                Event ev = new Event(dtf.format(localDate), title, start, end, cost, id);
                ed.addEvent(s, ev);
            }
            else if(command.equals("VF")){
                printEventFunctions();
            }
            else if(command.equals("R")){
                System.out.println("Leaving Employee Screen..");
                return;
            }
        }
        System.out.println("Leaving Employee Screen..");
    }
}
