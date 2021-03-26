package dataP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import modelP.Client;

public class ClientDAO extends DataOperations<Client> {
	/**
	 * 
	 * @param name name of the client
	 * @param addr address of the client
	 */
	public void insertClient(String name, String addr) {
		String values = " ( '"+ name + "', '"+ addr + "') ";
		String fields = " (`name`, `address`) ";
		insert(fields, values);
	}
	/**
	 * 
	 * @return the clients from the table
	 */
	public List<Client> findAll(){
		List<Client> toReturn = new ArrayList<Client>();
		Connection conect = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM `shop`." + "`client`";
		try {
			conect = ConnectionFactory.getConnection();
			statement = conect.prepareStatement(query);
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				toReturn.add(new Client(id, name, address));
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
	 * @param nameC name of the searched client
	 * @return the client from the database
	 */
	public Client findByName(String nameC) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		String query = "SELECT * FROM `shop`." + "`client`" + " WHERE `name` = ?";
		try {
			findStatement = dbConnection.prepareStatement(query);
			findStatement.setString(1, nameC);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String address = rs.getString("address");
			toReturn = new Client(name, address);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
}
