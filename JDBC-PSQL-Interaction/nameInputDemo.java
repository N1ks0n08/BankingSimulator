import java.util.regex.Pattern;
import java.util.Scanner;

public class nameInputDemo {
    public static void main(String[] args) {
        String userName = askUserName();
        System.out.println(String.format("The name of the user is: %s", userName));
    }

    public static String askUserName() {
        Scanner scan = new Scanner(System.in);
        String name = "";
        boolean ask = true;
        while (ask) {
            name = getUserInput(scan);
            ask = nameValidifier(name) == 0 ? false : true;
            if (ask) System.out.println("Please enter a valid name!");
        }
        scan.close();
        return name;
    }

    public static int nameValidifier(String name) {
        return Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+")
                        .matcher(name)
                        .matches() ? 0 : 1;
    }

    public static String getUserInput(Scanner scan) {
        System.out.println("Please enter your first and last name: ");
        String name = scan.nextLine();
        return name;
    }
}
