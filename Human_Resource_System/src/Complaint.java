import java.util.Scanner;

public class Complaint {
    String givenComplaint;
    Scanner fileReader;

    public Complaint(String message){
        givenComplaint = message;
        fileReader = new Scanner("complaints.txt");
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
        while (command.equals("Y") && reader.hasNext()){
            System.out.println("Next complaint:");
            message = getNextComplaint();
            System.out.println(message);

            System.out.println("View next complaint? (Y/N)");
            command = reader.nextLine();
        }
        System.out.println("Leaving complaints..");
        reader.close();
        fileReader.close();

    }

    public String getNextComplaint(){
        boolean endOfComplaint = false;
        String complaint = "";
        String currentLine;
        while(!endOfComplaint){
            currentLine = fileReader.nextLine();
            if(currentLine.equals("BEGIN OF COMPLAINT")){
                complaint = "";
            }
            else if(currentLine.equals("END OF COMPLAINT")){
                endOfComplaint = true;
            }
            else{
                complaint += currentLine;
            }
        }
        return  complaint;
    }
}
