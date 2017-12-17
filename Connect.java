
import java.sql.*;

public class Connect {

    public static String validateCredential(String username, String password) {
        final String sql = "select firstname from User where username=? and password=SHA(?)";
        String firstName = null;
        try (Connection conn = getConnection()) {
            if (conn == null) return null;

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet user = statement.executeQuery();
            if (user.next()) {
                firstName = user.getString("firstName");
            } else {
                System.out.println("Invalid username or password. Please try again.");
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
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_hoams?useSSL=true";
        String dbUser = "student", dbPassword = "student";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (SQLRecoverableException e) {
            System.out.println("Couldn't communicate to the Database Server, make sure the server is accessible");
        }

        return conn;
    }
}

