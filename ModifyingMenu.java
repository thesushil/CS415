import java.util.Scanner;

public class ModifyingMenu {

    public static MenuChoice chooseFromMenu() {
        showMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose from options above:");
        int menuNumber = scanner.nextInt();
        return FindChoice(menuNumber);
    }

    private static void showMenu(){
        int menuNumber = 0;
        System.out.println(String.format("%d: Return to Main Menu", 0));
        System.out.println(String.format("%d: Update Record", ++menuNumber));
        System.out.println(String.format("%d: Add New Record", ++menuNumber));
        System.out.println(String.format("%d: Delete Record", ++menuNumber));
    }

    private static MenuChoice FindChoice(int menuNumber) {
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
    }
}
