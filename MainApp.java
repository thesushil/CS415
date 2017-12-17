import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        authenticateUser();

        while (true) {
            processMenu();
        }
    }

    private static void processMenu() {
        MenuChoice menuChoice = ViewingMenu.chooseFromMenu();
        int id;
        Entity entity;
        switch (menuChoice) {
            case List_Home_OWNERS:
                entity = new HomeOwner();
                break;
            case List_Home_PROPERTIES:
                entity = new HomeProperty();
                break;
            case List_REGISTERED_VEHICLES:
                entity = new Vehicle();
                break;
            case List_PROPERTY_ASSESSMENTS:
                entity = new Assessment();
                break;
            default:
                entity = new HomeOwner();
                break;
        }

        entity.listAll();
        id = chooseAnId();
        entity.displayDetails(id);
        MenuChoice nextChoice = ModifyingMenu.chooseFromMenu();

        switch (nextChoice){
            case UPDATE_RECORD:
                entity.update();
                break;
            case ADD_NEW_RECORD:
                entity.addNew();
                break;
            case DELETE_RECORD:
                entity.delete();
                break;
        }
    }

    private static int chooseAnId() {
        System.out.print("Enter an ID from above for details:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        String firstName = null;
        while (firstName == null) {
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

