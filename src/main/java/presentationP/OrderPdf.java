package presentationP;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import modelP.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * This class contains the methods for creating the pdf tables for each Class in modelP package
 * It also contains methods for filling each table 
 *
 */
public class OrderPdf {
	/**
	 * 
	 * @param list list of clients for which we create the pdf table
	 * @param i the number of the order (in case we have more then 1)
	 */
	public void reportClient(List<Client> list, int i) {
		Document document = new Document();
		try {
			String fileName = "reportClient" + i + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			
			PdfPTable table = new PdfPTable(3);
			addTableHeaderClient(table);
			for(Client itC: list)
				addRowsClient(table, itC);
			 
			document.add(table);
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addTableHeaderClient(PdfPTable table) {
		table.addCell("Id");
	    table.addCell("Name");
	    table.addCell("Address");
	}
	
	private void addRowsClient(PdfPTable table, Client client) {
		String s = String.valueOf(client.getID());
	    PdfPCell c1 = new PdfPCell(new Paragraph(s));
	    table.addCell(c1);
	    s = client.getName();
	    PdfPCell c2 = new PdfPCell(new Paragraph(s));
	    table.addCell(c2);
	    s = client.getAddress();
	    PdfPCell c3 = new PdfPCell(new Paragraph(s));
	    table.addCell(c3);
	}
	/**
	 * 
	 * @param list list list of products for which we create the pdf table
	 * @param i the number of the order (in case we have more then 1)
	 * 
	 */
	public void reportProduct(List<Product> list, int i) {
		Document document = new Document();
		try {
			String fileName = "reportProduct" + i + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			
			PdfPTable table = new PdfPTable(4);
			addTableHeaderProduct(table);
			for(Product itP: list)
				addRowsProduct(table, itP);
			 
			document.add(table);
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addTableHeaderProduct(PdfPTable table) {
		table.addCell("Id");
	    table.addCell("Name");
	    table.addCell("Price");
	    table.addCell("Quantity");
	}
	
	private void addRowsProduct(PdfPTable table, Product product) {
		String s = String.valueOf(product.getID());
	    PdfPCell c1 = new PdfPCell(new Paragraph(s));
	    table.addCell(c1);
	    s = product.getName();
	    PdfPCell c2 = new PdfPCell(new Paragraph(s));
	    table.addCell(c2);
	    s = String.valueOf(product.getPrice());
	    PdfPCell c3 = new PdfPCell(new Paragraph(s));
	    table.addCell(c3);
	    s = String.valueOf(product.getQuantity());
	    PdfPCell c4 = new PdfPCell(new Paragraph(s));
	    table.addCell(c4);
	}
	/**
	 * 
	 * @param list list list of orders for which we create the pdf table
	 * @param i the number of the order (in case we have more then 1)
	 * 
	 */
	public void reportOrder(List<Order> list, int i) {
		Document document = new Document();
		try {
			String fileName = "reportOrder" + i + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			
			PdfPTable table = new PdfPTable(4);
			addTableHeaderOrder(table);
			for(Order itO: list)
				addRowsOrder(table, itO);
			 
			document.add(table);
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addTableHeaderOrder(PdfPTable table) {
		table.addCell("Id");
	    table.addCell("ClientName");
	    table.addCell("ProductName");
	    table.addCell("Quantity");
	}
	
	private void addRowsOrder(PdfPTable table, Order order) {
		String s = String.valueOf(order.getID());
	    PdfPCell c1 = new PdfPCell(new Paragraph(s));
	    table.addCell(c1);
	    s = order.getClientName();
	    PdfPCell c2 = new PdfPCell(new Paragraph(s));
	    table.addCell(c2);
	    s = order.getProductName();
	    PdfPCell c3 = new PdfPCell(new Paragraph(s));
	    table.addCell(c3);
	    s = String.valueOf(order.getQuantity());
	    PdfPCell c4 = new PdfPCell(new Paragraph(s));
	    table.addCell(c4);
	}
	
}

