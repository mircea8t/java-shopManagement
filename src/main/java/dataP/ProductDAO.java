package dataP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import modelP.Product;

public class ProductDAO extends DataOperations<Product> {
	/**
	 * 
	 * @param name the name of product to be inserted in table
	 * @param price the value of the price to be inserted
	 * @param quant the value of the quantity to be inserted
	 */
	public void insertProduct(String name,  double price, int quant) {
		String values = " ( '"+ name + "', '"+ price + "', '"+ quant + "') ";
		String fields = " (`name`, `price`, `quantity`) ";
		insert(fields, values);
	}
	/**
	 * 
	 * @param name the name of the product
	 * @param newQuantity the value that we add to the old one for a new quantity
	 * @return
	 */
	public boolean updateQuantity(String name, int newQuantity) {
		Product aux = findByName(name);
		int auxQuantity = aux.getQuantity() + newQuantity;
		if (auxQuantity >= 0) {
			update(name, auxQuantity);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return the products from the table
	 */
	public List<Product> findAll(){
		List<Product> toReturn = new ArrayList<Product>();
		Connection conect = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM `shop`." + "`product`";
		try {
			conect = ConnectionFactory.getConnection();
			statement = conect.prepareStatement(query);
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				int quantity = resultSet.getInt("quantity");
				toReturn.add(new Product(id, name, price, quantity));
			}
			return toReturn;
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "DAO:findAll " + e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(conect);
		}
		return null;
	}
	/**
	 * 
	 * @param nameC name of the searched product
	 * @return the searched product from the table
	 */
	public Product findByName(String nameC) {
		Product toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		String query = "SELECT * FROM `shop`." + "`product`" + " WHERE `name` = ?";
		try {
			findStatement = dbConnection.prepareStatement(query);
			findStatement.setString(1, nameC);
			rs = findStatement.executeQuery();
			if (rs != null) {
				rs.next();
				String name = rs.getString("name");
				Double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				toReturn = new Product(name, price, quantity);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
}
