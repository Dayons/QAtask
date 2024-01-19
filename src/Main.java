import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();

        while (true) {
            contactManager.showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    contactManager.displayContacts();
                    break;
                case 2:
                    System.out.print("Введите имя: ");
                    String firstName = scanner.next();
                    System.out.print("Введите фамилию: ");
                    String lastName = scanner.next();
                    System.out.print("Введите номер телефона: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Введите электронную почту: ");
                    String email = scanner.next();

                    Contact newContact = new Contact(firstName, lastName, phoneNumber, email);
                    contactManager.addContact(newContact);
                    break;

                case 3:
                    System.out.println("Введите индекс контакта");
                    int index = scanner.nextInt() - 1;
                    contactManager.updateContact(index);
                    break;
                case 4:
                    System.out.print("Введите имя контакта для удаления: ");
                    String name = scanner.next();
                    contactManager.deleteContact(name);
                    break;
                case 5:
                    contactManager.saveContactsToFile("contacts");
                    break;
                case 6:
                    contactManager.loadContactsFromFile("contacts");
                    break;
                case 7:
                    System.out.println("Введите имя контакта");
                    String nameToSearch = scanner.next();
                    contactManager.searchContact(nameToSearch);
                    break;
                case 0:
                    System.out.println("Успешно завершено");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте ещё раз");
            }
        }
    }
}