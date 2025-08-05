package JDprogram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/muthustudents_db"; 
        String username = "root";         
        String password = "Muthu@123";    
        return DriverManager.getConnection(url, username, password);
    }
}
