package _Booking;

import java.util.Scanner;

public class FinanceManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Финансовый учет ===");
            System.out.println("1. Учет платежей");
            System.out.println("2. Создание отчетов");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    managePayments();
                    break;
                case 2:
                    generateReports();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void managePayments() {
        // Логика управления платежами
        System.out.println("Учет платежей...");
    }

    private void generateReports() {
        // Логика создания отчетов
        System.out.println("Создание отчетов...");
    }
}
