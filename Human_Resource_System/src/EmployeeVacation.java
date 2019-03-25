import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeVacation {

    Scanner fileReader;
    File file;
    EmployeeVacationDatabase a;
    int totaldays = 0;

    public EmployeeVacation() {
        file = new File("EmployeeVacation.txt");
        a = new EmployeeVacationDatabase();
    }

    public void addVacation(int eid) {
        a.addVacation(eid);
    }

    public void viewVacation(Employee emp) {
        try {
            fileReader = new Scanner(file);
            boolean foundEmp = false;
            int entryNumber = 1;
            while (fileReader.hasNextLine()) {
                String currentRecord = fileReader.nextLine();
                List<String> vacationInfo = Arrays.asList(currentRecord.split("\\s*,\\s*"));
                if (vacationInfo.contains(String.valueOf(emp.getID()))) {
                    foundEmp = true;
                    System.out.println("Entry number: " + entryNumber);
                    System.out.println("Employee name: " + emp.getEmployeeName());
                    System.out.println("Start date: " + vacationInfo.get(1));
                    System.out.println("End date: " + vacationInfo.get(2));
                    System.out.println("Amount of vacation days left: " + vacationInfo.get(3));
                    System.out.println("----");
                    entryNumber++;
                }
            }
            if (!foundEmp) {
                System.out.println("No vacation requested so far.");
                System.out.println("Amount of vacation days left: " + 15);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No work hours entered on record.");
            ex.printStackTrace();
        }
        fileReader.close();
    }

}
