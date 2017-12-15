
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        //authenticateUser();

        MenuChoice menuChoice = MenuChoice.NONE;
        while (true) {
            menuChoice = Menu.chooseFromMenu(menuChoice);
            switch (menuChoice) {
                case List_Home_OWNERS:
                    ListHomeOwners();
                    break;
                default:
                    break;
            }
        }
    }

    private static void ListHomeOwners() {
    }

    private static void authenticateUser() {
        Scanner scanner = new Scanner(System.in);

        String firstName = null;
        while(firstName == null) {
            System.out.print("Enter your username:");
            String username = scanner.nextLine();
            System.out.print("Enter your password:");
            String password = scanner.nextLine();
            firstName = Connect.validateCredential(username, password);
            if (firstName != null) {
                System.out.println(String.format("Welcome %s !!!", firstName));
            }
        }
    }
}

