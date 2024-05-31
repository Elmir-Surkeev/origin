package _Booking;

import java.util.Scanner;

public class GuestManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Регистрация и выезд гостей ===");
            System.out.println("1. Регистрация гостей");
            System.out.println("2. Выезд гостей");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerGuest();
                    break;
                case 2:
                    checkoutGuest();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void registerGuest() {
        // Логика регистрации гостей
        System.out.println("Регистрация гостей...");
    }

    private void checkoutGuest() {
        // Логика выезда гостей
        System.out.println("Выезд гостей...");
    }
}
