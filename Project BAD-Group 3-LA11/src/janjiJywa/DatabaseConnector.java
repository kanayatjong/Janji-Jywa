package janjiJywa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

	private final String HOST = "localhost";
	private final String PORT = "3306";
	private final String DATABASE_NAME = "janji_jywa";
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	
	private final String CONNECTION_STRING = String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DATABASE_NAME);
	
	private Connection connection;
	private Statement statement;
	
	private static DatabaseConnector connector = null;
	
	public static DatabaseConnector getConnector() {
		if (connector == null) {
			synchronized (DatabaseConnector.class) {
				if (connector == null) {
					connector = new DatabaseConnector();
				}
			}
		}
		return connector;
	}
	
	private DatabaseConnector() {
		try {
			// Load MySQL driver
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			// Get Connection from the driver
			connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
			
			// Create statement
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) {
		// Untuk select data
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean execute(String query) {
		// Untuk update, delete, insert
		try {
			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
