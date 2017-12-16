import java.sql.*;

public class HomeProperty {

    public static void listAll() {
        System.out.println("============================== Properties ====================================");
        printHomeProperties();
        System.out.println("==============================================================================");
    }

    public static void displayDetails(int id) {
        System.out.println("============================== Property Details ====================================");
        printPropertyDetails(id);
        System.out.println("=====================================================================================");
    }

    private static void printHomeProperties() {
        String sql = "select propertyId, street, city, state, zipCode from property";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String displayFormat = "%-3s%-30s%-30s%-30s%-30s";
            System.out.println(String.format(displayFormat, "Id", "Street", "City", "State", "Zip Code"));
            while (result.next()) {
                int id = result.getInt("propertyId");
                String street = result.getString("street");
                String city = result.getString("city");
                String state = result.getString("state");
                String zip = result.getString("zipCode");
                System.out.println(String.format(displayFormat, id, street, city, state, zip));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printPropertyDetails(int id) {
        String sql = "select street, city, state, zipCode from property where propertyId = ?";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String street = result.getString("street");
                System.out.printf("Street:%s", street);
                String city = result.getString("city");
                System.out.printf("\tCity:%s", city);
                String state = result.getString("state");
                System.out.printf("\tState:%s", state);
                String zip = result.getString("zipCode");
                System.out.printf("\tZip Code:%s%n", zip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
