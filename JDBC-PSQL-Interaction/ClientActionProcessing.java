import java.sql.*;
import java.util.Scanner;

public class ClientActionProcessing {
    public static final String databaseURL = "jdbc:postgresql://localhost:5432/bankingsim";

    public static void main(String[] args) {
        String typeOfCommand;
        int result;
        Scanner scan = new Scanner(System.in);
        Connection databaseConnection = establishConnection();

        if (databaseConnection == null) {
            System.err.print("ERROR: Failed to establish database connection :(");;
            System.exit(1);
        }

        System.out.println("Successfully established database connection!");
        System.out.println("Please enter the type of command to execute...:");
        System.out.println("Create, Read, Update, Delete");
        typeOfCommand = scan.nextLine();
        result = commandProcessor(typeOfCommand, databaseConnection);
        scan.close();

        switch (result) {
            case 0:
                System.exit(0);
            case 2:
                System.err.print("ERROR: Command not supported yet!");
                System.exit(1);
            default:
                System.err.println("ERROR: Error occurred while executing command!");
                System.out.print(result);
                System.exit(1);
        }
    }

    // establish the connection to database
    public static Connection establishConnection() {
        Connection databaseConnection = null;
        try {
            databaseConnection = DriverManager.getConnection(databaseURL, "n1ks0nic", "");
            return databaseConnection;
        } catch (Exception e) {
            return databaseConnection;
        }
        
    }

    // determine type of command
    public static int commandProcessor (String typeOfCommand, Connection connection) {
        switch (typeOfCommand.toUpperCase()) {
            case "CREATE":
                return createUser(connection);
            case "READ":
                return readUserInfo(connection);
            case "UPDATE":
                return updateUserInfo(connection);
            case "DELETE":
                return removeUserInfo(connection);
            default:
                return 1;
        }
    }

    // method to create data
    public static int createUser (Connection connection) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter first name of the user to be added: ");
            String firstName = scan.nextLine();
            System.out.println("Enter last name of the user to be added: ");
            String lastName = scan.nextLine();

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO bank_users (name) VALUES (?)");
            pstmt.setString(1, String.format("%s %s", firstName, lastName));
            pstmt.executeUpdate();
            System.out.println("User added successfully!");
            scan.close();

            connection.close();
            pstmt.close();
            return 0;
        } catch (SQLException sqlE) {
            return Integer.parseInt(sqlE.getSQLState());
        }
    }

    // method to remove data
    public static int removeUserInfo (Connection connection) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter first name of the user to be removed: ");
            String firstName = scan.nextLine();
            System.out.println("Enter last name of the user to be removed: ");
            String lastName = scan.nextLine();
            scan.close();

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM bank_users WHERE name=?");
            pstmt.setString(1, String.format("%s %s", firstName, lastName));
            pstmt.executeUpdate();
            System.out.println("User removed successfully!");
            connection.close();
            pstmt.close();
            return 0;
        } catch (SQLException sqlE) {
            return Integer.parseInt(sqlE.getSQLState());
        }
    }

    // method to change existing data
    public static int updateUserInfo(Connection connection) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the first name of the user to be updated: ");
            String prevFirstName = scan.nextLine();
            System.out.println("Enter the last name of the user to be updated: ");
            String prevLastName = scan.nextLine();
            System.out.println("Please enter the new first name to be placed: ");
            String newFirstName = scan.nextLine();
            System.out.println("Please enter the new last name to be placed: ");
            String newLastName = scan.nextLine();


            PreparedStatement pstmt = connection.prepareStatement("UPDATE bank_users SET name=? WHERE name=?");
            pstmt.setString(1, String.format("%s %s", newFirstName, newLastName));
            pstmt.setString(2, String.format("%s %s", prevFirstName, prevLastName));           
            pstmt.executeUpdate();
            System.out.println("User updated successfully!");
            scan.close();
            connection.close();
            return 0;
        } catch (SQLException sqlE) {
            return Integer.parseInt(sqlE.getSQLState());
        }
    }

    // method to return metadata about data
    public static int readUserInfo(Connection connection) {
        try {
            Scanner scan = new Scanner(System.in);
            String firstName;
            String lastName;
            System.out.println("Please enter the first name of the user to retrieve data about: ");
            firstName = scan.nextLine();
            System.out.println("Please enter the last name of the user to retrieve data about: ");
            lastName = scan.nextLine();

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM bank_users WHERE name=?");
            pstmt.setString(1, String.format("%s %s", firstName, lastName));
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            
            int user_id = rs.getInt("id");
            String user_name = rs.getString("name");
            System.out.println(String.format("ID: %d%nNAME: %s", user_id, user_name));
            
            rs.close();
            connection.close();
            scan.close();
            return 0;
        } catch (SQLException sqlE) {
            return Integer.parseInt(sqlE.getSQLState());
        }
    }
}
