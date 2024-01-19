import java.io.*;
import java.util.Scanner;

public class ContactManager {
    private static final int MAX_CONTACTS = 100;
    private Contact[] contacts;
    public int size;


    public void showMenu(){
        System.out.println("==========Телефонная книжка==========");
        System.out.println("1. Посмотреть контакты");
        System.out.println("2. Создать новый контакт");
        System.out.println("3. Обновить контакт");
        System.out.println("4. Удалить контакт");
        System.out.println("5. Сохранить контакты в файл");
        System.out.println("6. Загрузить контакты из файла");
        System.out.println("7. Поиск контакта");
        System.out.println("0. Выход");
    }

    public ContactManager() {
        this.contacts = new Contact[MAX_CONTACTS];
        this.size = 0;
    }

    public void addContact(Contact contact){
        if (size < MAX_CONTACTS) {
            contacts[size ++] = contact;
            System.out.println("Контакт успешно добавлен.");
        } else {
            System.out.println("Невозможно добавить контакты.");
        }
    }
    public void displayContacts() {
        if (size == 0){
            System.out.println("Список контактов пуст!");
            return;
        }
        System.out.println("------------------------" + "\nСписок контактов:");
        for (int i = 0; i < size; i++) {
            System.out.println("Контакт №" + (i + 1));
            System.out.println(contacts[i]);
            System.out.println("------------------------");
        }
    }

    public void updateContact(int index) {
        if (index >= 0 && index < size) {
            Contact contactToUpdate = contacts[index];

            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите, что вы хотите обновить:");
            System.out.println("1. Имя");
            System.out.println("2. Фамилия");
            System.out.println("3. Номер телефона");
            System.out.println("4. Электронная почта");
            System.out.println("5. Отмена");

            System.out.print("Введите номер опции: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите новое имя: ");
                    String updatedFirstName = scanner.next();
                    contactToUpdate.setFirstName(updatedFirstName);
                    break;
                case 2:
                    System.out.print("Введите новую фамилию: ");
                    String updatedLastName = scanner.next();
                    contactToUpdate.setLastName(updatedLastName);
                    break;
                case 3:
                    System.out.print("Введите новый номер телефона: ");
                    String updatedPhoneNumber = scanner.next();
                    contactToUpdate.setPhoneNumber(updatedPhoneNumber);
                    break;
                case 4:
                    System.out.print("Введите новую электронную почту: ");
                    String updatedEmail = scanner.next();
                    contactToUpdate.setEmail(updatedEmail);
                    break;
                case 5:
                    System.out.println("Обновление отменено.");
                    return;
                default:
                    System.out.println("Некорректный выбор. Обновление отменено.");
                    return;
            }

            System.out.println("Контакт успешно обновлен:");
        } else {
            System.out.println("Некорректный индекс контакта.");
        }
    }


    public void deleteContact(String firstName) {
        if (size == 0){
            System.out.println("Список контактов пуст");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (contacts[i].getFirstName().equalsIgnoreCase(firstName) || contacts[i].getLastName().equalsIgnoreCase(firstName)) {
                System.out.println("Подтвердите выбор: 1 - ДА   2 - НЕТ");
                Scanner scanner = new Scanner(System.in);
                int confirmationChoice = scanner.nextInt();
                if (confirmationChoice == 1){
                contacts[i] = contacts[size - 1];
                size--;

                System.out.println("---------------------------");
                System.out.println("Контакт успешно удален:");}
                else {
                    System.out.println("Удаление отменено");
                    return;}
            }
            else {
                System.out.println("Контакт с таким именем не найден!");
            }
        }
    }

    public void searchContact(String firstName) {
        if (size == 0) {
            System.out.println("Список контактов пуст");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (contacts[i].getFirstName().equalsIgnoreCase(firstName) || contacts[i].getLastName().equalsIgnoreCase(firstName)) {
                System.out.println("Контакт найден.");
                System.out.println(contacts[i]);
            } else {
                System.out.println("Контакт не найден.");
            }
        }
    }
    public void saveContactsToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < size; i++) {
                Contact contact = contacts[i];
                writer.println(contact.getFirstName() + "," +
                        contact.getLastName() + "," +
                        contact.getPhoneNumber() + "," +
                        contact.getEmail());
            }
            System.out.println("Contacts saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }
    public void loadContactsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contactData = line.split(",");
                if (contactData.length == 4) {
                    Contact contact = new Contact(
                            contactData[0].trim(),
                            contactData[1].trim(),
                            contactData[2].trim(),
                            contactData[3].trim()
                    );
                    addContact(contact);
                }
            }
            System.out.println("Contacts loaded from file successfully.");
        } catch (IOException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }


}