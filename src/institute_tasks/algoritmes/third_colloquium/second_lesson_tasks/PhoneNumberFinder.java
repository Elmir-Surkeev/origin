package institute_tasks.algoritmes.third_colloquium.second_lesson_tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneNumberFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество номеров телефонов: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // считываем лишний перевод строки

        Map<String, String> phoneBook = new HashMap<>();

        System.out.println("Введите номера телефонов и их владельцев:");

        for (int i = 0; i < n; i++) {
            System.out.print("Номер телефона " + (i + 1) + ": ");
            String phone = scanner.next();
            System.out.print("Имя владельца: ");
            String name = scanner.next().toLowerCase(); // приводим к нижнему регистру
            phoneBook.put(name, phone);
        }

        System.out.print("Введите имя владельца для поиска его номера телефона: ");
        String query = scanner.next().toLowerCase(); // приводим к нижнему регистру

        String phoneNumber = phoneBook.get(query);

        if (phoneNumber != null) {
            System.out.println("Номер телефона для " + query + ": " + phoneNumber);
        } else {
            System.out.println("Нет в телефонной книге");
        }

        scanner.close();
    }
}
