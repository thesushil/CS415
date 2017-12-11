
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AuthenticateUser();

        while (true) {
            MenuChoice menuChoice = Menu.chooseFromMenu();
            switch (menuChoice) {
                case VIEW_OWNERS:
                    // do something
                    break;
                default:
                    break;
            }
        }
    }

    private static void AuthenticateUser() {
        Scanner scanner = new Scanner(System.in);

        String firstName = null;
        while(firstName == null) {
            System.out.print("Enter your username:");
            String username = scanner.nextLine();
            System.out.print("Enter your password:");
            String password = scanner.nextLine();
            //System.out.println(username + " " + password);
            firstName = Connect.validateCredential(username, password);
            if (firstName == null) {
                System.out.println("Invalid username or password. Please try again.");}
            else {
                System.out.println(String.format("Welcome %s !!!", firstName));
            }
        }
    }
}

