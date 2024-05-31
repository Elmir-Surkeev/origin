package _Booking;
import java.util.Scanner;

public class ReportManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Отчеты и аналитика ===");
            System.out.println("1. Статистика заселения");
            System.out.println("2. Анализ доходов и расходов");
            System.out.println("3. Отчеты о клиентах");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    generateOccupancyStats();
                    break;
                case 2:
                    analyzeIncomeExpenses();
                    break;
                case 3:
                    generateClientReports();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void generateOccupancyStats() {
        // Логика создания статистики заселения
        System.out.println("Статистика заселения...");
    }

    private void analyzeIncomeExpenses() {
        // Логика анализа доходов и расходов
        System.out.println("Анализ доходов и расходов...");
    }

    private void generateClientReports() {
        // Логика создания отчетов о клиентах
        System.out.println("Отчеты о клиентах...");
    }
}

