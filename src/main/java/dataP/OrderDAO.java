package dataP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import modelP.Order;

public class OrderDAO extends DataOperations<Order> {
	/**
	 * 
	 * @param nameClient name of the order's client
	 * @param nameProd name of the order's product
	 * @param quant value of the quantity
	 */
	public void insertOrder(String nameClient, String nameProd, int quant) {
		String values = " ( '"+ nameClient + "', '"+ nameProd + "', '"+ quant + "') ";
		String fields = " (`clientName`, `productName`, `quantity`) ";
		insert(fields, values);
	}
	/**
	 * 
	 * @return the orders from the table
	 */
	public List<Order> findAll(){
		List<Order> toReturn = new ArrayList<Order>();
		Connection conect = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM `shop`." + "`order`";
		try {
			conect = ConnectionFactory.getConnection();
			statement = conect.prepareStatement(query);
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String clientName = resultSet.getString("clientName");
				String productName = resultSet.getString("productName");
				int quantity = resultSet.getInt("quantity");
				toReturn.add(new Order(id, clientName, productName, quantity));
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
	 * @param nameC name of the order's client that we search
	 * @return the searched order
	 */
	public Order findByName(String nameC) {
		Order toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		String query = "SELECT * FROM `shop`." + "`order`" + " WHERE `clientName` = ?";
		try {
			findStatement = dbConnection.prepareStatement(query);
			findStatement.setString(1, nameC);
			rs = findStatement.executeQuery();
			rs.next();

			String clientName = rs.getString("clientName");
			String productName = rs.getString("productName");
			int quantity = rs.getInt("quantity");
			toReturn = new Order(clientName, productName, quantity);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
}
