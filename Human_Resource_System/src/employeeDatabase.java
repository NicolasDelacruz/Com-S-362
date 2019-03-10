import java.io.*;

public class employeeDatabase {
    File file;


    public employeeDatabase() {
        file = new File("Employee_Info.txt");
        generateDataFile();
    }

    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("NAME, ID, DEPARTMENT, SALARY, DOB, HIRE_DATE");
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

    public void addEmployee(Employee e){
        String employeeRecord;

        String name = e.getEmplyeeName();
        String department = e.getDepartment();
        String DOB = e.getDOB();
        String hireDate = e.getHireDate();
        int ID = e.getID();
        int salary = e.getSalary();

        employeeRecord = name + ", " + ID + ", " + department +", " + salary + ", " + DOB + ", " + hireDate;

        write(employeeRecord);

    }

    public void removeEmployee(Employee e) throws IOException {
        File inputFile = new File("Employee_Info.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String employeeRecord;

        String name = e.getEmplyeeName();
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
