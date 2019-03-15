import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobPostDatabase {
    File file;
    File file2;

    private ArrayList<JobPost> jobPostList = new ArrayList<JobPost>();

    public JobPostDatabase() {
        file = new File("Job_Postings.txt");
        file2 = new File("Job_Applicants.txt");
        generateDataFile();
    }

    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("TITLE, ID, DEPARTMENT, POST DATE, SALARY, DESCRIPTION");
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
                pw.println("APPLICANT NAME, APPLICANT DOB, TITLE, JOB POST ID, DEPARTMENT, POST DATE, SALARY, DESCRIPTION");
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
        jobPostList.add(p);

        String jobPostDetails;

        String title = p.getJobTitle();
        String department = p.getDepartment();
        String desc = p.getDesc();
        String postDate = p.getPostDate();
        int ID = p.getID();
        int salary = p.getSalary();

        jobPostDetails = title + ", " + ID + ", " + department +", " + postDate + ", " + salary + ", " + desc;

        writeJobApplicant(jobPostDetails);

    }

    public void acceptApplicant(JobPost p, String name)
    {
        boolean success = false;
        JobPost job = p;
        String empName = name;

        for(int i = 0; i < jobPostList.size(); i++)
        {
            if(jobPostList.get(i).equals(job))
            {
                jobPostList.get(i).accept(empName);
                jobPostList.remove(i);
                success = true;
                break;
            }
        }

        if(!success)
        {
            System.out.println("Failed to accept applicant " + name + " for position " + p.getJobTitle());
        }
    }

    public void viewJobPosts()
    {
        for(int i = 0; i < jobPostList.size(); i++)
        {
            System.out.println(i + ". " + jobPostList.get(i).getJobPost());
        }
    }

    public void removeJobPost(JobPost p) throws IOException {
        jobPostList.remove(p);

        File inputFile = new File("Job_Postings.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String jobPostDetails;

        String title = p.getJobTitle();
        String department = p.getDepartment();
        String desc = p.getDesc();
        String postDate = p.getPostDate();
        int ID = p.getID();
        int salary = p.getSalary();

        jobPostDetails = title + ", " + ID + ", " + department +", " + postDate + ", " + salary + ", " + desc;

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(jobPostDetails)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }

    public void fillJobPostList() throws IOException {
        File inputFile = new File("Job_Postings.txt");
        JobPost jp;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            String department, description, postDate, title, ID, salary;

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                List<String> JobPostInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
                if(!JobPostInfo.get(0).equals("TITLE")) {
                    department = JobPostInfo.get(2);
                    description = JobPostInfo.get(5);
                    postDate = JobPostInfo.get(3);
                    title = JobPostInfo.get(0);
                    ID = JobPostInfo.get(1);
                    salary = JobPostInfo.get(4);

                    jp = new JobPost(department, description, postDate, title, Integer.parseInt(ID), Integer.parseInt(salary));

                    jobPostList.add(jp);
                }
            }
        }
    }

    public JobPost getJobPost(int num) throws IOException {
        JobPost jp;
        fillJobPostList();
        for (int i = 0; i < jobPostList.size(); ++i) {
            jp = jobPostList.get(i);
            if (jp.getID() == num) {
                return jp;
            }
        }
        return null;
    }

}