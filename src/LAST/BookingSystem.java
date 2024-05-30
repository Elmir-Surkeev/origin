package LAST;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingSystem {
    private List<Infrastructure> infrastructures;

    public BookingSystem() {
        infrastructures = new ArrayList<>();
    }

    public void addInfrastructure(Infrastructure infrastructure) {
        infrastructures.add(infrastructure);
    }

    public void showInfrastructures() {
        for (int i = 0; i < infrastructures.size(); i++) {
            System.out.println((i + 1) + ". " + infrastructures.get(i));
        }
    }

    public void sortInfrastructuresByPrice() {
        infrastructures.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
    }

    public Infrastructure chooseInfrastructure(int index) {
        if (index >= 0 && index < infrastructures.size()) {
            return infrastructures.get(index);
        }
        return null;
    }

    public void book() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ваш логин:");
        String username = scanner.nextLine();
        System.out.println("Введите ваш пароль:");
        String password = scanner.nextLine();

        if (!User.login(username, password)) {
            System.out.println("Неверный логин или пароль. Пожалуйста, зарегистрируйтесь.");
            System.out.println("Введите новый логин:");
            username = scanner.nextLine();
            System.out.println("Введите новый пароль:");
            password = scanner.nextLine();
            User.register(username, password);
            System.out.println("Регистрация прошла успешно! Теперь войдите в систему.");
            return;
        }

        System.out.println("Добро пожаловать, " + username + "! Выберите инфраструктуру:");
        showInfrastructures();
        int infrastructureIndex = scanner.nextInt();
        Infrastructure selectedInfrastructure = chooseInfrastructure(infrastructureIndex - 1);
        if (selectedInfrastructure == null) {
            System.out.println("Неверный выбор инфраструктуры.");
            return;
        }

        System.out.println("Введите количество дней пребывания:");
        int numberOfDays = scanner.nextInt();

        double totalPrice = selectedInfrastructure.getPrice() * numberOfDays;
        System.out.println("Итоговая стоимость: " + totalPrice);
    }

    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();

        // Добавляем гостиницы, кафе и рестораны
        bookingSystem.addInfrastructure(new Hotel("Отель А", "Москва", 2000, 100));
        bookingSystem.addInfrastructure(new Hotel("Отель B", "Санкт-Петербург", 1800, 80));
        bookingSystem.addInfrastructure(new Cafe("Кафе Вкусно", "Москва", 500, "Итальянская"));
        bookingSystem.addInfrastructure(new Restaurant("Ресторан Уют", "Сочи", 1500, "Французская"));

        // Сортировка по цене
        bookingSystem.sortInfrastructuresByPrice();

        // Регистрация и бронирование
        bookingSystem.book();
    }
}

