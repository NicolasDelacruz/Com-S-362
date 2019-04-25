import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class employeeClaimDatabase {
	File file;
	Scanner fileReader;
	public employeeClaimDatabase() {
		file = new File("employeeClaimDatabase.txt");
		generateDataFile();
	}

	public void generateDataFile(){
		if(!file.exists()){
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file, true);
				PrintWriter pw = new PrintWriter(fw);
				//pw.println("ID, YEAR, MONTH, WORK_HOURS");
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
	}

	public void addClaim(int eid, int year, int month, int date, double spend, String type){
		int ID = eid;
		String input1 ="id:"+ID;
		String input2 ="year:" + year;
		String input3 ="month:" + month;
		String input4 ="day:" + date;
		String input5 ="cost:" + spend;
		String input6 ="type:" + type;
		write(input1);
		write(input2);
		write(input3);
		write(input4);
		write(input5);
		write(input6);
	}
	
	public void viewClaim(int eid){

		try {
			fileReader = new Scanner(file);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		getInfo(eid);
		fileReader.close();
	}
	
	public void getInfo(int eid) {
		int required_id = eid;
		//int limit_days = 0;
		while (fileReader.hasNextLine()){
			String data = fileReader.nextLine();
			if (data.startsWith("id:")) {
				int cur_id = Integer.valueOf(data.substring(3));
				if (cur_id == required_id) {
					data = fileReader.nextLine();
					int start_year = Integer.valueOf(data.substring(5));
					data = fileReader.nextLine();
					int start_month = Integer.valueOf(data.substring(6));
					data = fileReader.nextLine();
					int start_day = Integer.valueOf(data.substring(4));
					data = fileReader.nextLine();
					double spend = Double.valueOf(data.substring(5));
					data = fileReader.nextLine();
					String type = data.substring(5);
					System.out.println("Emplyee ID: "+ cur_id + " have a " + type + " reimbursement claim on "
							+ start_year +"/"+start_month+"/"+start_day + ", cost: $"+
							spend);
				}
				else {
					data = fileReader.nextLine();
				}
			}
			else {
				data = fileReader.nextLine();
			}
		}
	}
}
