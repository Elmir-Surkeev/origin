package _Booking;
import java.util.Scanner;

public class SecurityManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Настройки и безопасность ===");
            System.out.println("1. Контроль доступа");
            System.out.println("2. Резервное копирование данных");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    manageAccessControl();
                    break;
                case 2:
                    backupData();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void manageAccessControl() {
        // Логика управления контролем доступа
        System.out.println("Контроль доступа...");
    }

    private void backupData() {
        // Логика резервного копирования данных
        System.out.println("Резервное копирование данных...");
    }
}

