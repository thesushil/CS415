import java.sql.*;

public class HomeOwner {

    public static void listAll() {
        System.out.println("============================= Home Owners ====================================");
        printHomeOwners();
        System.out.println("==============================================================================");
    }

    public static void displayDetails(int id) {
        System.out.println("============================= Home Owner Details ====================================");
        printHomeOwnerDetails(id);
        System.out.println("=====================================================================================");
    }

    private static void printHomeOwners() {
        String sql = "select OwnerId, FirstName, LastName from Homeowner";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String displayFormat = "%-3s%-30s%-30s";
            System.out.println(String.format(displayFormat, "Id", "First Name", "Last Name"));
            while (result.next()) {
                int ownerId = result.getInt("OwnerId");
                String firstName = result.getString("FirstName");
                String lastName = result.getString("LastName");
                System.out.println(String.format(displayFormat, ownerId, firstName, lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printHomeOwnerDetails(int id) {
        String sql = "select firstName, lastName, phoneNumber, email from Homeowner where ownerid = ?";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String firstName = result.getString("firstName");
                System.out.printf("First Name:%s", firstName);
                String lastName = result.getString("lastName");
                System.out.printf("\tLast Name:%s", lastName);
                String phoneNumber = result.getString("phoneNumber");
                System.out.printf("\tPhone Number:%s", phoneNumber);
                String email = result.getString("email");
                System.out.printf("\tEmail:%s%n", email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
