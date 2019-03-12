import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Complaint {
    String givenComplaint;
    Scanner fileReader;
    File file;

    public Complaint(String message){
        givenComplaint = message;
        file = new File("complaints.txt");
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addComplaint(){
        complaintDatabase d = new complaintDatabase();
        d.addComplaint(givenComplaint);
    }

    public void viewComplaints(){
        String message = "";
        String command = "Y";
        //TO DO
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        while (command.equals("Y") && fileReader.hasNext()){
            System.out.println("Next complaint:");
            getNextComplaint();
            System.out.println(message);

            System.out.println("View next complaint? (Y/N)");
            command = reader.nextLine();

            if(!fileReader.hasNext()){
                command = "N";
            }
        }
        System.out.println("Leaving complaints..");
        fileReader.close();

    }

    public void getNextComplaint(){
        boolean endOfComplaint = false;
        String currentLine;
        while(!endOfComplaint){
            currentLine = fileReader.nextLine();
            if(currentLine.equals("BEGIN OF COMPLAINT")){
                System.out.println("Beggining of complaint:");
            }
            else if(currentLine.equals("END OF COMPLAINT")){
                endOfComplaint = true;
            }
            else{
                System.out.println(currentLine);
            }
        }
    }
}
