
public class DepositInfo {
	String address;
	int empID, account, routing, id;
	
	public DepositInfo(int eid, String eaddress, int empIDGiven, int eaccount, int erouting) {
		id = eid;
		address = eaddress;
		empID = empIDGiven;
		account = eaccount;
		routing = erouting;
	}
	
	public int getID() {
		return id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getEmpID() {
		return empID;
	}
	
	public int getAccount() {
		return account;
	}
	
	public int getRouting() {
		return routing;
	}
}
