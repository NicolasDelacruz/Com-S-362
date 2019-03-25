import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeVacationDatabase {
    File file;

    public EmployeeVacationDatabase() {
        file = new File("EmployeeVacation.txt");
        generateDataFile();
    }

    public void generateDataFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("EMPLOYEE ID, START VACATION, END VACATION, VACATION REMAINING");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String s) {
        if (file.exists()) {
            try {
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(s);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addVacation(int eid) {
        int vacationMax = vacationLeft(eid);

        if(vacationMax <= 0) {
            System.out.println("You do not have any vacation left.");
        }
        else {
            Date currentDate = new Date();

            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("What day is your vacation starting?");
            int dayStart = reader.nextInt();

            System.out.println("What month is your vacation starting?");
            int monthStart = reader.nextInt();
            LocalDate startDate = LocalDate.of(2019, monthStart, dayStart);

            System.out.println("What day is your vacation ending?");
            int dayEnd = reader.nextInt();

            System.out.println("What month is your vacation ending?");
            int monthEnd = reader.nextInt();
            LocalDate endDate = LocalDate.of(2019, monthEnd, dayEnd);

            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

            if(daysBetween <= vacationMax){
                vacationMax -= (int) daysBetween;
                String startStr = dayStart + "/" +monthStart + "/2019";
                String endStr = dayEnd + "/" +monthEnd + "/2019";
                String vacationRec = eid + ", " + startStr + ", " + endStr + ", " + vacationMax;
                write(vacationRec);
            }
            else {
                System.out.println("You are attempting to take too much vacation.");
            }
        }
    }

    public int vacationLeft(int empId) {
        int vacationLeft = 15;
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String currentRecord = fileReader.nextLine();
                List<String> vacationInfo = Arrays.asList(currentRecord.split("\\s*,\\s*"));
                if (vacationInfo.contains(String.valueOf(empId))) {
                    vacationLeft = Integer.valueOf(vacationInfo.get(3));
                }
            }
            fileReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No work hours entered on record.");
            ex.printStackTrace();
        }
        return vacationLeft;
    }
}