import java.util.Scanner;

public class Menu {

    public static MenuChoice chooseFromMenu(MenuChoice currentChoice) {
        MenuChoice nextChoice = showMenuAndChoose(currentChoice);
        if (nextChoice == MenuChoice.RETURN_TO_MAIN_MENU)
            nextChoice = showMenuAndChoose(MenuChoice.NONE);
        return nextChoice;
    }

    private static MenuChoice showMenuAndChoose(MenuChoice currentChoice) {
        showMenu(currentChoice);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose from options above:");
        int menuNumber = scanner.nextInt();
        return FindChoice(currentChoice, menuNumber);
    }

    private static void showMenu(MenuChoice currentChoice){
        int menuNumber = 0;
        switch (currentChoice) {
            case DETAILS_FOR_ID:
                System.out.println(String.format("%d: Return to Main Menu", 0));
                System.out.println(String.format("%d: Update Record", ++menuNumber));
                System.out.println(String.format("%d: Add New Record", ++menuNumber));
                System.out.println(String.format("%d: Delete Record", ++menuNumber));
                break;
            case List_Home_OWNERS:
            case List_Home_PROPERTIES:
            case List_REGISTERED_VEHICLES:
            case List_PROPERTY_ASSESSMENTS:
                System.out.println(String.format("%d: Return to Main Menu", 0));
                System.out.println(String.format("%d: Update Record", ++menuNumber));
                System.out.println(String.format("%d: Add New Record", ++menuNumber));
                System.out.println(String.format("%d: Delete Record", ++menuNumber));
                break;
//                System.out.println(String.format("%d: Return to Main Menu", 0));
//                System.out.println(String.format("%d: Enter ID for Details", ++menuNumber));
//                break;
            case NONE:
                System.out.println(String.format("%d: List Home Owners", ++menuNumber));
                System.out.println(String.format("%d: List Home Properties", ++menuNumber));
                System.out.println(String.format("%d: List Registered Vehicles", ++menuNumber));
                System.out.println(String.format("%d: List Property Assessments", ++menuNumber));
        }
    }

    private static MenuChoice FindChoice(MenuChoice currentChoice, int menuNumber) {
        switch (currentChoice) {
            case DETAILS_FOR_ID:
                switch (menuNumber){
                    case 0:
                        return MenuChoice.RETURN_TO_MAIN_MENU;
                    case 1:
                        return MenuChoice.UPDATE_RECORD;
                    case 2:
                        return MenuChoice.ADD_NEW_RECORD;
                    case 3:
                        return MenuChoice.DELETE_RECORD;
                    default:
                        return MenuChoice.NONE;
                }
            case List_Home_OWNERS:
            case List_Home_PROPERTIES:
            case List_REGISTERED_VEHICLES:
            case List_PROPERTY_ASSESSMENTS:
                switch (menuNumber){
                    case 0:
                        return MenuChoice.RETURN_TO_MAIN_MENU;
                    case 1:
                        return MenuChoice.DETAILS_FOR_ID;
                    default:
                        return MenuChoice.NONE;
                }
            case NONE:
                switch (menuNumber){
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
        return MenuChoice.NONE;
    }
}
