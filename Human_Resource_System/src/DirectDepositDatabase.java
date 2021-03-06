import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DirectDepositDatabase {
    File file;


    public DirectDepositDatabase() {
        file = new File("DirectDeposit.txt");
        generateDataFile();
    }

    /**
     * This method generates a file to store DepositInfos and their attributes.
     * starts first line of the file with names of each categories.
     */
    public void generateDataFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("DepositInfo ID, Employee Name, Account Number, Routing Number, Address");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * write s in the file
     * @param s - a DepositInfo's direct deposit info being added to the file generated by generateDataFile()
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
     * write direct deposit data in the file using write(String s)
     * @param e - direct deposit being added to the list of DepositInfo data
     */
    public void addDirectDeposit(DepositInfo e) throws IOException {

        if(getDirectDeposit(e.getID()) == null) {
            String DepositInfoRecord;
            // DepositInfo ID, Employee ID, Account Number, Routing Number, Address
            String address = e.getAddress();
            int routing = e.getRouting();
            String name = e.getName();
            int ID = e.getID();
            int account = e.getAccount();

            DepositInfoRecord = ID + ", " + name + ", " + account + ", " + routing + ", " + address;

            write(DepositInfoRecord);
        }

    }

    /**
     * remove DepositInfo e's data from the file
     * @param e - DepositInfo being deleted from the list of DepositInfo data
     * @throws IOException
     */
    public void removeDirectDeposit(DepositInfo e) throws IOException {
        File inputFile = new File("DirectDeposit.txt");
        File tempFile = new File("myDTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String DepositInfoRecord;

        String address = e.getAddress();
        int rounting = e.getRouting();
        String name = e.getName();
        int ID = e.getID();
        int account = e.getAccount();

        DepositInfoRecord = ID + ", " + name + ", " + account + ", " + rounting + ", " + address;
        
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(DepositInfoRecord)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }

    /**
     * get a instance of DepositInfo that has same ID with given parameter id
     * @param d
     * @return DepositInfo with same ID given
     * @throws IOException
     */
    public DepositInfo getDirectDeposit(int d) throws IOException {
        File inputFile = new File("DirectDeposit.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        DepositInfo de;

        String address;
        String name;
    	int account, routing, id;
    	
        String currentLine;
        String strID = Integer.toString(d);

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
        	// DepositInfo ID, Social Security Number, Account Number, Rounting Number, Address
            List<String> DepositInfoInfo = Arrays.asList(currentLine.split("\\s*,\\s*"));
            if(DepositInfoInfo.contains(strID)){
            	account = Integer.parseInt(DepositInfoInfo.get(2));
            	address = DepositInfoInfo.get(4);
                id = Integer.parseInt(DepositInfoInfo.get(0));
                name = DepositInfoInfo.get(1);
                routing = Integer.parseInt(DepositInfoInfo.get(3));
                de = new DepositInfo(id, address, name, account, routing);

                return de;
            }
        }
        reader.close();

        return null;
    }
}
