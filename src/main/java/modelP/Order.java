package modelP;
/**
 * 
 * Use this class to store the orders from the database
 * It only contains constructors, getters and setters
 *
 */
public class Order {
	private int id;
	private String clientName;
	private String productName;
	private int quantity;
	
	public Order(String cName, String pName, int qnt) {
		clientName = cName;
		productName = pName;
		quantity = qnt;
	}
	
	public Order(int id, String cName, String pName, int qnt) {
		this.id = id;
		clientName = cName;
		productName = pName;
		quantity = qnt;
	}
	
	public int getID() {
		return id;
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int newQnt) {
		quantity = newQnt;
	}
}
