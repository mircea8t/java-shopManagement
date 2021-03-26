package modelP;
/**
 * 
 * Use this class to store the products from the database
 * It only contains constructors, getters and setters
 *
 */
public class Product {
	private int id;
	private String name;
	private double price;
	private int quantity;
	
	public Product(String nme, double prc, int qnt) {
		name = nme;
		price = prc;
		quantity  = qnt;
	}
	public Product(int id, String nme, double prc, int qnt) {
		this.id = id;
		name = nme;
		price = prc;
		quantity  = qnt;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String newNme) {
		name = newNme;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double newPrc) {
		price = newPrc;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int newQnt) {
		quantity = newQnt;
	}
}
