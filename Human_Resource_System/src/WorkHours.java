import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WorkHours {
    Scanner fileReader;
    File file;
    WorkHoursDatabase a;
    public WorkHours(){
        file = new File("Work_Hours.txt");
        a = new WorkHoursDatabase();
    }

    public void addWorkHours(int eid){
        double hours;
        Scanner input = new Scanner(System.in);

        System.out.println("How many hours did you work?");
        hours = input.nextDouble();
        a.addWorkHoursDB(eid, hours);
    }

    public void viewWorkHours(Employee emp){
        try {
            fileReader = new Scanner(file);
            boolean foundEmp = false;
            int entryNumber = 1;
            while (fileReader.hasNextLine()){
                String currentRecord = fileReader.nextLine();
                List<String> workHoursInfo = Arrays.asList(currentRecord.split("\\s*,\\s*"));
                if(workHoursInfo.contains(String.valueOf(emp.getID()))){
                    foundEmp = true;
                    System.out.println("Entry number: " + entryNumber);
                    System.out.println("Employee name: " + emp.getEmployeeName());
                    System.out.println("Date: "+ workHoursInfo.get(1));
                    System.out.println("Hours Worked: " + workHoursInfo.get(2));
                    System.out.println("----");
                    entryNumber++;
                }
            }
            if(!foundEmp) {
                System.out.println(emp.getEmployeeName() + " has not entered any hours worked.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No work hours entered on record.");
            ex.printStackTrace();
        }
        fileReader.close();
    }
}