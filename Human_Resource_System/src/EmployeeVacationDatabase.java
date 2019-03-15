import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeVacationDatabase {
    File file;

    public EmployeeVacationDatabase() {
        file = new File("EmployeeVacation.txt");
        generateDataFile();
    }

    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
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

    public void addVacation(int eid, int start_year, int start_month
            , int start_day, int days, int limit_days){
        int ID = eid;
        String input1 ="id:"+ID;
        String input2 ="s_y:" + start_year;
        String input3 ="s_m:" + start_month;
        String input4 ="s_d:" + start_day;
        String input5 ="days:" + days;
        String input6 ="limit:" + limit_days;

        write(input1);
        write(input2);
        write(input3);
        write(input4);
        write(input5);
        write(input6);
    }
}