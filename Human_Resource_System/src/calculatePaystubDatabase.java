import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class calculatePaystubDatabase {
	File file;
	Scanner fileReader;

	public calculatePaystubDatabase() {
		file = new File("calculatePaystubDatabase.txt");
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

	public void addPaystub(int eid, String beginDate, String endDate, double rate, double hours,
			double vacationUsedHours, double taxRate,double employeePaidBenefits){
		int ID = eid;
		String input1 ="id:"+ID;
		String input2 ="beginDate:" + beginDate;
		String input3 ="endDate:" + endDate;
		String input4 ="rate:" + rate;
		String input5 ="hours:" + hours;
		String input6 ="vac_hr:" + vacationUsedHours;
		String input7 ="taxRate:" + taxRate;
		String input8 ="ePB:" + employeePaidBenefits;
		//calculated
		double earning = (hours+ vacationUsedHours) * rate;
		String input9 ="earning:" + earning;
		String input10 ="netPay:" + (earning -employeePaidBenefits) * (1 - taxRate/100) ;
		write(input1);
		write(input2);
		write(input3);
		write(input4);
		write(input5);
		write(input6);
		write(input7);
		write(input8);
		write(input9);
		write(input10);
	}
	
	public void viewPlaystub(int eid){

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
					String beginDate = data.substring(10);
					data = fileReader.nextLine();
					String endDate = data.substring(8);
					data = fileReader.nextLine();
					double rate = Double.valueOf(data.substring(5));
					data = fileReader.nextLine();
					double hours = Double.valueOf(data.substring(6));
					data = fileReader.nextLine();
					double vac_hr = Double.valueOf(data.substring(7));
					data = fileReader.nextLine();
					double taxRate = Double.valueOf(data.substring(8));
					data = fileReader.nextLine();
					double ePB = Double.valueOf(data.substring(4));
					data = fileReader.nextLine();
					double earning = Double.valueOf(data.substring(8));
					data = fileReader.nextLine();
					double netPay = Double.valueOf(data.substring(7));
				
					System.out.println("Paystub: Emplyee ID: "+ cur_id);
					System.out.println("Begin Date: " + beginDate +", End Date: " + endDate);
					System.out.println("Wage Rate: " + rate +", Tax Rate: " + taxRate+"%");
					System.out.println("Working Hours: " + hours +", Vacation Used Hours:" + vac_hr);
					System.out.println("Employee Paid Benefits: " + ePB);
					System.out.println(" Total earning: " + earning + ", Net Pay " +netPay);
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
