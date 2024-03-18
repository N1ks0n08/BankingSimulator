import java.util.Scanner;
import java.util.regex.Pattern;

public class ssnInputDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean ask = true;
        String ssn = "";

        while (ask) {
            ask = (0 == ssnValidator(ssn = getUserInput(scan))) ? false : true;
            if (ask) System.out.println("ERROR: INVALID SSN!");
        }
        System.out.println(String.format("Your SSN is: %s", ssn));
    }

    public static int ssnValidator(String ssn) {
        return Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}")
                .matcher(ssn)
                .matches() ? 0 : 1;
    }

    public static String getUserInput(Scanner scan) {
        System.out.println("Please enter your SSN (EX: 334-731-3285): ");
        String ssn = scan.nextLine();
        return ssn;
    }
}