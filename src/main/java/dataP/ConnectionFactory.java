package dataP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 * 
 * The given model class for connecting to the database
 *
 */
public class ConnectionFactory {
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/shop?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "Caracatita9!";
	
	private static ConnectionFactory singleInstance  = new ConnectionFactory();
	
	public ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		Connection conect = null;
		try {
			conect = DriverManager.getConnection(DBURL, USER, PASS);
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to database");
			e.printStackTrace();
		}
		return conect;
	}
	
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}
	
	public static void close(Connection conect) {
		if (conect != null) 
			try {
				conect.close();
			}catch(SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection to the database");
			}
	}
	
	public static void close(Statement statement) {
		if (statement != null) 
			try {
				statement.close();
			}catch(SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
	}
	
	public static void close(ResultSet resultSet) {
		if (resultSet != null) 
			try {
				resultSet.close();
			}catch(SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the Result Set");
			}
	}
}
