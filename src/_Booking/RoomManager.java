package _Booking;
import java.io.*;
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
                    planCleaning(scanner);
                    break;
                case 3:
                    manageMaintenance(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void viewAvailableRooms() {
        try (BufferedReader br = new BufferedReader(new FileReader("rooms.txt"))) {
            String line;
            System.out.println("Доступные номера:");
            while ((line = br.readLine()) != null) {
                if (line.contains("Available")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private void planCleaning(Scanner scanner) {
        System.out.print("Введите номер комнаты для планирования уборки: ");
        String room = scanner.nextLine();
        System.out.print("Введите дату уборки (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("cleaning_schedule.txt", true))) {
            bw.write(room + ": " + date);
            bw.newLine();
            System.out.println("Уборка запланирована для комнаты " + room + " на " + date);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private void manageMaintenance(Scanner scanner) {
        System.out.print("Введите номер комнаты для ремонта/обслуживания: ");
        String room = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String task = scanner.nextLine();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("maintenance.txt", true))) {
            bw.write(room + ": " + task);
            bw.newLine();
            System.out.println("Ремонт/обслуживание запланировано для комнаты " + room + ": " + task);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        RoomManager manager = new RoomManager();
        manager.displayMenu();
    }
}
