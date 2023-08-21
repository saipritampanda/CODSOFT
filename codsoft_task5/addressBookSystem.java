import java.io.*;
import java.util.*;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPhone: " + phoneNumber + "\nEmail: " + emailAddress + "\n";
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public List<Contact> searchContacts(String keyword) {
        List<Contact> results = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(contact);
            }
        }
        return results;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmailAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class addressBookSystem {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.loadFromFile("contacts.txt");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Address Book System");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contacts");
            System.out.println("3. Display All Contacts");
            System.out.println("4. Save Contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added.");
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Enter search keyword: ");
                    String keyword = scanner.nextLine();
                    List<Contact> searchResults = addressBook.searchContacts(keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No matching contacts found.");
                    } else {
                        System.out.println("Matching contacts:");
                        for (Contact contact : searchResults) {
                            System.out.println(contact);
                        }
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println("All contacts:");
                    addressBook.displayAllContacts();
                    System.out.println();
                    break;

                case 4:
                    addressBook.saveToFile("contacts.txt");
                    System.out.println("Contacts saved to file.");
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.out.println();
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    System.out.println();
            }
        } while (choice != 5);

        scanner.close();
    }
}
