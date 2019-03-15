public class JobApplicant {

    private String name, DOB;

    public JobApplicant(String appName, String appDOB){
        name = appName;
        DOB = appDOB;
    }


    public String getName(){
        return name;
    }

    public String getDOB(){
        return DOB;
    }

}