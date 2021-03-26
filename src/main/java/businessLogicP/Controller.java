package businessLogicP;

import java.util.List;
import dataP.*;
import modelP.*;
import presentationP.OrderPdf;
import presentationP.BillCreator;

/**
 * Contains all the methods defined in dataP package
 * This class calls the appropriate method for the given instruction 
 */
public class Controller {
	private ClientDAO client = new ClientDAO();
	private ProductDAO product = new ProductDAO();
	private OrderDAO order = new OrderDAO();
	
	private int counterReportClient = 0;
	private int counterReportProduct = 0;
	private int counterReportOrder = 0;
	private int billCounter = 0;
	
	public void insertClient(String name, String address) {
		client.insertClient(name, address);
	}
	
	public void insertProduct(String name, int quantity, double price) {
		if(product.findByName(name) == null)
			product.insertProduct(name, price, quantity);
		else
			product.updateQuantity(name, quantity);
	}
	
	public void insertOrder(String clientName, String productName, int quantity) {
		BillCreator bill = new BillCreator();
		billCounter++;
		if (product.updateQuantity(productName, -quantity) == true) {
			order.insertOrder(clientName, productName, quantity);
			double price = product.findByName(productName).getPrice();
			bill.successfulBuy(clientName, productName, quantity, price, billCounter);
		}else
			bill.unssucess(billCounter);
	}
	
	public void deleteClient(String name) {
		client.delete(name);
	}
	
	public void deleteProduct(String name) {
		product.delete(name);
	}
	
	public void reportClient() {
		List<Client> list = client.findAll();
		OrderPdf ord = new OrderPdf();
		counterReportClient++;
		ord.reportClient(list, counterReportClient);
	}
	
	public void reportProduct() {
		List<Product> list = product.findAll();
		OrderPdf ord = new OrderPdf();
		counterReportProduct++;
		ord.reportProduct(list, counterReportProduct);
	}
	
	public void reportOrder() {
		List<Order> list = order.findAll();
		OrderPdf ord = new OrderPdf();
		counterReportOrder++;
		ord.reportOrder(list, counterReportOrder);
	}
}
