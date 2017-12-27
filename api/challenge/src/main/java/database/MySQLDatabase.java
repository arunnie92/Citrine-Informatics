package database;

import data.Chemical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class MySQLDatabase {
	private final String username = "root";
	private final String password = "password";
	private final String connectionURL = "jdbc:mysql://localhost/citrineinformatics";
	private static Connection connection;
	
	public MySQLDatabase() throws SQLException {	
		try {
			
			connection = DriverManager.getConnection(
					connectionURL, username, password);
			System.out.println("Connected to database: " + connectionURL);
		} catch (SQLException e) {
			System.out.println("ERROR: " +e);
		} finally {
			// import csv file
			connection.close();
		}
	}

	// import any added/extra csv file
	public boolean importCSV(String csvFileName) throws SQLException{
		Statement stmt = null;
		
		try {
			stmt = openConnection().createStatement();
			StringBuilder query = new StringBuilder();
			query.append("BULK INSERT data FROM ")
				 .append("'").append(csvFileName).append("' ")
				 .append("WTIH ( FIELDTERMINATOR = ',', ROWTERMINATOR = '\\n'");
			stmt.executeUpdate(query.toString());
		} catch (SQLException e) {
			
		}
		
		return true;
	}
	
	public boolean dataExist(Chemical chemical) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM data WHERE ")
		     .append("Chemical_formula=").append("'").append(chemical.getChemical_formula()).append("' AND ")
		     .append("Property_1_name= ").append("'").append(chemical.getProperty_1_name()).append("' AND ")
		     .append("Property_1_value= ").append(chemical.getProperty_1_value()).append(" AND ")
		     .append("Property_2_name= ").append("'").append(chemical.getProperty_2_name()).append("' AND ")
		     .append("Property_2_value= ").append("'").append(chemical.getProperty_2_value()).append("'");
		
		Statement stmt = null; 
		ResultSet result = null; 
		try {
			stmt = openConnection().createStatement();
			result = stmt.executeQuery(query.toString());
		} catch (SQLException e) {
			
		}
		closeConnection();
		
		if (!result.next()) {
			return false;
		}
		return true;
	}
	
	public JsonArray getData(String query) throws SQLException {
		Statement stmt = null; 
		ResultSet result = null; 
		try {
			stmt = openConnection().createStatement();
			result = stmt.executeQuery(query);
			closeConnection();
		} catch (SQLException e) {
			
		}
		
		return toJson(result);
	}
	
	public boolean addRemoveData(String query) throws SQLException {
		Statement stmt = null; 
		try {
			stmt = openConnection().createStatement();
			stmt.executeUpdate(query);
			closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	private JsonArray toJson(ResultSet rs) throws SQLException {
		JsonArray result = new JsonArray();
		while(rs.next()) {
			 int total_rows = rs.getMetaData().getColumnCount();
	            JsonObject obj = new JsonObject();
	            for (int i = 0; i < total_rows; i++) {
	                obj.addProperty(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getString(i + 1));
	            }
		  result.add(obj);
		}
		return result;
	}
	
	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(
				connectionURL, username, password);
	}
	
	private void closeConnection() throws SQLException {
		connection.close();
	}
}
