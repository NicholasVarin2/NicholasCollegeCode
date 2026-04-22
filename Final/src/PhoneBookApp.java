
import java.util.Scanner;

/**
 *
 *
 * @author Varin
 * @version 1.0
 * @since March 15, 2025
 */
public class PhoneBookApp {

    private static int userChoice = 0;
    private static final int EXIT = 0;
    Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {

        runApplication();

    }

    /**
     * runApplication: This method displays the menu, get the user choice and
     * execute a valid command. If user exits, it clears the console
     *
     * @author FHB
     * @version 1.0
     * @param none
     * @return void
     * @since March 15, 2025
     */
    public static void runApplication() {
        do {
            clearConsoleMac();
            displayMenu();
            getUserChoice();
            executeCommand();
        } while (userChoice != EXIT);
        clearConsoleMac();
    } // End of method runApplication

    /**
     * displayMenu: This method display a simple menu, it gets the imput from
     * the user, and passess the user imput to the method that executes the
     * selected choice.
     *
     * @author FHB
     * @version 1.0
     * @param none
     * @return void
     * @since March 15, 2025
     */
    public static void displayMenu() {
        System.out.println(" ________________________________________");
        System.out.println("|           Phone Book Menu:             |");
        System.out.println("|   SELECT ONE OF THE FOLLOWING CHOICES  |");
        System.out.println("|________________________________________|");
        System.out.println("|          1. Add New Contact:           |");
        System.out.println("|          2. Update Contact             |");
        System.out.println("|          3. Search by Name             |");
        System.out.println("|          4. Search by Number           |");
        System.out.println("|          5. Search by City             |");
        System.out.println("|          6. Display All Contacts       |");
        System.out.println("|          7. Delete Contact             |");
        System.out.println("|          8. Delete all Contacts        |");
        System.out.println("|          0. Exit Program               |");
        System.out.println("|________________________________________|");
        System.out.print("   Enter your choice: ");

    }

    /**
     * getUserChoice: This method gets the choice from the user as an integer
     * and stores it in the userChoice
     *
     * @author FHB
     * @version 1.0
     * @param none
     * @return void
     * @since March 15, 2025
     */
    public static void getUserChoice() {
        // Setting a Scanner object to get the user choice.
        Scanner userInput = new Scanner(System.in);
        userChoice = userInput.nextInt();
        userInput.nextLine();
        System.out.println();
    } // End of method getUserChoice

    /**
     * executeCommand: This method executes the command that matches the
     * parameter.
     *
     * @author FHB
     * @version 1.0
     * @param none
     * @return void
     * @since March 15, 2025
     */
    public static void executeCommand() {

        PhoneBook phoneBook = new PhoneBook("contacts.txt");

        switch (userChoice) {
            case 1:
                phoneBook.addContact();
                pressEnterToContinue();
                break;
            case 2:
                phoneBook.updateContact();
                pressEnterToContinue();
                break;
            case 3:
                phoneBook.searchByName();
                pressEnterToContinue();
                break;
            case 4:
                phoneBook.searchByNumber();
                pressEnterToContinue();
                break;
            case 5:
                phoneBook.searchByCity();
                pressEnterToContinue();
                break;
            case 6:
                phoneBook.displayAllContacts();
                pressEnterToContinue();
                break;
            case 7:
                phoneBook.deleteContact();
                pressEnterToContinue();
                break;
            case 8:
                phoneBook.deleteAllContacts();
                pressEnterToContinue();
                break;
            case 0:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid Choice, Try Again");
                pressEnterToContinue();
                break;

        }
    }

    /**
     * clearConsoleMac: This method clears the console in a apple mac.
     *
     * @author FHB
     * @version 1.0
     * @param none
     * @return void
     * @since March 15, 2025
     */
    public static void clearConsoleMac() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    } // End of method clearConsoleMac

    /**
     * pressEnterToContinue: This method waits for the user to press the ENTER
     * key to continue the program execution. It uses the Scanner class to
     * capture the ENTER key from the user.
     *
     * @author FHB
     * @version 1.0
     * @param none
     * @return void
     * @since March 15, 2025
     */
    public static void pressEnterToContinue() {
        //Use Scanner to get user input
        Scanner userInput = new Scanner(System.in);
        System.out.println();
        System.out.println("Press \"ENTER\" to continue...");
        userInput.nextLine(); //Capture input without using it.
    } // End of method pressEnterToContinue

} // end class PhoneBookApp