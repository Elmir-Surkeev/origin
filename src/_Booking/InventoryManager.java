package _Booking;
import java.util.Scanner;

public class InventoryManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Управление инвентарем ===");
            System.out.println("1. Учет инвентаря");
            System.out.println("2. Заказ и пополнение");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    manageInventory();
                    break;
                case 2:
                    orderAndRestock();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void manageInventory() {
        // Логика управления инвентарем
        System.out.println("Учет инвентаря...");
    }

    private void orderAndRestock() {
        // Логика заказа и пополнения инвентаря
        System.out.println("Заказ и пополнение...");
    }
}

