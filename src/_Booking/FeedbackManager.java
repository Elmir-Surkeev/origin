package _Booking;
import java.util.Scanner;

public class FeedbackManager {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Обратная связь и отзывы ===");
            System.out.println("1. Сбор отзывов");
            System.out.println("2. Анализ отзывов");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    collectFeedback();
                    break;
                case 2:
                    analyzeFeedback();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void collectFeedback() {
        // Логика сбора отзывов
        System.out.println("Сбор отзывов...");
    }

    private void analyzeFeedback() {
        // Логика анализа отзывов
        System.out.println("Анализ отзывов...");
    }
}
