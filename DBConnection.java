import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        
        Connection con = null;

        try {
            //load oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //Connection URL
            String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
            String user = "student_db";
            String password = "student123";

            //Establish the connection
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            }
        }
        catch(Exception e) {
            System.out.println("connection error: " + e.getMessage());
        }
        return con;
    }
}