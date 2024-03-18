import java.util.Scanner;
import java.util.regex.Pattern;

public class phoneNumberInputDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean ask = true;
        String phoneNumber = "";
        while (ask) {
            if (0 == phoneNumberValidator(phoneNumber = getPhoneNumber(scan))) break;
            System.out.println("ERROR: INVALID PHONE NUMBER!");
        }

        System.out.println(String.format("The phone number is: %s", phoneNumber));
    }

    public static int phoneNumberValidator(String phoneNumber) {
        return Pattern.compile("((?!911)(?!800)\\d{3})-[0-9]{3}-[0-9]{4}")
                .matcher(phoneNumber)
                .matches() ? 0 : 1;
    }

    public static String getPhoneNumber(Scanner scan) {
        System.out.println("Please enter your phone number (NO PARENTHESES OR COUNTRY CODE; INCLUDE HYPHENS!): ");
        System.out.println("EX: 682-311-2894");

        String phoneNumber = scan.nextLine();
        return phoneNumber;
    }
}
