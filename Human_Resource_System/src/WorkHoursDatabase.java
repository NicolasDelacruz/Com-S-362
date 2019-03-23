import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WorkHoursDatabase {
    File file;

    public WorkHoursDatabase() {
        file = new File("Work_Hours.txt");
        generateDataFile();
    }

    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("EMPLOYEE ID, DATE, TIME WORKED");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String s){
        if(file.exists()){
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

    public void addWorkHoursDB(int eid, int timeWorked){
        LocalDate date = LocalDate.now();
        String stringDate = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(date);
        String workHoursEntry = eid + ", " + stringDate + ", " + timeWorked;

        write(workHoursEntry);
    }
}