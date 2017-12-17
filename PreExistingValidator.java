import java.sql.*;
import java.util.InputMismatchException;

public class PreExistingValidator {

    public static boolean validatePropertyId(int id) {
        String sql = "select count(*) > 0 as isValid from Property where propertyId = ?";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return false;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getBoolean("isValid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
