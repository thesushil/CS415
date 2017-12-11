import java.util.Scanner;

public class Menu {

    public static MenuChoice chooseFromMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose from options above:");
        scanner.next();
        return MenuChoice.VIEW_OWNERS;
    }
}
