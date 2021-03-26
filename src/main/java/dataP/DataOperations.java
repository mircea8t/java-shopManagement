package dataP;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * It contains the insert, delete, update operations for our 3 tables in the database
 *
 * @param <T> will be replaced with one of the 3 classes from the modelP package
 */
public class DataOperations<T> {
	protected static final Logger LOGGER = Logger.getLogger(DataOperations.class.getName());
	private final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public DataOperations() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	/**
	 * 
	 * @param name the name of the element we seracj to delete
	 */
	public void delete(String name) {
		Connection conect = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "DELETE FROM shop." + type.getSimpleName() + " WHERE  `name` = " + "'" + name + "'";
		try {
			conect = ConnectionFactory.getConnection();
			statement = conect.prepareStatement(query);
			statement.executeUpdate(query);
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(conect);
		}
	}
	/**
	 * 
	 * @param fields contains the names of the fields for the respective table
	 * @param values contains the values that we will insert in the table
	 */
	public void insert(String fields, String values) {
		Connection conect = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "INSERT INTO `shop`." + "`"+ type.getSimpleName() + "`" + fields + " VALUES " + values;
		try {
			conect = ConnectionFactory.getConnection();
			statement = conect.prepareStatement(query);
			statement.executeUpdate(query);
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(conect);
		}
	}
	/**
	 * 
	 * @param name is the name of the element we search to update
	 * @param newQuant is the value of the new quantity
	 */
	public void update(String name, int newQuant) {
		Connection conect = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "UPDATE `shop`." + "`"+ type.getSimpleName() + "`" + " SET `quantity` =  " + "'" + newQuant + "'" + " WHERE (`name` = " + "'" + name + "' )";
		try {
			conect = ConnectionFactory.getConnection();
			statement = conect.prepareStatement(query);
			statement.executeUpdate(query);
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		}finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(conect);
		}
	} 
}
