import java.io.IOException;

public interface DepartmentInterface {

    /**
     * Terminal UI that completes user's functions that are dependent on department
     * @throws IOException
     */
    void doFunctions() throws IOException;

    /**
     * @return department name
     */
    String getDepartmentName();

    /**
     * print available user functions that are dependent on department
     */
    void printAvailableOptions();
}
