import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        //authenticateUser();

        while (true) {
            processMenu();
        }
    }

    private static void processMenu() {
        MenuChoice menuChoice = ViewingMenu.chooseFromMenu();
        int id;
        Entity entity = null;
        switch (menuChoice) {
            case LIST_HOME_OWNERS:
                entity = new HomeOwner();
                break;
            case LIST_HOME_PROPERTIES:
                entity = new HomeProperty();
                break;
            case LIST_REGISTERED_VEHICLES:
                entity = new Vehicle();
                break;
            case LIST_PROPERTY_ASSESSMENTS:
                entity = new Assessment();
                break;
            case LIST_OWNERS_BY_ASSESSMENT:
                ReportGenerator.ListOwnerByAssessment();
                break;
            default:
                entity = new HomeOwner();
                break;
        }

        if (entity != null) {
            entity.listAll();
            id = chooseAnId();
            entity.displayDetails(id);
            MenuChoice nextChoice = ModifyingMenu.chooseFromMenu();

            switch (nextChoice) {
                case UPDATE_RECORD:
                    entity.update(id);
                    break;
                case ADD_NEW_RECORD:
                    entity.addNew();
                    break;
                case DELETE_RECORD:
                    entity.delete(id);
                    break;
            }
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

