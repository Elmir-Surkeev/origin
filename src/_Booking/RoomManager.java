package _Booking;
import java.util.Scanner;

public class RoomManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Управление номерами ===");
            System.out.println("1. Просмотреть доступные номера");
            System.out.println("2. Планирование уборки");
            System.out.println("3. Ремонт и техническое обслуживание");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    planCleaning();
                    break;
                case 3:
                    manageMaintenance();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void viewAvailableRooms() {
        // Логика просмотра доступных номеров
        System.out.println("Просмотр доступных номеров...");
    }

    private void planCleaning() {
        // Логика планирования уборки
        System.out.println("Планирование уборки...");
    }

    private void manageMaintenance() {
        // Логика управления ремонтом и обслуживанием
        System.out.println("Управление ремонтом и обслуживанием...");
    }
}
