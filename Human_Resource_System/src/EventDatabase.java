import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EventDatabase {
    File file;
    Scanner fileReader;


    public EventDatabase() throws FileNotFoundException {
        file = new File("Events.txt");
        fileReader = new Scanner(file);
        generateDataFile();
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("No events!");
            e.printStackTrace();
        }
    }
    
    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("Event Database");
                pw.println("ID, TITLE, DATE, STARTDATE, ENDDATE, COST");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void write(String s, String ev){
        if(file.exists()){
            try {
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("BEGIN OF EVENT");
                pw.println(ev);
                pw.println(s);
                pw.println("END OF EVENT");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void addEvent(String s, Event ev){
        String eventDetail = s;
//      "ID, TITLE, DATE, STARTDATE, ENDDATE, cost"
        String event = ev.getID() + ", " + ev.getTitle() + ", " + ev.getPostDate() + ", " + ev.getStartDate() + ", " + ev.getEndDate() + ", " + ev.getCost();
        write(eventDetail, event);
    }
    
    public void viewEvents() throws FileNotFoundException{
        String message = "";
        String command = "Y";
        String currentLine;

        Scanner reader = new Scanner(System.in);
        while (command.equals("Y") && fileReader.hasNext()){
            System.out.println("Next Event:");
            boolean endOfComplaint = false;
            while(!endOfComplaint){
                currentLine = fileReader.nextLine();
                if(currentLine.equals("BEGIN OF EVENT")){
                    System.out.println("Beggining of event:");
                }
                else if(currentLine.equals("END OF EVENT")){
                    endOfComplaint = true;
                }
                else{
                    System.out.println(currentLine);
                }
            }
            System.out.println(message);

            System.out.println("View next Event? (Y/N)");
            command = reader.nextLine();

            if(!fileReader.hasNext()){
                command = "N";
            }
        }
        System.out.println("Leaving Events..");
        //fileReader.close();
    }
    
    public Event getEvent(int id) throws IOException {
    	File inputFile = new File("Events.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        Event ev;

        String postDate, title, start, end;
        int cost, ID;

        String currentLine;
        String strID = Integer.toString(id);

        while((currentLine = reader.readLine()) != null) {
            List<String> eventInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
            if(eventInfo.contains(strID)){
//            	"ID, TITLE, DATE, STARTDATE, ENDDATE, cost"
            	ID = Integer.parseInt(eventInfo.get(0));
                title = eventInfo.get(1);
                postDate = eventInfo.get(2);
                start = eventInfo.get(3);
                end = eventInfo.get(4);
                cost = Integer.parseInt(eventInfo.get(5));
                ev = new Event(postDate, title, start, end, cost, ID);

                return ev;
            }
        }
        reader.close();

        return null;
    }
    
    public void printEventID(int id) throws IOException {
    	File inputFile = new File("Events.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine;
        String strID = Integer.toString(id);

        while((currentLine = reader.readLine()) != null) {
            List<String> eventInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
            if(eventInfo.contains(strID)){
            	System.out.println("Beggining of event:");
            	while(!currentLine.equals("END OF EVENT")) {
            		System.out.println(currentLine);
            		currentLine = reader.readLine();
            	}
            }
        }
        reader.close();
    }
    
    public void writeRSVP(Employee e, String a, int id) throws IOException {
    	File inputFile = new File("Events.txt");
        File tempFile = new File("Event_temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        
        String currentLine;
        String strID = Integer.toString(id);

        while((currentLine = reader.readLine()) != null) {
            List<String> eventInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
            if(eventInfo.contains(strID)){
            	while(!currentLine.equals("END OF EVENT")) {
            		writer.write(currentLine + System.getProperty("line.separator"));
            		currentLine = reader.readLine();
            	}
            	writer.write(Integer.toString(e.getID()) + "  " + e.getEmployeeName() + "  " + a + System.getProperty("line.separator"));
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.flush();
        writer.close();
        reader.close();
        System.gc();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }
}
