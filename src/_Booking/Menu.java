package _Booking;
import java.util.Scanner;
public class Menu {
    private BookingManager bookingManager;
    private RoomManager roomManager;
    private GuestManager guestManager;
    private StaffManager staffManager;
    private FinanceManager financeManager;
    private ReportManager reportManager;
    private ClientManager clientManager;
    private FeedbackManager feedbackManager;
    private InventoryManager inventoryManager;
    private IntegrationManager integrationManager;
    private SecurityManager securityManager;

    public Menu() {
        bookingManager = new BookingManager();
        roomManager = new RoomManager();
        guestManager = new GuestManager();
        staffManager = new StaffManager();
        financeManager = new FinanceManager();
        reportManager = new ReportManager();
        clientManager = new ClientManager();
        feedbackManager = new FeedbackManager();
        inventoryManager = new InventoryManager();
        integrationManager = new IntegrationManager();
        securityManager = new SecurityManager();
    }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Главное меню ===");
            System.out.println("1. Управление бронированиями");
            System.out.println("2. Управление номерами");
            System.out.println("3. Регистрация и выезд гостей");
            System.out.println("4. Управление персоналом");
            System.out.println("5. Финансовый учет");
            System.out.println("6. Отчеты и аналитика");
            System.out.println("7. Управление клиентами");
            System.out.println("8. Обратная связь и отзывы");
            System.out.println("9. Управление инвентарем");
            System.out.println("10. Интеграция с внешними системами");
            System.out.println("11. Настройки и безопасность");
            System.out.println("0. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    bookingManager.displayMenu();
                    break;
                case 2:
                    roomManager.displayMenu();
                    break;
                case 3:
                    guestManager.displayMenu();
                    break;
                case 4:
                    staffManager.displayMenu();
                    break;
                case 5:
                    financeManager.displayMenu();
                    break;
                case 6:
                    reportManager.displayMenu();
                    break;
                case 7:
                    clientManager.displayMenu();
                    break;
                case 8:
                    feedbackManager.displayMenu();
                    break;
                case 9:
                    inventoryManager.displayMenu();
                    break;
                case 10:
                    integrationManager.displayMenu();
                    break;
                case 11:
                    securityManager.displayMenu();
                    break;
                case 0:
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}
