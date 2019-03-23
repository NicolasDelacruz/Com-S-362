import java.io.File;
import java.io.FileNotFoundException;
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
        int hours;
        Scanner input = new Scanner(System.in);

        System.out.println("How many hours did you work?");
        hours = input.nextInt();
        a.addWorkHoursDB(eid, hours);
    }

    public void viewWorkHours(int eid){
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        boolean find = false;
        int required_id = eid;
        while (fileReader.hasNextLine()){
            String data = fileReader.nextLine();
            if (data.startsWith("id:")) {
                int cur_id = Integer.valueOf(data.substring(3));
                if (cur_id == required_id) {
                    find = true;
                    data = fileReader.nextLine();
                    int year = Integer.valueOf(data.substring(5));
                    data = fileReader.nextLine();
                    int month = Integer.valueOf(data.substring(6));
                    data = fileReader.nextLine();
                    double hours = Double.valueOf(data.substring(5));
                    System.out.println("Emplyee ID: "+ cur_id + " works "
                            + hours +" hours in "+year+"/"+month);
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
            System.out.println("Error: The employee is not found!");
        }
        System.out.println("-----------------------------");
    }
}