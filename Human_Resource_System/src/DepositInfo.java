
public class DepositInfo {
	String address;
	String name;
	int account, routing, id;
	
	public DepositInfo(int eid, String eaddress, String givenName, int eaccount, int erouting) {
		id = eid;
		address = eaddress;
		name = givenName;
		account = eaccount;
		routing = erouting;
	}
	
	public int getID() {
		return id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAccount() {
		return account;
	}
	
	public int getRouting() {
		return routing;
	}
}
