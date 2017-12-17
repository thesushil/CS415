import java.sql.*;
import java.util.Scanner;

public class ReportGenerator {

    public static void ListOwnerByAssessment() {
        int assmtId = requestAssessmentId();
        System.out.println("============================= Home Owner Details ====================================");
        printHomeOwnerDetails(assmtId);
        System.out.println("=====================================================================================");
    }

    private static int requestAssessmentId() {
        new Assessment().listAll();
        System.out.println("Enter an Assessment Id from above:");
        int id = new Scanner(System.in).nextInt();
        return id;
    }

    private static void printHomeOwnerDetails(int assmtId) {
        String sql = "select firstName, lastName, phoneNumber, email from Homeowner o " +
                "inner join property p on o.propertyId = p.propertyId " +
                "inner join assessment a on p.propertyId = a.propertyId where a.assmtId = ? ";

        try (Connection conn = Connect.getConnection()) {
            if (conn == null) return;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, assmtId);
            ResultSet result = statement.executeQuery();
            String displayFormat = "%-20s%-20s%-20s%-20s";
            System.out.println(String.format(displayFormat, "First Name", "Last Name", "Phone Number", "Email"));
            while (result.next()) {
                String firstName = result.getString("FirstName");
                String lastName = result.getString("LastName");
                String phoneNumber = result.getString("phoneNumber");
                String email = result.getString("email");
                System.out.println(String.format(displayFormat, firstName, lastName, phoneNumber, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
