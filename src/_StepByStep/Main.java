package _StepByStep;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // Предустановленные логины и пароли
    private static final Map<String, String> predefinedCredentials = new HashMap<>();
    static {
        predefinedCredentials.put("!Elmir", "6632");
        predefinedCredentials.put("!Belek", "6632");
    }

    // Переменная для хранения информации о наличии заведения
    private static boolean cafeAdded = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите логин:");
            String login = scanner.nextLine();

            System.out.println("Введите пароль:");
            String password = scanner.nextLine();

            // Проверка логина и пароля
            if (authenticate(login, password)) {
                if (login.startsWith("!")) {
                    if (!cafeAdded) {
                        System.out.println("Принтлн добавить заведение");
                        cafeAdded = true; // Устанавливаем флаг, что заведение добавлено
                    }
                } else {
                    chooseCafe(scanner);
                }
                break; // Выход из цикла при успешном входе
            } else {
                System.out.println("Ошибка: неправильный логин или пароль.");
                System.out.println("Хотите зарегистрироваться? (да/нет)");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("да")) {
                    register(scanner);
                }
            }
        }

        scanner.close();
    }

    // Метод для проверки логина и пароля
    private static boolean authenticate(String login, String password) {
        // Проверка предустановленных данных
        String predefinedPassword = predefinedCredentials.get(login);
        return predefinedPassword != null && predefinedPassword.equals(password);
    }

    // Метод для регистрации нового пользователя
    private static void register(Scanner scanner) {
        System.out.println("Регистрация нового пользователя:");

        System.out.println("Введите новый логин:");
        String newLogin = scanner.nextLine();

        // Проверка на существующий логин
        if (predefinedCredentials.containsKey(newLogin)) {
            System.out.println("Ошибка: такой логин уже существует.");
            return;
        }

        System.out.println("Введите новый пароль:");
        String newPassword = scanner.nextLine();

        // Добавление нового пользователя
        predefinedCredentials.put(newLogin, newPassword);

        System.out.println("Регистрация успешна! Теперь вы можете войти с новыми данными.");
    }

    // Метод для выбора кафе
    private static void chooseCafe(Scanner scanner) {
        System.out.println("Выберите Услугу:");
        System.out.println("1. Кафе 'Приятная атмосфера'");
        System.out.println("2. Кафе 'Вкусная выпечка'");
        System.out.println("3. Кафе 'Уютный уголок'");
        System.out.println("Введите номер кафе:");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после nextInt()

        switch (choice) {
            case 1:
                System.out.println("Вы выбрали кафе 'Приятная атмосфера'.");
                break;
            case 2:
                System.out.println("Вы выбрали кафе 'Вкусная выпечка'.");
                break;
            case 3:
                System.out.println("Вы выбрали кафе 'Уютный уголок'.");
                break;
            default:
                System.out.println("Ошибка: неверный выбор.");
        }
    }
}
