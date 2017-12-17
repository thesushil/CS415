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
    }

    private static MenuChoice FindChoice(int menuNumber) {
        switch (menuNumber) {
            case 1:
                return MenuChoice.List_Home_OWNERS;
            case 2:
                return MenuChoice.List_Home_PROPERTIES;
            case 3:
                return MenuChoice.List_REGISTERED_VEHICLES;
            case 4:
                return MenuChoice.List_PROPERTY_ASSESSMENTS;
            default:
                return MenuChoice.NONE;
        }
    }
}
