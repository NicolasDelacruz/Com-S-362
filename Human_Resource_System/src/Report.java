import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Report {
    File file;
    BufferedReader reader;

    int retirementMatch;
    int eventExpense;
    int numberOfEmpl;
    int numberOfEvents;
    int maxExpense;
    int numberOfMatches;
    int maxMatch;

    double hoursWorked;

    String home;

    List<String> departmentNames;


    public Report(){
        home = System.getProperty("user.home");

        retirementMatch = 0;
        eventExpense = 0;
        numberOfEmpl = 0;
        hoursWorked = 0;
        numberOfEvents = 0;
        maxExpense = 0;
        numberOfMatches = 0;
        maxMatch = 0;

        departmentNames = new ArrayList<>();
    }

    public boolean createExpenseReport() throws IOException {
        file = new File(home+"/Downloads/Expense_Report.txt");

        int eventExpense = getEventExpense();
        int retirementExpense = getRetirementMatch();

        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("EXPENSE REPORT FOR FISCAL YEAR: " + Calendar.getInstance().get(Calendar.YEAR));
                pw.println("--------------------------------------------");
                pw.println();
                pw.println("Expense report for company events");
                pw.println("Number of events: " + numberOfEvents);
                pw.println("Most expensive event: " + maxExpense);
                pw.println("Total amount spent on events: " + eventExpense);
                pw.println("--------------------------------------------");
                pw.println();
                pw.println("Expense report for employee retirement company match");
                pw.println("Number of employees matched: " + numberOfMatches);
                pw.println("Highest employee match: " + maxMatch);
                pw.println("Total amount spent on retirement matches: " + retirementExpense);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            return false;
        }
        return true;
    }

    public boolean createDepartmentReport() throws IOException {
        file = new File(home+"/Downloads/Department_Report.txt");

        getDepartments();

        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("Up to date department statistics");
                pw.println("--------------------------------------------");
                pw.println();
                for(int i = 0; i < departmentNames.size(); ++i){
                    int depNumEmp = getNumberOfEmployees(departmentNames.get(i));
                    double depHoursWorked = getHoursWorked(departmentNames.get(i));
                    hoursWorked = getTotalHoursWorked();

                    pw.println(departmentNames.get(i) + " report: ");
                    pw.println("Number of employees: " + depNumEmp);
                    pw.println("Number of hours worked: " + depHoursWorked);
                    pw.println("--------------------------------------------");
                    pw.println();
                }
                pw.println("Total amount company hours worked: " + hoursWorked);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            return false;
        }
        return true;
    }

    public int getEventExpense() throws IOException {
        File inputFile = new File("Events.txt");
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        String currentLine;
        List<String> eventInfo;

        while ((currentLine = reader.readLine()) != null) {
           eventInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
            if(eventInfo.size() == 1 && eventInfo.get(0).equals("BEGIN OF EVENT") && !eventInfo.get(0).equals("Event Database")){
                currentLine = reader.readLine();
                eventInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
                eventExpense += Integer.valueOf(eventInfo.get(5));
                numberOfEvents++;
                if(Integer.valueOf(eventInfo.get(5)) > maxExpense){
                    maxExpense = Integer.valueOf(eventInfo.get(5));
                }
            }
        }
        reader.close();
        return eventExpense;
    }

    public int getRetirementMatch() throws IOException {
        numberOfEmpl = 0;
        File inputFile = new File("Employee_Info.txt");
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        String currentLine;
        List<String> employeeInfo;

        while ((currentLine = reader.readLine()) != null) {
            if(!currentLine.equals("NAME, ID, DEPARTMENT, SALARY, DOB, HIRE_DATE, RETIREMENT")) {
                employeeInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
                double temp = Double.valueOf(employeeInfo.get(6));
                retirementMatch += (int) temp;
                if(temp > maxMatch){
                    maxMatch = (int) temp;
                }
                if(temp > 0 ) {
                    numberOfMatches++;
                }
                numberOfEmpl++;
            }
        }
        reader.close();
        return retirementMatch;
    }

    public int getNumberOfEmployees(String department) throws IOException {
        int numEmp = 0;
        numberOfEmpl = 0;
        File inputFile = new File("Employee_Info.txt");
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        String currentLine;
        List<String> employeeInfo;

        while ((currentLine = reader.readLine()) != null) {
            if(!currentLine.equals("NAME, ID, DEPARTMENT, SALARY, DOB, HIRE_DATE, RETIREMENT")) {
                employeeInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));

                if(!departmentNames.contains(employeeInfo.get(2))){
                    departmentNames.add(employeeInfo.get(2));
                }

                if(employeeInfo.get(2).equals(department)){
                    numEmp++;
                }
                numberOfEmpl++;
            }
        }

        reader.close();
        return numEmp;
    }

    public void getDepartments() throws IOException {
        numberOfEmpl = 0;
        File inputFile = new File("Employee_Info.txt");
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String currentLine;
        List<String> employeeInfo;

        while ((currentLine = reader.readLine()) != null) {
            if(!currentLine.equals("NAME, ID, DEPARTMENT, SALARY, DOB, HIRE_DATE, RETIREMENT")) {
                employeeInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));

                if(!departmentNames.contains(employeeInfo.get(2))){
                    departmentNames.add(employeeInfo.get(2));
                }
            }
        }

        reader.close();
    }

    public double getHoursWorked(String department) throws IOException {
        double depHours = 0;
        numberOfEmpl = 0;
        File inputFile = new File("Work_Hours.txt");
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        String currentLine;
        List<String> workHoursInfo;

        while ((currentLine = reader.readLine()) != null) {
            if(!currentLine.equals("EMPLOYEE ID, DATE, TIME WORKED")) {
                workHoursInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));

                employeeDatabase e = new employeeDatabase();
                Employee emp = e.getEmployee(Integer.valueOf(workHoursInfo.get(0)));
                if(emp.getDepartment().equals(department)){
                    depHours += Double.valueOf(workHoursInfo.get(2));
                }
            }
        }

        reader.close();
        return depHours;
    }

    public double getTotalHoursWorked() throws IOException {
        double depHours = 0;
        numberOfEmpl = 0;
        File inputFile = new File("Work_Hours.txt");
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        String currentLine;
        List<String> workHoursInfo;

        while ((currentLine = reader.readLine()) != null) {
            if(!currentLine.equals("EMPLOYEE ID, DATE, TIME WORKED")) {
                workHoursInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
                depHours += Double.valueOf(workHoursInfo.get(2));
            }
        }
        reader.close();
        return depHours;
    }
}
