import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPostgreSQLConnect {
	private final String url = "jdbc:postgresql://localhost/";
	private final String user = "postgres";
	private final String password = "1456";
	
	private void connect() {
		try(Connection connection = DriverManager.getConnection(url, user, password);){
				if(connection != null)
					System.out.println("Connection Succesful to PostgreSQL server...");
				else
					System.out.println("Failed to connect to PostgreSQL server...");
				
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
				if(resultSet.next()) {
					System.out.println(resultSet.getString(1));
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public static void main(String[] args) {
		JDBCPostgreSQLConnect sqlconnect = new JDBCPostgreSQLConnect();
		sqlconnect.connect();
		
	}
}
