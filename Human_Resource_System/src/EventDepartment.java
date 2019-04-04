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
            
            if(command.equals("RC")){
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
            else if(command.equals("VE")) {
            	ed.viewEvents();
            }
            else if(command.equals("RS")) {
            	System.out.println("Enter event ID: ");
            	int eid = reader.nextInt();
            	String a = reader.nextLine();
            	ed.writeRSVP(tempEmp, a, eid);
            }
            else if(command.equals("CE")) {
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
            	String s = reader.nextLine();
            	Event ev = new Event(dtf.format(localDate), title, start, end, cost, id);
            	ed.addEvent(s, ev);
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
        System.out.println("Employee Functions:");
        System.out.println("View Retirement Report [VR], View Employee Vacation [VV]");
        System.out.println("Add Work Hours [AW], Show Work Hours [SW], Report Complaints [RC]");
        System.out.println("View Company Insurance Plan [VI], Edit Company Insurance Plan [EI]");
        System.out.println("View Events [VE], RSVP to an Event [RS]");
        System.out.println("View Functions [VF]");
        System.out.println("------");
        System.out.println("Event Management:");
        System.out.println("Create Event [CE]");
	}

}
