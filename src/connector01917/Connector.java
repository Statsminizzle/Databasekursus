package connector01917;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    private final String HOST     = "185.121.172.103";
    private final int    PORT     = 3306;
    private final String DATABASE = "nizzledk_cdio";
    private final String USERNAME = "nizzledk_cdio"; 
    private final String PASSWORD = "P9f7gIA5TqZ2";
    private static Connection connection;
    
    public Connector() {
        try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			System.out.println("Connected to : " + url);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
    }
    
    public Connection getConnection(){
    	return connection;
    }
    
    public static ResultSet doQuery(String query) throws SQLException{
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(query);
        return res;
    }
    
    public static void doUpdate(String query) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }
    
	public static PreparedStatement prepare(String sql) throws SQLException {
		return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	}

}
