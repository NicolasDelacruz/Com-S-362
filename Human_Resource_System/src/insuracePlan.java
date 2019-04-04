import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class insuracePlan {
	File file;
	Scanner fileReader;

	/**
	 * Constructor of insurance plan
	 */
	public insuracePlan() {
		file = new File("insurancePlan_Info.txt");
		generateDataFile();
	}

	/**
	 * Generate insurance plan database
	 */
	public void generateDataFile(){
		if(!file.exists()){
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file, true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("NAME, ID, DEPARTMENT, SALARY, DOB, HIRE_DATE");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Write record of insurance plan to database
	 * @param s
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
	 * Add an insurance plan to an employee
	 * @param eid
	 * @param plan_type
	 * @param single_family
	 * @param add_children
	 */
	public void addInsurancePlan(int eid, int plan_type, int single_family
			, int add_children){

		int ID = eid;
		String input1 ="id:"+ID;
		String input2 ="type:" + plan_type;
		String input3 ="s/f:" + single_family;
		String input4 ="children:" + add_children;
		String input5 ="price:" + calPrice(plan_type, single_family, add_children);
		String input6 ="desc:" + getDescription(plan_type);

		write(input1);
		write(input2);
		write(input3);
		write(input4);
		write(input5);
		write(input6);
		write("END");
	}

	/**
	 * calPrice calculates the insurance plan that an individual selects depending on their current plan
	 * @param plan_type
	 * @param single_family
	 * @param add_children
	 * @return
	 */
	public int calPrice(int plan_type, int single_family, int add_children) {
		int price = 0;
		if (single_family == 0) {
			switch (plan_type) {
			case 0:
				price += 800;
				break;
			case 1:
				price += 2000;
				break;
			case 2:
				price += 5000;
				break;
			}
		}
		else {
			switch (plan_type) {
			case 0:
				price += 1500;
				break;
			case 1:
				price += 3500;
				break;
			case 2:
				price += 8000;
				break;
			}
		}

		price += 400 * add_children;
		return price;
	}

	/**
	 * Get the description of an insurance type
	 * @param plan_type
	 * @return
	 */
	public String getDescription(int plan_type) {
		String desc = "";
		switch (plan_type) {
		case 0:
			desc = "Dental check ups, medical check ups, "
					+ "$1000 deductible on operations up to $20,000";
			break;
		case 1:
			desc = "Complete medical appointments, dental check ups,"
					+ " $1000 deductible on operations up to $50,000";
			break;
		case 2:
			desc = "Full dental coverage, up to $3,000 per person outside of checkups, "
					+ "Complete medical appointments, $2000 deductible on operations up to $100,000";
			break;
		}

		return desc;
	}

	/**
	 * Change insurance plan of an employee
	 * @param eid
	 * @param plan_type
	 * @param single_family
	 * @param add_children
	 */
	public void editInsurancePlan(int eid, int plan_type, int single_family
			, int add_children) {

		try {
			File inputFile = new File("insurancePlan_Info.txt");
			File tempFile = new File("myTempFile.txt");
			//BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			Scanner fileReader = new Scanner(inputFile);
			boolean find = false;

			while(fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				if (data.startsWith("id:")) {
					int cur_id = Integer.valueOf(data.substring(3));
					if (cur_id == eid) {
						while (!data.equals("END")) {
							data = fileReader.nextLine();
						}
						find = true;
						continue;
					}
				}
				pw.println(data);
				pw.flush();
			}

			int ID = eid;
			String input1 ="id:"+ID;
			String input2 ="type:" + plan_type;
			String input3 ="s/f:" + single_family;
			String input4 ="children:" + add_children;
			String input5 ="price:" + calPrice(plan_type, single_family, add_children);
			String input6 ="desc:" + getDescription(plan_type);

			pw.println(input1);
			pw.println(input2);
			pw.println(input3);
			pw.println(input4);
			pw.println(input5);
			pw.println(input6);
			pw.println("END");
			pw.flush();

			find = false;
			pw.close();
			fileReader.close();
			tempFile.renameTo(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * View employee's insurance plan
	 * @param eid
	 */
	public void viewInsurancePlan(int eid){
		try {
			fileReader = new Scanner(file);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		getInfo(eid);
		fileReader.close();
	}

	/**
	 * Get insurance plan information
	 * @param eid
	 */
	public void getInfo(int eid) {
		boolean find = false;
		int required_id = eid;
		
		while(fileReader.hasNextLine()) {
			String data = fileReader.nextLine();
			if (data.startsWith("id:")) {
				int cur_id = Integer.valueOf(data.substring(3));
				if (cur_id == required_id) {
					find = true;
					String data1 = fileReader.nextLine();
					int old_plan_type = Integer.valueOf(data1.substring(5));
					//data1.replace(old_plan_type, Integer.toString(plan_type));
					String type ="";
					switch (old_plan_type) {
					case 0:
						type = "Basic plan";
						break;
					case 1:
						type = "Intermediate plan";
						break;
					
					case 2:
						type = "Full coverage plan";
						break;
					}
					
					String data2 = fileReader.nextLine();
					int old_single_family = Integer.valueOf(data2.substring(4));
					//data2.replace(old_single_family, Integer.toString(single_family));
					String SorF = "";
					switch (old_single_family) {
					case 0:
						SorF = "Singular plan";
						break;
					case 1:
						SorF = "Family plan";
						break;
					}
					
					String data3 = fileReader.nextLine();
					String old_add_children = data3.substring(9);
					//data3.replace(old_add_children, Integer.toString(add_children));

					String data4 = fileReader.nextLine();
					//int newPrice = calPrice(plan_type, single_family, add_children);
					String old_price = data4.substring(6);
					//data4.replace(old_price, Integer.toString(newPrice));

					String data5 = fileReader.nextLine();
					//String newDesc = getDescription(plan_type);
					String old_desc = data5.substring(5);
					//data5.replace(old_desc, newDesc);
					
					System.out.println("ID: "+ cur_id + ", insurance plan: "
							+ type +" (" +SorF +"), with " + old_add_children + " additional children");
					System.out.println("Total price is: $"+ old_price);
					System.out.println("Description:");
					System.out.println(old_desc);				
				}
			}
		}

		if ( find == false) {
			System.out.println("The insurance plan is not found!");
		}
		System.out.println("-----------------------------");
	}
}
