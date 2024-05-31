package _Booking;

import java.util.Scanner;

public class IntegrationManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Интеграция с внешними системами ===");
            System.out.println("1. Онлайн-бронирование");
            System.out.println("2. Платежные системы");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    manageOnlineBooking();
                    break;
                case 2:
                    managePaymentSystems();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void manageOnlineBooking() {
        // Логика управления онлайн-бронированием
        System.out.println("Онлайн-бронирование...");
    }

    private void managePaymentSystems() {
        // Логика управления платежными системами
        System.out.println("Платежные системы...");
    }
}
