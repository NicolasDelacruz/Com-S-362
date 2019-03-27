public class JobPost {

    private String department, postDate, title;
    private int ID, salary;


    public JobPost(String jobDepartment, String jobPostDate, String jobTitle, int jobID, int jobSalary){
        department = jobDepartment;
        postDate = jobPostDate;
        title = jobTitle;
        ID = jobID;
        salary = jobSalary;
    }


    public String getDepartment(){
        return department;
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

        String jobPostDetails = title + ", #" + ID + ", " + department +", " + postDate + ", $" + salary;

        return jobPostDetails;
    }

    public void printAvailableActions(){
        System.out.println("Add Job Post Applicants [APA], View Job Post Applicants [VPA], Return [R]");
    }
}