
import java.io.*;
import java.util.*;

/**
 * A simple phonebook that stores contacts in a file. It allows users to add,
 * search, and delete contacts.
 *
 * @author Varin
 * @version 1.0
 * @since March 15, 2025
 */
public class PhoneBook {

    private List<Contact> contacts;
    private String filename;

    /**
     * Constructor to initialize the phonebook
     *
     * @param filename The name of the file to read the contacts from
     */
    public PhoneBook(String filename) {
        this.contacts = new ArrayList<>();
        this.filename = filename;
        loadContacts();
    }

    /**
     * Constructor to initialize the phonebook
     *
     * @param filename The name of the file to read the contacts from
     * @since March 15, 2025
     */
    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",\\s*");
                if (values.length == 5) { // Ensure the line has 5 parts
                    Contact contact = new Contact(values[0], values[1], values[2].charAt(0), values[3], values[4]);
                    contacts.add(contact);
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

    /**
     * Loads the contacts from a file and stores them in the contacts list.
     *
     * @author Varin
     * @version 1.0
     * @param void
     * @return void
     * @since March 15, 2025
     */
    public void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                writer.write(contact.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    /**
     * This method adds a new contact to the phonebook. User is prompted to
     * enter the first name, last name, gender, phone number and city The
     * contact is added to the contacts list and saved to the phonebook file
     *
     * @author Varin
     * @version 1.0
     * @param void
     * @return void
     * @since March 15, 2025
     */
    public void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();
        char gender;
        while (true) {
            System.out.print("Enter gender (M/F): ");
            gender = Character.toUpperCase(scanner.next().charAt(0));
            if (gender == 'M' || gender == 'F') {
                break;
            }
            System.out.println("Invalid input. Please enter M or F.");
        }
        String phoneNumber;
        while (true) {
            System.out.print("Enter new phone number (10 digits): ");
            phoneNumber = scanner.next().trim();
            if (phoneNumber.matches("\\d{10}")) {
                break;
            }
            System.out.println("Invalid phone number. It must be exactly 10 digits.");
        }
        scanner.nextLine(); // consume leftover newline
        System.out.print("Enter city: ");
        String city = scanner.nextLine().trim();
        // print out the new contact with all the information
        Contact contact = new Contact(firstName, lastName, gender, phoneNumber, city);
        contacts.add(contact);
        saveContacts();
    }

    /**
     * Updates a contact based on the phone number. User replaces that user with
     * a new first and last name, gender, phone number and city
     *
     * @author Varin
     * @version 1.0
     * @param void
     * @return void
     * @since March 15, 2025
     */
    public void updateContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone number of contact to update: ");
        String phoneNumber = scanner.next();

        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                System.out.print("Enter new first name: ");
                String firstName = scanner.next();
                System.out.print("Enter new last name: ");
                String lastName = scanner.next();

                char gender;
                while (true) {
                    System.out.print("Enter new gender (M/F): ");
                    gender = Character.toUpperCase(scanner.next().charAt(0));
                    if (gender == 'M' || gender == 'F') {
                        break;
                    }
                    System.out.println("Invalid input. Please enter M or F.");
                }
                String newPhoneNumber;
                while (true) {
                    System.out.print("Enter new phone number (10 digits): ");
                    newPhoneNumber = scanner.next().trim();
                    if (newPhoneNumber.matches("\\d{10}")) {
                        break;
                    }
                    System.out.println("Invalid phone number. It must be exactly 10 digits.");
                }
                scanner.nextLine();
                System.out.print("Enter new city: ");
                String city = scanner.nextLine();

                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setGender(gender);
                contact.setPhoneNumber(newPhoneNumber);
                contact.setCity(city);

                saveContacts();
                System.out.println("Contact updated successfully.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    /**
     * Searches for contacts by name and prints the matching contacts.
     *
     * This method prompts the user to enter a last name and searches the
     * contacts list for any contacts with a matching last name. If a match is
     * found, the contact details are printed, otherwise a message indicating no
     * match is displayed.
     *
     * @author Varin
     * @version 1.0
     * @param void
     * @return void
     * @since March 15, 2025
     */
    public void searchByName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter first or last name to search: ");
            String name = scanner.nextLine();
            boolean found = false;

            contacts.sort(Comparator.comparing(Contact::getFirstName));
            for (Contact contact : contacts) {
                if (contact.getLastName().equalsIgnoreCase(name) || contact.getFirstName().equalsIgnoreCase(name)) {
                    System.out.println(contact.toString());
                    found = true;
                }

            }

            if (found) {
                System.out.println("Contact(s) found. Returning to menu...");
                return;  // Exit the method and return to the menu
            } else {
                System.out.println("There is no record with that name. Try again.");
            }
        }
    }

    /**
     * Searches for contacts by city and prints the matching contacts.
     *
     * This method prompts the user to enter a city and searches the contacts
     * list for any contacts with a matching city. If a match is found, the
     * contact details are printed, otherwise a message indicating no match is
     * displayed.
     *
     * @author Varin
     * @version 1.0
     * @param void
     * @return void
     * @since March 15, 2025
     */
    public void searchByNumber() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter phone number to search: ");
            String phoneNumber = scanner.next();
            boolean found = false;

            contacts.sort(Comparator.comparing(Contact::getFirstName));
            for (Contact contact : contacts) {
                if (contact.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                    System.out.println(contact.toString());
                    found = true;
                }
            }

            if (found) {
                System.out.println("Contact(s) found. Returning to menu...");
                return;  // Exit the method and return to the menu
            } else {
                System.out.println("There is no record with that number. Try again.");
            }

        }

    }

    /**
     * Searches for contacts by city and prints the matching contacts.
     *
     * This method prompts the user to enter a city and searches the contacts
     * list for any contacts with a matching city. If a match is found, the
     * contact details are printed, otherwise a message indicating no match is
     * displayed.
     *
     * @author Varin
     * @version 1.0
     * @param void
     * @return void
     * @since March 15, 2025
     */
    public void searchByCity() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter city to search: ");
            String city = scanner.nextLine();
            boolean found = false;

            contacts.sort(Comparator.comparing(Contact::getFirstName));
            for (Contact contact : contacts) {
                if (contact.getCity().equalsIgnoreCase(city)) {
                    System.out.println(contact.toString());
                    found = true;
                }
            }

            if (found) {
                System.out.println("Contact(s) found. Returning to menu...");
                return;  // Exit the method and return to the menu
            } else {
                System.out.println("There is no record with that city. Try again.");
            }
        }
    }

    /**
     * Displays all contacts in the phonebook.
     *
     * This method displays all contacts in the phonebook in a sorted order by
     * first name. If the phonebook is empty, it displays a message indicating
     * that the phonebook is empty.
     *
     * @author Varin
     * @version 1.0
     * @param void
     * @return void
     * @since March 15, 2025
     */
    public void displayAllContacts() {

        if (contacts.isEmpty()) {
            System.out.println("There are no contacts in the phonebook.");
            return;
        }
        contacts.sort(Comparator.comparing(contact -> contact.getFirstName().trim().toLowerCase()));
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }

    }

    /**
     * Deletes a contact based on the phone number.
     *
     * This method prompts the user to enter a phone number and deletes the
     * contact with the matching phone number from the phonebook. If the contact
     * is not found, it displays a message indicating that the contact is not
     * found.
     *
     * @author Varin
     * @version 1.0
     * @param void
     * @return void
     * @since March 15, 2025
     */
    public void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone number of contact to delete: ");
        String phoneNumber = scanner.next();

        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                contacts.remove(contact);
                System.out.println("Deleted Contact");
                saveContacts();
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    /**
     * Deletes all contacts from the phonebook.
     *
     * This method deletes all contacts from the phonebook. It does not prompt
     * the user for any input. After deleting all contacts, it saves the empty
     * phonebook to a file.
     *
     * @author Varin
     * @version 1.0
     * @param void
     * @return void
     * @since March 15, 2025
     */
    public void deleteAllContacts() {
        System.out.println("Deleting all contacts");
        contacts.clear();
        saveContacts();
    }
}