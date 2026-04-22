
/**
 * Represents a contact with basic details such as first name, last name, gender, phone number, and city.
 *
 * This class provides methods to access and modify the contact details.
 *
 * @param void
 * @return void
 * @author Varin
 * @version 1.0
 * @since March 15, 2025
 */
public class Contact {

    private String firstName;
    private String lastName;
    private char gender;
    private String phoneNumber;
    private String city;

    public Contact(String firstName, String lastName, char gender, String phoneNumber, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns a string representation of the Contact object in the format:
     * firstName, lastName, gender, phoneNumber, city
     *
     * @return a string representation of the Contact object
     */
    @Override
    public String toString() {
        return firstName + ", " + lastName + ", " + gender + ", " + phoneNumber + ", " + city;
    }

}