import java.io.*;
import java.util.Arrays;
import java.util.List;

public class employeeDatabase {
    File file;


    public employeeDatabase() {
        file = new File("Employee_Info.txt");
        generateDataFile();
    }

    /**
     * This method generates a file to store employees and their attributes.
     * starts first line of the file with names of each categories.
     */
    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("NAME, ID, DEPARTMENT, SALARY, DOB, HIRE_DATE, RETIREMENT");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * write s in the file
     * @param s - a employee's info being added to the file generated by generateDataFile()
     */
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

    /**
     * write employee e's data in the file using write(String s)
     * @param e - employee being added to the list of employee data
     */
    public void addEmployee(Employee e) throws IOException {

        if(getEmployee(e.getID()) == null) {
            String employeeRecord;

            String name = e.getEmployeeName();
            String department = e.getDepartment();
            String DOB = e.getDOB();
            String hireDate = e.getHireDate();
            int ID = e.getID();
            int salary = e.getSalary();
            double retirement = e.getRetirement();

            employeeRecord = name + ", " + ID + ", " + department + ", " + salary + ", " + DOB + ", " + hireDate + ", " + retirement;

            write(employeeRecord);
        }

    }

    /**
     * remove employee e's data from the file
     * @param e - employee being deleted from the list of employee data
     * @throws IOException
     */
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
        double retirement = e.getRetirement();
        employeeRecord = name + ", " + ID + ", " + department +", " + salary + ", " + DOB + ", " + hireDate + ", " + retirement;

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

    /**
     * get a instance of employee that has same ID with given parameter id
     * @param id
     * @return employee with same ID given
     * @throws IOException
     */
    public Employee getEmployee(int id) throws IOException {
        File inputFile = new File("Employee_Info.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        Employee emp;

        String department, DOB, hireDate, name;
        int ID, salary;
        double retirement;

        String currentLine;
        String strID = Integer.toString(id);

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            List<String> employeeInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
            if(employeeInfo.contains(strID)){
                department = employeeInfo.get(2);
                DOB = employeeInfo.get(4);
                hireDate = employeeInfo.get(5);
                name = employeeInfo.get(0);
                ID = Integer.parseInt(employeeInfo.get(1));
                salary = Integer.parseInt(employeeInfo.get(3));
                retirement = Double.parseDouble(employeeInfo.get(6));
                emp = new Employee(department, DOB, hireDate, name, ID, salary, retirement);

                return emp;
            }
        }
        reader.close();

        return null;
    }
}
