import java.util.Scanner;
import java.util.regex.Pattern;

public class emailInputDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean ask = true;
        String email = "";

        while (ask) {
            if (0 == (emailValidator(email = getEmail(scan)))) break;
            if (ask) System.out.println("ERROR: INVALID EMAIL FORMAT OR UNSUPPORTED EMAIL");
        }

        System.out.println(String.format("Your email is: %s", email));
    }

    public static int emailValidator(String email) {
        return Pattern.compile("[a-zA-z0-9]+@((gmail)|(outlook)|(yahoo)|(icloud)|(protonmail)|(zoho)|(aol)|(gmx)).com")
                .matcher(email)
                .matches() ? 0 : 1;
    }

    public static String getEmail(Scanner scan) {
        System.out.println("Enter your email: ");
        System.out.println("EX: example@gmail.com, example@outlook.com, etc.");
        String email = scan.nextLine();
        return email;
    }
}
