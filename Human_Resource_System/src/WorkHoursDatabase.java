import java.io.*;

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
                //pw.println("ID, YEAR, MONTH, WORK_HOURS");
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

    public void addWorkHoursDB(int eid, int year, int month, double hours){
        int ID = eid;
        String input1 ="id:"+ID;
        String input2 ="year:" + year;
        String input3 ="month:" + month;
        String input4 ="time:" + hours;
        write(input1);
        write(input2);
        write(input3);
        write(input4);
    }
}