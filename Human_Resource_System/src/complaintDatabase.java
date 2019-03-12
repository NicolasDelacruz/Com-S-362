import java.io.*;

public class complaintDatabase {
    File file;


    public complaintDatabase() {
        file = new File("complaints.txt");
        generateDataFile();
    }

    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("Complaint Database");
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
                pw.println("BEGIN OF COMPLAINT");
                pw.println(s);
                pw.println("END OF COMPLAINT");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addComplaint(String c){
        String complaint = c;

        write(complaint);

    }

    public void removeEmployee(Employee e) throws IOException {
        File inputFile = new File("Employee_Info.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String employeeRecord;

        String name = e.getEmployeeName();
        String department = e.getDepartment();
        String DOB = e.getDOB();
        String hireDate = e.getHireDate();
        int ID = e.getID();
        int salary = e.getSalary();
        employeeRecord = name + ", " + ID + ", " + department +", " + salary + ", " + DOB + ", " + hireDate;

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(employeeRecord)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }
}
