import java.sql.*;

public class Assessment implements Entity {

    public void listAll() {
        System.out.println("============================== Assessments ====================================");
        printHomeAssessments();
        System.out.println("================================================================================");
    }

    public void displayDetails(int id) {
        System.out.println("============================== Assessment Details ====================================");
        printAssessmentDetails(id);
        System.out.println("=====================================================================================");
    }

    public void addNew() {

    }

    public void update(int id) {

    }

    public void delete(int id) {
        String sql = "delete from assessment where assmtId = ?";

        try (Connection conn = Connect.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Successfully deleted Assessment!");
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred!");
            System.out.println(e.getMessage());
        }
    }

    private static void printHomeAssessments() {
        String sql = "select assmtId, amount, frequency from assessment";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String displayFormat = "%-4s%-10s%-10s";
            System.out.println(String.format(displayFormat, "Id", "Amount", "Frequency"));
            while (result.next()) {
                int id = result.getInt("assmtId");
                int amount = result.getInt("amount");
                String frequency = result.getString("frequency");
                System.out.println(String.format(displayFormat, id, amount, frequency));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printAssessmentDetails(int id) {
        String sql = "select amount, frequency from assessment where assmtId = ?";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int amount = result.getInt("amount");
                System.out.printf("Amount:%s", amount);
                String frequency = result.getString("frequency");
                System.out.printf("\tFrequency:%s%n", frequency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
