import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class JobPost {

    private String department, desc, postDate, title;
    private int ID, salary;
    private ArrayList<JobApplicant> applicantList = new ArrayList<JobApplicant>();

    public JobPost(String jobDepartment, String jobDesc, String jobPostDate, String jobTitle, int jobID, int jobSalary){
        department = jobDepartment;
        desc = jobDesc;
        postDate = jobPostDate;
        title = jobTitle;
        ID = jobID;
        salary = jobSalary;
    }


    public String getDepartment(){
        return department;
    }

    public String getDesc(){
        return desc;
    }

    public String getPostDate(){
        return postDate;
    }

    public String getJobTitle(){
        return title;
    }

    public int getID(){
        return ID;
    }

    public int getSalary(){
        return salary;
    }

    public String getJobPost(){

        String jobPostDetails = title + ", #" + ID + ", " + department +", " + postDate + ", $" + salary + "\n \t" + desc;

        return jobPostDetails;
    }

    public void addApplicant(String newApplicant)
    {
        JobPostDatabase jpdb = new JobPostDatabase();
        jpdb.writeJobApplicant(newApplicant);
    }

    public void viewApplicants()
    {
        JobApplicant curJobApp;
        for(int i = 0; i < applicantList.size(); i++)
        {
            curJobApp = applicantList.get(i);
            System.out.println("Name: " + curJobApp.getName() + " DOB: " + curJobApp.getDOB());
        }
    }

    public void accept(String name)
    {
        boolean foundEmp = false;
        String empName = name;
        double retirement = 0;
        for(int i = 0; i < applicantList.size(); i++)
        {
            if(applicantList.get(i).getName().equals(empName))
            {
                Employee emp = new Employee(department, applicantList.get(i).getDOB(), new Date().toString(), empName, ID, salary, retirement);
                foundEmp = true;
            }
        }
        if(!foundEmp)
        {
            System.out.println("No Applicant with the name " + empName);
        }
    }

    public void doAction(JobPost jp){
        printAvailableActions();
        String command = "";

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        while (!command.equals("R")){
            System.out.println("What action would you like to do? ");
            command = reader.nextLine();

            if(command.equals("APA")){
                String newApplicant;
                // Get Name and DOB of Applicant
                System.out.println("Enter Applicants Name");
                String appName = reader.nextLine();

                System.out.println("Enter Applicants Date of Birth (DOB)");
                String appDOB = reader.nextLine();

                newApplicant = appName + ", " + appDOB + ", " + jp.getJobTitle() + ", " + jp.getID() + ", " + jp.getDepartment() + ", " + jp.getPostDate() + ", " + jp.getSalary() + ", " + jp.getDesc();
                // Add them to Job Post
                addApplicant(newApplicant);
            }
            else if(command.equals("VPA")){
                viewApplicants();
            }
        }

        System.out.println("Exiting Job Post");
    }

    public void printAvailableActions(){
        System.out.println("Add Job Post Applicants [APA], View Job Post Applicants [VPA], Return [R]");
    }
}