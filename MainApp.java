
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        //authenticateUser();

        MenuChoice menuChoice = MenuChoice.NONE;
        while (true) {
            menuChoice = Menu.chooseFromMenu(menuChoice);
            int id;
            switch (menuChoice) {
                case List_Home_OWNERS:
                    HomeOwner.listAll();
                    id = chooseAnId();
                    HomeOwner.displayDetails(id);
                    break;
                case List_Home_PROPERTIES:
                    HomeProperty.listAll();
                    id = chooseAnId();
                    HomeProperty.displayDetails(id);
                    break;
                case List_REGISTERED_VEHICLES:
                    Vehicle.listAll();
                    id = chooseAnId();
                    Vehicle.displayDetails(id);
                    break;
                case List_PROPERTY_ASSESSMENTS:
                    Assessment.listAll();
                    id = chooseAnId();
                    Assessment.displayDetails(id);
                    break;
                case UPDATE_RECORD:
                    break;
                case ADD_NEW_RECORD:
                    break;
                case DELETE_RECORD:
                    break;
                default:
                    break;
            }
        }
    }

    private static int chooseAnId(){
        System.out.println("To get details enter an ID from above:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
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

