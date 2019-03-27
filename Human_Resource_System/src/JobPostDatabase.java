import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JobPostDatabase {
    File file;
    File file2;

    public JobPostDatabase() {
        file = new File("Job_Postings.txt");
        file2 = new File("Job_Applicants.txt");
        generateDataFile();
        generateApplicantDataFile();
    }

    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("TITLE, ID, DEPARTMENT, POST DATE, SALARY");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateApplicantDataFile(){
        if(!file2.exists()){
            try {
                file2.createNewFile();
                FileWriter fw = new FileWriter(file2, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("APPLICANT NAME, APPLICANT DOB, TITLE, JOB POST ID, DEPARTMENT, POST DATE, SALARY");
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
        else{
            String givenJobPost = s;
            generateDataFile();
            write(givenJobPost);
        }
    }

    public void writeJobApplicant(String s){
        if(file2.exists()){
            try {
                FileWriter fw = new FileWriter(file2, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(s);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            String givenApplicant = s;
            generateApplicantDataFile();
            writeJobApplicant(givenApplicant);
        }
    }

    public void addJobPost(JobPost p){
        String jobPostDetails;

        String title = p.getJobTitle();
        String department = p.getDepartment();
        String postDate = p.getPostDate();
        int ID = p.getID();
        int salary = p.getSalary();

        jobPostDetails = title + ", " + ID + ", " + department +", " + postDate + ", " + salary;

        write(jobPostDetails);
    }

    public Employee acceptApplicant(JobPost p, String name) throws IOException
    {
        boolean success = false;
        String empName = name;

        double retirement = 0;
        String DOB, department;
        String strID = Integer.toString(p.getID());
        int ID, salary;

        File inputFile = new File("Job_Applicants.txt");;
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        while(reader.ready())
        {
            List<String> applicantInfo = Arrays.asList(reader.readLine().split("\\s*,\\s*"));
            if(applicantInfo.contains(empName) && applicantInfo.contains(strID))
            {
                DOB = applicantInfo.get(1);
                department = applicantInfo.get(4);
                ID = Integer.parseInt(applicantInfo.get(3));
                salary = Integer.parseInt(applicantInfo.get(6));

                Employee emp = new Employee(department, DOB, new Date().toString(), empName, ID, salary, retirement);
                success = true;
                reader.close();


                removeJobPost(p);
                removeJobApplicants(p);

                return emp;
            }
        }
        if(!success)
        {
            System.out.println("Failed to accept applicant " + name + " for position " + p.getJobTitle());
        }

        reader.close();

        return null;
    }

    // view applicants DONE
    public void viewApplicants(JobPost p) throws IOException
    {
        String strID = Integer.toString(p.getID());
        String appName;
        String DOB, department, title, postDate;
        int ID, salary;

        File inputFile = new File("Job_Applicants.txt");;
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        while(reader.ready())
        {
            List<String> applicantInfo = Arrays.asList(reader.readLine().split("\\s*,\\s*"));
            // Make sure job ID's match
            if(applicantInfo.contains(strID))
            {
                appName = applicantInfo.get(0);
                DOB = applicantInfo.get(1);
                title = applicantInfo.get(2);
                ID = Integer.parseInt(applicantInfo.get(3));
                department = applicantInfo.get(4);
                postDate = applicantInfo.get(5);
                salary = Integer.parseInt(applicantInfo.get(6));

                System.out.println(appName + ", " + DOB + ", " + title + ", " + ID + ", " + department + ", " +
                        postDate + ", " + salary);
            }

        }

        reader.close();
    }

    // go through text file DONE
    public void viewJobPosts() throws IOException
    {
        File inputFile = new File("Job_Postings.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        while(reader.ready())
        {
            System.out.println(reader.readLine());
        }

        reader.close();
    }

    public void removeJobPost(JobPost p) throws IOException {
        File inputFile = new File("Job_Postings.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String jobPostDetails;

        String title = p.getJobTitle();
        String department = p.getDepartment();
        String postDate = p.getPostDate();
        int ID = p.getID();
        int salary = p.getSalary();

        jobPostDetails = title + ", " + ID + ", " + department +", " + postDate + ", " + salary;

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(jobPostDetails)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void removeJobApplicants(JobPost p) throws IOException
    {
        File inputFile = new File("Job_Applicants.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        int ID = p.getID();

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            List<String> applicantInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
            if(!applicantInfo.get(0).equals("APPLICANT NAME") && ID == Integer.parseInt(applicantInfo.get(3))) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    // get job post from text file DONE
    public JobPost getJobPost(int num) throws IOException
    {
        JobPost jp;
        String strID = Integer.toString(num);
        File inputFile = new File("Job_Postings.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            String department, postDate, title, ID, salary;

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                List<String> JobPostInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
                if(JobPostInfo.contains(strID)) {
                    department = JobPostInfo.get(2);
                    postDate = JobPostInfo.get(3);
                    title = JobPostInfo.get(0);
                    ID = JobPostInfo.get(1);
                    salary = JobPostInfo.get(4);

                    jp = new JobPost(department, postDate, title, Integer.parseInt(ID), Integer.parseInt(salary));

                    return jp;
                }
            }
        }

        System.out.println("Could not retrieve Job Post: " + num);

        return null;
    }

}