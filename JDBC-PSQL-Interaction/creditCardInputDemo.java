import java.util.Scanner;
import java.util.regex.Pattern;

public class creditCardInputDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean ask = true;
        String creditCardNumber = "";

        while (ask) {
            if (0 == creditCardNumberValidator(creditCardNumber = getCreditCardNumber(scan))) break;
            System.out.println("ERROR: INVALID CREDIT CARD NUMBER!");
        }

        System.out.println(String.format("Your credit card number is: %s", creditCardNumber));
    }

    public static int creditCardNumberValidator(String creditCardNumber) {
        return Pattern.compile("[0-9]{16}")
                .matcher(creditCardNumber)
                .matches() ? 0 : 1;
    }
    
    public static String getCreditCardNumber(Scanner scan) {
        System.out.println("Please enter your credit card number: ");
        System.out.println("EX: 9307298522042466");
        String creditCardNumber = scan.nextLine();
        return creditCardNumber;
    }
}
