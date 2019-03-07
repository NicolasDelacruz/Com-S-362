import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class EmployeeInformation {
    File file;


    public EmployeeInformation() {
        file = new File("file.txt");
        generateDataFile();
    }

    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                PrintWriter pw = new PrintWriter(file);
                pw.println("ID, SEX, SALARY, DOB, HIRE_DATE");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
