package _Booking;
import java.util.Scanner;

public class ClientManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Управление клиентами ===");
            System.out.println("1. База данных клиентов");
            System.out.println("2. Программа лояльности");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    manageClientDatabase();
                    break;
                case 2:
                    manageLoyaltyProgram();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void manageClientDatabase() {
        // Логика управления базой данных клиентов
        System.out.println("Управление базой данных клиентов...");
    }

    private void manageLoyaltyProgram() {
        // Логика управления программой лояльности
        System.out.println("Управление программой лояльности...");
    }
}

