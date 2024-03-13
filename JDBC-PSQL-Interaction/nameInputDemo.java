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
            if (ask) {
                System.out.println("Please enter a valid name!");
                System.out.println("EX: Darth Vader");
                System.out.println("EX: Obi Wan Kenobi");
            }
        }
        scan.close();
        return name;
    }

    /* Depracated; the fuction nameValidifier(String name) already checks for optional middle name
    public static boolean middleNameCheck(Scanner scan) {
        boolean ask = true;
        boolean hasMiddleName = false;
        while (ask) {
            System.out.println("Do you have a middle name? (Y/N)");
            switch (scan.nextLine().toUpperCase()) {
                case "Y":
                    ask = false;
                    hasMiddleName = true;
                    break;
                case "N":
                    ask = false;
                    break;
                default:
                    System.out.println("Please enter Y or N!");
            }
        }
        return hasMiddleName;
    }
    */

    public static int nameValidifier(String name) {
        return Pattern.compile("[A-Z][a-z]+( [A-Z][a-z]+)?( [A-Z][a-z]+)")
                        .matcher(name)
                        .matches() ? 0 : 1;
    }
    
    /* Deprecated; a more optimal solution (method above) is utilized instead
    public static int firstAndLastNameValidifier(String name) {
        return Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+")
                        .matcher(name)
                        .matches() ? 0 : 1;
    }

    public static int firstMiddleAndLastNameValidator(String name) {
        return Pattern.compile("[A-Z][a-z]+ [A-Z][a-z]+ [A-Z][a-z]+")
                        .matcher(name)
                        .matches() ? 0 : 1;
    }
    */

    public static String getUserInput(Scanner scan) {
        System.out.println("Please enter your full name (including middle name if applicable): ");
        String name = scan.nextLine();
        return name;
    }
}
