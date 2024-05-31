package _Booking;
import java.util.Scanner;

public class StaffManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Управление персоналом ===");
            System.out.println("1. Графики работы");
            System.out.println("2. Учет рабочего времени");
            System.out.println("3. Контактная информация");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    manageWorkSchedules();
                    break;
                case 2:
                    trackWorkHours();
                    break;
                case 3:
                    manageContactInfo();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void manageWorkSchedules() {
        // Логика управления графиками работы
        System.out.println("Управление графиками работы...");
    }

    private void trackWorkHours() {
        // Логика учета рабочего времени
        System.out.println("Учет рабочего времени...");
    }

    private void manageContactInfo() {
        // Логика управления контактной информацией
        System.out.println("Управление контактной информацией...");
    }
}

