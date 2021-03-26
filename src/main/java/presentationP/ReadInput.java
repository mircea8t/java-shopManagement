package presentationP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import businessLogicP.Controller;

public class ReadInput {
	private Controller ctrl = new Controller();
	private String file;
	/**
	 * 
	 * @param inputFile contains the name of the text file from which we read
	 */
	public ReadInput(String inputFile) {
		file = inputFile;
	}
	/**
	 * Use a scanner to read information from the file
	 * After 1 line read, the controller will execute the instruction written in the file
	 */
	public void getInfo(){
		String path  =System.getProperty("user.dir");
		path = path + "\\" + file;
		try {
			File fil = new File(path);
			Scanner s  = new Scanner(fil);
			while(s.hasNextLine()) {
				String comand = s.next();
				if(comand.contains("Report")) {
					String table = s.next();
					if(table.contentEquals("client")) {
						ctrl.reportClient();
					}
					if(table.contentEquals("product")) {
						ctrl.reportProduct();
					}
					if(table.contentEquals("order")) {
						ctrl.reportOrder();	
					}
				}	
				if(comand.contentEquals("Insert")) {
					String table = s.next();
					if (table.contentEquals("client:"))
						client(s);
					else  
						if(table.contentEquals("product:"))
							product(s);
				}
				if(comand.contentEquals("Order:")) {
					order(s);
				}
				if(comand.contentEquals("Delete")) {
					String table = s.next();
					if(table.contentEquals("client:"))
						deleteClient(s);
					else
						deleteProduct(s);
				}
			}
			s.close();
			
		}catch(FileNotFoundException e) {	
			System.out.print("The file is incorrect");
		}
	}
	/**
	 * 
	 * @param s the Scanner
	 * it calls the insertClient method from controller
	 */
	private void client(Scanner s) {
		String elementName = s.next();
		while(elementName.charAt(elementName.length() - 1) != ',')
			elementName = elementName + " " + s.next();
		elementName = elementName.substring(0, elementName.length() - 1);
		String address = s.next();
		ctrl.insertClient(elementName, address);
	}
	/**
	 * 
	 * @param s the Scanner
	 * it calls the insertProduct method from controller
	 */
	private void product(Scanner s) {
		String elementName = s.next();
		while(elementName.charAt(elementName.length() - 1) != ',')
			elementName = elementName + " " + s.next();
		elementName = elementName.substring(0, elementName.length() - 1);
		String quant = s.next();
		int q = Integer.parseInt(quant.substring(0, quant.length() - 1));
		float price = s.nextFloat();
		ctrl.insertProduct(elementName, q, price);
	}
	/**
	 * 
	 * @param s the Scanner
	 * it calls the insertOrder method from controller
	 */
	private void order(Scanner s) {
		String clientName = s.next();
		while(clientName.charAt(clientName.length() - 1) != ',')
			clientName = clientName + " " + s.next();
		clientName = clientName.substring(0, clientName.length() - 1);
		String productName = s.next();
		while(productName.charAt(productName.length() - 1) != ',')
			productName = productName + " " + s.next();
		productName = productName.substring(0, productName.length() - 1);
		int quant = s.nextInt();
		ctrl.insertOrder(clientName, productName, quant);
	}
	/**
	 * 
	 * @param s the Scanner
	 * it calls the deleteClient method from controller
	 */
	private void deleteClient(Scanner s) {
		String clientName = s.next();
		while(clientName.charAt(clientName.length() - 1) != ',')
			clientName = clientName + " " + s.next();
		clientName = clientName.substring(0, clientName.length() - 1);
		ctrl.deleteClient(clientName);
	}
	/**
	 * 
	 * @param s the Scanner
	 * it calls the deleteProduct from controller
	 */
	private void deleteProduct(Scanner s){
		String productName = s.next();
		ctrl.deleteProduct(productName);
	}
}
