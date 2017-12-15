
import java.sql.*;

/**
 * Make sure to replace your database name and user and password!!!!!!!!
 */
public class Connect {

    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/db_hoams?useSSL=true";
    private static final String SELECT_USER = "select firstname from User where username=? and password=?";

    public static String validateCredential(String username, String password) {
        String firstName = null;
        try (Connection conn = getConnection("student", "student");) {
            if (conn != null) {
                //System.out.println("connection successful !!!");
                PreparedStatement statement = conn.prepareStatement(SELECT_USER);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet user = statement.executeQuery();
                if (user.next()) {
                    firstName = user.getString("firstName");
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("28000")) {
                System.out.println("Access Denied - Make sure the Database Server is setup correctly for this application.");
                return null;
            }
            e.printStackTrace();
        }
        return firstName;
    }

    /**
     * This method returns a connection to the database
     */
    private static Connection getConnection(String dbUser, String dbPassword) throws SQLException {
        Connection conn = null;
        String url = CONN_STRING;

        try {
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (SQLRecoverableException e) {
            System.out.println("Couldn't communicate to the Database Server, make sure the server is accessible");
        }

        return conn;
    }
}

