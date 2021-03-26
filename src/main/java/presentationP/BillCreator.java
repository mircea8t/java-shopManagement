package presentationP;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

public class BillCreator {
	/**
	 * Writes a bill for each successful order made by a client
	 * @param client name of the order's client
	 * @param product name of the order's product
	 * @param quantity value of the quantity
	 * @param price value of the price
	 * @param billCounter number of the bill (if we have more then 1)
	 */
	public void successfulBuy(String client, String product, int quantity, double price, int billCounter) {
		Document document = new Document();
		try {
			billCounter++;
			String fileName = "Bill" + billCounter + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			
			String billText = client + " ordered " + quantity + " " + product + " for " + price*quantity + " currency";
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk(billText, font);
			 
			document.add(chunk);
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Writes the under-stock message in a bill if the quantity of products ordered exceeds the quantity of products stored
	 * @param billCounter number of the bill (if we have more then 1)
	 */
	public void unssucess(int billCounter) {
		Document document = new Document();
		try {
			String fileName = "Bill" + billCounter + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("under-stock", font);
			 
			document.add(chunk);
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
