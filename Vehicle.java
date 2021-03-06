import java.sql.*;

public class Vehicle implements Entity {
    public void listAll() {
        System.out.println("====================================== Vehicles ========================================");
        printVehicles();
        System.out.println("========================================================================================");
    }

    public void displayDetails(int id) {
        System.out.println("====================================== Vehicle Details ===========================================");
        printVehicleDetails(id);
        System.out.println("==================================================================================================");
    }

    public void addNew() {

    }

    public void update(int id) {

    }

    public void delete(int id) {
        String sql = "delete from vehicle where vehicleId = ?";

        try (Connection conn = Connect.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Successfully deleted Vehicle!");
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred!");
            System.out.println(e.getMessage());
        }
    }

    private static void printVehicles() {
        String sql = "select vehicleId, numberPlate, stateRegistered, year, make, model, color from vehicle";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String displayFormat = "%-5s%-15s%-20s%-6s%-15s%-15s%-10s";
            System.out.println(String.format(displayFormat, "Id", "Number Plate", "Registered State", "Year", "Make", "Model", "Color"));
            while (result.next()) {
                int id = result.getInt("vehicleId");
                String numberPlate = result.getString("numberPlate");
                String stateRegistered = result.getString("stateRegistered");
                int year = result.getInt("Year");
                String make = result.getString("make");
                String model = result.getString("model");
                String color = result.getString("color");
                System.out.println(String.format(displayFormat, id, numberPlate, stateRegistered, year, make, model, color));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printVehicleDetails(int id) {
        String sql = "select numberPlate, stateRegistered, year, make, model, color from vehicle where vehicleId = ?";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String numberPlate = result.getString("numberPlate");
                System.out.printf("Number Plate:%s", numberPlate);
                String stateRegistered = result.getString("stateRegistered");
                System.out.printf("\tRegistered State:%s", stateRegistered);
                int year = result.getInt("year");
                System.out.printf("\tYear:%s", year);
                String make = result.getString("make");
                System.out.printf("\tMake:%s", make);
                String model = result.getString("model");
                System.out.printf("\tModel:%s", model);
                String color = result.getString("color");
                System.out.printf("\tColor:%s%n", color);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
