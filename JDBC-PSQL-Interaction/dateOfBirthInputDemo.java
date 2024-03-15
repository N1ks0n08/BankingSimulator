import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
    The birth year is limited to at or more than 122 years
    previous from the current year.
    This has been selected due to the oldest known human
    being to have lived ~122 years of age throughout history.

    initial fix to the scan.nextInt() infnite loop problem was:
    scan = new Scanner(System.in);

    New solution is:
    scan.nextLine()
    This consumes the previous mismatched data and allows for new data
    to be taken in, preventing the infinite loop occurring from
    the Scanner object being unable to take in new data.
*/

public class dateOfBirthInputDemo {
    public static void main(String[] args) {
        LocalDate dateOfBirth = LocalDate.now();
        boolean ask = true;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
        Scanner scan = new Scanner(System.in);
        int day = day(scan);
        int month = month(scan);
        int year = year(scan);
        
        while (ask) {
            try {
                dateOfBirth = getDateOfBirth(day, month, year);
                ask = false;
            } catch (DateTimeException dte) {
                System.out.println("ERROR: INVALID DATE!");
            } catch (Exception e) {
                System.out.println("ERROR: UNKOWN ERROR OCCURRED!");
            }
        }
        System.out.println("The date of birth (in Month/Day/Year format) is: ");
        System.out.println(dateOfBirth.format(dateFormatter));
        scan.close();
    }

    public static LocalDate getDateOfBirth(int day, int month, int year) {
        return LocalDate.of(year, month, day);
    }

    public static int month(Scanner scan) {
        int month = 0;
        boolean ask = true;
        while (ask) {
            try {
                System.out.println("Please enter the number of the month you were born: ");
                System.out.println("EX: 01 (For January), 06 (For June), etc.");
                month = scan.nextInt();
                ask = false;
            } catch (InputMismatchException ime) {
                System.out.println("ERROR: INVALID INPUT!");
                scan.nextLine();
            }
        }
        return month;
    }

    public static int day (Scanner scan) {
        int day = 0;
        boolean ask = true;
        while (ask) {
            try {
                System.out.println("Please enter the number of the day you were born: ");
                System.out.println("EX: 02, 17, etc.");
                day = scan.nextInt();
                ask = false;
                break;
            } catch (InputMismatchException ime) {
                System.out.println("ERROR: INVALID INPUT!");
                scan.nextLine();
            }
        }
        return day;
    }

    public static int year (Scanner scan) {
        int year = 0;
        boolean ask = true;
        while (ask) {
            try {
                System.out.println("Please enter the year you were born: ");
                System.out.println("EX: 1987");
                if ((year = scan.nextInt()) >= LocalDate.now().getYear() - 122) {
                    ask = false;
                    break;
                } else if (year < LocalDate.now().getYear() - 122) {
                    System.out.println("ERROR: AGE TOO OLD!");
                } else {
                    System.out.println("INVALID BIRTH YEAR");
                }
            } catch (InputMismatchException ime) {
                System.out.println("ERROR: INVALID INPUT!");
                scan.nextLine();
            }
        }
        return year;
    }
}
