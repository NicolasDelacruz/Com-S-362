import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TrainingDatabase {
    File file;
    Scanner fileReader;

    public TrainingDatabase() throws FileNotFoundException {
        file = new File("Training.txt");
        generateDataFile();
        fileReader = new Scanner(file);
    }

    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("Training Database");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * write s in the file
     * @param s - training being added to the file generated by generateDataFile()
     */
    public void write(ArrayList<String> s, String title, String type, int id){
        if(file.exists()){
            try {
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("BEGIN OF TRAINING");
                pw.println(id+"\t"+title + "\t" + type);
                for(int i = 1; i < s.size(); i++) {
                    pw.println(s.get(i));
                }
                pw.println("END OF TRAINING");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * write training in the file using write(String s)
     * @param e - training being added to the list of employee data
     */
    public void addTraining(ArrayList<String> s, String title, String type, int id){
        write(s, title, type, id);
    }    
    
    public void viewTrainings() throws FileNotFoundException{
        String message = "";
        String command = "Y";
        String currentLine;

        Scanner reader = new Scanner(System.in);
        while (command.equals("Y") && fileReader.hasNext()){
            System.out.println("Next Training:");
            boolean endOfTraining = false;
            while(!endOfTraining){
                currentLine = fileReader.nextLine();
                if(currentLine.equals("BEGIN OF TRAINING")){
                    System.out.println("Beggining of training:");
                }
                else if(currentLine.equals("END OF TRAINING")){
                	endOfTraining = true;
                }
                else{
                    System.out.println(currentLine);
                }
            }
            System.out.println(message);

            System.out.println("View next Eployee Training? (Y/N)");
            command = reader.nextLine();

            if(!fileReader.hasNext()){
                command = "N";
            }
        }
        System.out.println("Leaving Trainings..");
        //fileReader.close();
    }
    
    public void printTrainingID(int id) throws IOException {
    	File inputFile = new File("Training.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine;
        String strID = Integer.toString(id);

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(strID)){
            	while(!currentLine.equals("END OF TRAINING")) {
            		System.out.println(currentLine);
            		currentLine = reader.readLine();
            	}
            }
        }
        reader.close();
    }
    
    public void printTrainingType(String type) throws IOException {
    	File inputFile = new File("Training.txt");
    	File tempFile = new File("tempTraining.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        Scanner in = new Scanner(System.in);
        
        tempFile.createNewFile();
        FileWriter fw = new FileWriter(tempFile, true);
        PrintWriter pw = new PrintWriter(fw);

        String currentLine;
        String message = "";
        String command = "Y";
        String strType = type;
	    while((currentLine = reader.readLine()) != null) {
	        if(currentLine.contains(strType)){
	        	pw.println("BEGIN OF TRAINING");
	        	while(!currentLine.equals("END OF TRAINING")) {
	          		pw.println(currentLine);
	           		currentLine = reader.readLine();
	           	}
	        	pw.println("END OF TRAINING");
	        }
	    }
	    pw.close();
	    
	    Scanner tReader = new Scanner(tempFile);
	    while (command.equals("Y") && tReader.hasNext()){
            System.out.println("Next Training:");
            boolean endOfTraining = false;
            while(!endOfTraining){
                currentLine = tReader.nextLine();
                if(currentLine.equals("BEGIN OF TRAINING")){
                    System.out.println("Beggining of training:");
                }
                else if(currentLine.equals("END OF TRAINING")){
                	endOfTraining = true;
                }
                else{
                    System.out.println(currentLine);
                }
            }
            System.out.println(message);

            System.out.println("View next Eployee Training? (Y/N)");
            command = in.nextLine();

            if(!tReader.hasNext()){
                command = "N";
            }
	    }
	    
        reader.close();
        tReader.close();
        tempFile.delete();
    }
    
    public boolean checkID(int id) throws IOException {
    	File inputFile = new File("Training.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine;
        String strID = Integer.toString(id);

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(strID)){
            	System.out.println("taken:");
            	return true;
            }
        }
        reader.close();
        
        return false;
    }
}