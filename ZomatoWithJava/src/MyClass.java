

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyClass {
	
	public static Connection letConnection() {
		
		Connection com = null;
		String url, username = null, password = null;
		
		url = "jdbc:mysql://localhost:3306/zomato";
		username = "root";
		password = "123456";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			com = DriverManager.getConnection(url, username, password);
			System.out.println("Connectd..:)");
			
			
		} catch (SQLException | ClassNotFoundException e) {

			e.printStackTrace();
		}		
		return com;		
	}

}
