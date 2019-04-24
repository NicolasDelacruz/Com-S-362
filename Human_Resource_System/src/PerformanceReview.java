import java.io.IOException;
import java.util.Scanner;

public class PerformanceReview {
    int performance;
    int attendance;
    int complaints;

    employeeDatabase empDB;

    public PerformanceReview(){
        performance = 0;
        attendance = 0;
        complaints = 0;

        empDB = new employeeDatabase();
    }

    public void employeePerformanceReview() throws IOException {
        Scanner input = new Scanner(System.in);

        int employeeID;

        System.out.println("Enter employee ID regarding performance: ");
        String strEmployeeID = input.nextLine();
        employeeID = Integer.valueOf(strEmployeeID);

        Employee e = empDB.getEmployee(employeeID);
        if(e != null) {
            updatePerformances();
            double per = e.getSalary()*(.01*performance);
            double att = e.getSalary()*(.005*attendance);
            double com = e.getSalary()*(.005*complaints);
            double temp = per + att - com;
            int newSalary = Math.max((int) temp + e.getSalary(), e.getSalary());

            empDB.removeEmployee(e);
            System.out.println("Previous salary: " + e.getSalary());
            e.setSalary(newSalary);
            System.out.println("New Salary: " + e.getSalary());

            empDB.addEmployeeNP(e);
        }else{
            System.out.println("Employee is not found in the database.");
        }
    }

    public void updatePerformances(){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter employee performance rating [0-10]: ");
        String strPerformance = input.nextLine();
        performance = Integer.valueOf(strPerformance);

        System.out.println("Enter employee attendance [0-10]:");
        String strAttendance = input.nextLine();
        attendance = Integer.valueOf(strAttendance);

        System.out.println("Enter number of complaints:");
        String strComplaints = input.nextLine();
        complaints = Integer.valueOf(strComplaints);
    }


}
