import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EmployeeVacation {

    Scanner fileReader;
    File file;
    EmployeeVacationDatabase a;
    int totaldays = 0;
    public EmployeeVacation(){
        file = new File("EmployeeVacation.txt");
        a = new EmployeeVacationDatabase();
    }

    public void addVacation(int eid, int start_year, int start_month
            , int start_day, int days, int limit_days){
        a.addVacation(eid, start_year, start_month, start_day,days,limit_days);
    }

    public void viewVacation(int eid){
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        boolean find = false;
        int required_id = eid;
        int limit_days = 0;
        while (fileReader.hasNextLine()){
            String data = fileReader.nextLine();
            if (data.startsWith("id:")) {
                int cur_id = Integer.valueOf(data.substring(3));
                if (cur_id == required_id) {
                    find = true;
                    data = fileReader.nextLine();
                    int start_year = Integer.valueOf(data.substring(4));
                    data = fileReader.nextLine();
                    int start_month = Integer.valueOf(data.substring(4));
                    data = fileReader.nextLine();
                    int start_day = Integer.valueOf(data.substring(4));
                    data = fileReader.nextLine();
                    int days = Integer.valueOf(data.substring(5));
                    data = fileReader.nextLine();
                    limit_days = Integer.valueOf(data.substring(6));
                    System.out.println("Emplyee ID: "+ cur_id + " have a vaction from "
                            + start_year +"/"+start_month+"/"+start_day + ", lasts "+
                            days +" days");
                    totaldays += days;
                }
                else {
                    data = fileReader.nextLine();
                }
            }
            else {
                data = fileReader.nextLine();
            }
        }
        fileReader.close();
        if ( find == false) {
            System.out.println("The vacation schedule is not found!");
        }
        else {
            System.out.println("The total days are "+ totaldays +", and the limitation of vacation is " + limit_days
                    + " days");
            if (totaldays > limit_days) {
                System.out.println("Warning, it exceeds the limited days!");
            }
        }
        System.out.println("-----------------------------");
    }

}
