package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root"; // change if needed
    private static final String PASSWORD = "root"; // your MySQL password
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database Connected!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
