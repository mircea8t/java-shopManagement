package modelP;
/**
 * 
 * Use this class to store the clients from the database
 * It only contains constructors, getters and setters
 *
 */
public class Client {
	private int id;
	private String name;
	private String address;
	
	public Client(String nname, String addr) {
		name = nname;
		address = addr;
	}
	
	public Client(int id, String nname, String addr) {
		this.id = id;
		name = nname;
		address = addr;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String newAddr) {
		address = newAddr;
	}
}
