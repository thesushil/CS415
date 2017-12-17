import java.sql.*;
import java.util.Scanner;

public class HomeOwner implements Entity {
    public void listAll() {
        System.out.println("============================= Home Owners ====================================");
        HomeOwner.printHomeOwners();
        System.out.println("==============================================================================");
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

    public void displayDetails(int id) {
        System.out.println("================================== Home Owner Details =========================================");
        HomeOwner.printHomeOwnerDetails(id);
        System.out.println("===============================================================================================");
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

    public void addNew() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter First Name:");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name:");
        String lastName = scanner.nextLine();

        System.out.print("Enter Phone Number:");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Email:");
        String email = scanner.nextLine();

        int id = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter Property Id:");
            id = scanner.nextInt();
            isValid = PreExistingValidator.validatePropertyId(id);
            if (!isValid) System.out.println("Invalid Property Id! Try again.");
        }

        try {
            HomeOwner.insert(firstName, lastName, phoneNumber, email, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insert
            (String firstName, String lastName, String phoneNumber, String email, int propertyId)
            throws SQLException {
        String sql = "insert Homeowner (firstName, lastName, phoneNumber, email, propertyId) "
                + "values (?, ?, ?, ? ,?)";

        try (Connection conn = Connect.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phoneNumber);
            statement.setString(4, email);
            statement.setInt(5, propertyId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Successfully added new owner!");
            } else {
                System.out.println("Error Occurred!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id){
        System.out.print("Enter New Last Name:");
        String lastName = new Scanner(System.in).nextLine();
        updateLastName(id, lastName);
    }

    public void updateLastName(int id, String lastName) {
        String sql = "update Homeowner set lastName = ? where ownerId = ?";

        try (Connection conn = Connect.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, lastName);
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Successfully updated last name!");
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred!");
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "delete from Homeowner where ownerId = ?";

        try (Connection conn = Connect.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Successfully deleted owner!");
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred!");
            System.out.println(e.getMessage());
        }
    }
}
