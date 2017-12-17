import java.util.Scanner;

public class ViewingMenu {

    public static MenuChoice chooseFromMenu() {
        showMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose from options above:");
        int menuNumber = scanner.nextInt();
        return FindChoice(menuNumber);
    }

    private static void showMenu() {
        int menuNumber = 0;
        System.out.println(String.format("%d: List Home Owners", ++menuNumber));
        System.out.println(String.format("%d: List Home Properties", ++menuNumber));
        System.out.println(String.format("%d: List Registered Vehicles", ++menuNumber));
        System.out.println(String.format("%d: List Property Assessments", ++menuNumber));
        System.out.println(String.format("%d: Report of Owners By Assessment", ++menuNumber));
    }

    private static MenuChoice FindChoice(int menuNumber) {
        switch (menuNumber) {
            case 1:
                return MenuChoice.LIST_HOME_OWNERS;
            case 2:
                return MenuChoice.LIST_HOME_PROPERTIES;
            case 3:
                return MenuChoice.LIST_REGISTERED_VEHICLES;
            case 4:
                return MenuChoice.LIST_PROPERTY_ASSESSMENTS;
            case 5:
                return MenuChoice.LIST_OWNERS_BY_ASSESSMENT;
            default:
                return MenuChoice.NONE;
        }
    }
}
