import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class RetirementDatabase {
    File file;

    public RetirementDatabase() {
        file = new File("Emp_Retirements.txt");
        generateDataFile();
    }

    public void generateDataFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("EMP NAME, ID, % CHECK, % MATCH, RETIREMENT, % CHECK IRA, IRA");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String s) {
        if (file.exists()) {
            try {
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(s);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String givenJobPost = s;
            generateDataFile();
            write(givenJobPost);
        }
    }

    public void addEmpRetirement(Employee e) {
        String retirementDetails;

        String name = e.getEmployeeName();
        int ID = e.getID();
        Double perCheck = e.getRetirement();
        Double perCheckIRA = e.getIRA();

        // % Match default = 5.0
        // Retirement and IRA default = 0.0
        retirementDetails = name + ", " + ID + ", " + perCheck + ", " + 5.0 + ", " + 0.0 + ", " + perCheckIRA + ", " + 0.0;

        write(retirementDetails);
    }

    // go through text file DONE
    public void viewEmpRetirements(int givenID) throws IOException {
        File inputFile = new File("Emp_Retirements.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine;
        String strID = Integer.toString(givenID);

        boolean found = false;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            List<String> employeeInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
            if (employeeInfo.contains(strID)) {
                String name = employeeInfo.get(0);
                String id = employeeInfo.get(1);
                String check = employeeInfo.get(2);
                String match = employeeInfo.get(3);
                String retirement = employeeInfo.get(4);
                String checkIRA = employeeInfo.get(5);
                String IRA = employeeInfo.get(6);
                System.out.println("EMP NAME, ID, % CHECK, % MATCH, RETIREMENT, % CHECK IRA, IRA");
                System.out.println(name+", "+id+", "+check+", "+match+", "+retirement+", "+checkIRA+", "+IRA);

                found = true;
            }
        }
        if(!found){
            System.out.println("You are currently not in enrolled for IRA or Retirement. Do functions [IRA] or [CR] to get enrolled.");
        }
    }

    public void changeRetirementRate(int id, double pc) throws IOException {
        String strID = Integer.toString(id);

        String empName = "";
        int ID = 0, i = 0;
        String retirementDetails;
        double perCheck = 0.0, perCheckIRA = 0.0, retirement = 0.0, ira = 0.0, match = 0.0;

        File inputFile = new File("Emp_Retirements.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        while (reader.ready()) {
            List<String> applicantInfo = Arrays.asList(reader.readLine().split("\\s*,\\s*"));

            if (i != 0) {
                empName = applicantInfo.get(0);
                ID = Integer.parseInt(applicantInfo.get(1));
                perCheck = Double.parseDouble(applicantInfo.get(2));
                match = Double.parseDouble(applicantInfo.get(3));
                retirement = Double.parseDouble(applicantInfo.get(4));
                perCheckIRA = Double.parseDouble(applicantInfo.get(5));
                ira = Double.parseDouble(applicantInfo.get(6));
            }
            // If id's match, replace line in writer
            if (applicantInfo.contains(strID)) {
                perCheck = pc;

                retirementDetails = empName + ", " + ID + ", " + perCheck + ", " + match + ", " + retirement + ", " + perCheckIRA + ", " + ira;
                writer.write(retirementDetails + System.getProperty("line.separator"));
            } else if (i != 0) {
                retirementDetails = empName + ", " + ID + ", " + perCheck + ", " + match + ", " + retirement + ", " + perCheckIRA + ", " + ira;
                writer.write(retirementDetails + System.getProperty("line.separator"));
            } else {
                writer.write("EMP NAME, ID, % CHECK, % MATCH, RETIREMENT, % CHECK IRA, IRA" + System.getProperty("line.separator"));
            }

            i++;

        }

        writer.close();
        reader.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void changeIRARate(int id, double iraRate) throws IOException {
        String strID = Integer.toString(id);
        String empName = "";
        int ID = 0, i = 0;
        String retirementDetails;
        double perCheck = 0.0, perCheckIRA = 0.0, retirement = 0.0, ira = 0.0, match = 0.0;

        File inputFile = new File("Emp_Retirements.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));


        while (reader.ready()) {
            List<String> applicantInfo = Arrays.asList(reader.readLine().split("\\s*,\\s*"));

            if (i != 0) {
                empName = applicantInfo.get(0);
                ID = Integer.parseInt(applicantInfo.get(1));
                perCheck = Double.parseDouble(applicantInfo.get(2));
                match = Double.parseDouble(applicantInfo.get(3));
                retirement = Double.parseDouble(applicantInfo.get(4));
                perCheckIRA = Double.parseDouble(applicantInfo.get(5));
                ira = Double.parseDouble(applicantInfo.get(6));
            }
            // If id's match, replace line in writer
            if (applicantInfo.contains(strID)) {
                perCheckIRA = iraRate;

                retirementDetails = empName + ", " + ID + ", " + perCheck + ", " + match + ", " + retirement + ", " + perCheckIRA + ", " + ira;
                writer.write(retirementDetails + System.getProperty("line.separator"));
            } else if (i != 0) {
                retirementDetails = empName + ", " + ID + ", " + perCheck + ", " + match + ", " + retirement + ", " + perCheckIRA + ", " + ira;
                writer.write(retirementDetails + System.getProperty("line.separator"));
            } else {
                writer.write("EMP NAME, ID, % CHECK, % MATCH, RETIREMENT, % CHECK IRA, IRA" + System.getProperty("line.separator"));
            }

            i++;

        }

        writer.close();
        reader.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public boolean emplExist(int id) throws IOException {
        File inputFile = new File("Emp_Retirements.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine;
        String strID = Integer.toString(id);

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            List<String> employeeInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
            if (employeeInfo.contains(strID)) {
                return true;
            }
        }
        return false;
    }
}
