package _Booking;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BookingManager {
    private List<Booking> bookings = new ArrayList<>();
    private boolean[] rooms = new boolean[50]; // массив для отслеживания статуса бронирования каждого помещения
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void displayMenu() {
        while (true) {
            System.out.println("=== Управление бронированиями ===");
            System.out.println("1. Создать бронирование");
            System.out.println("2. Просмотреть текущие бронирования");
            System.out.println("3. Изменить бронирование");
            System.out.println("4. Отменить бронирование");
            System.out.println("5. Обновить дату выезда");
            System.out.println("6. Забронировать помещение администратором");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createBooking();
                    break;
                case 2:
                    viewBookings();
                    break;
                case 3:
                    editBooking();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    updateCheckOutDate();
                    break;
                case 6:
                    adminBookRoom();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void createBooking() {
        try {
            System.out.print("Введите имя гостя: ");
            String guestName = scanner.nextLine();

            // Проверка ввода даты заезда
            Pattern datePattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
            String checkInDateString;
            do {
                System.out.print("Введите дату заезда (dd/MM/yyyy): ");
                checkInDateString = scanner.nextLine();
                Matcher matcher = datePattern.matcher(checkInDateString);
                if (!matcher.matches()) {
                    System.out.println("Неправильный формат даты заезда. Пожалуйста, используйте формат dd/MM/yyyy.");
                }
            } while (!checkInDateString.matches("\\d{2}/\\d{2}/\\d{4}"));

            Date checkInDate = dateFormat.parse(checkInDateString);

            // Ввод остальных данных
            System.out.print("Введите дату выезда (dd/MM/yyyy) или оставьте пустым, если неизвестна: ");
            String checkOutDateString = scanner.nextLine();
            Date checkOutDate = null;
            if (!checkOutDateString.isEmpty()) {
                checkOutDate = dateFormat.parse(checkOutDateString);
            }
            System.out.print("Введите тип номера: ");
            String roomType = scanner.nextLine();

            // Ввод и проверка номера помещения
            int roomNumber;
            do {
                System.out.print("Введите номер помещения (1-50): ");
                roomNumber = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (roomNumber < 1 || roomNumber > 50) {
                    System.out.println("Неверный номер помещения. Пожалуйста, выберите номер от 1 до 50.");
                } else if (rooms[roomNumber - 1]) {
                    System.out.println("Помещение уже забронировано. Пожалуйста, выберите другой номер.");
                } else {
                    break;
                }
            } while (true);

            Booking booking = new Booking(guestName, checkInDate, checkOutDate, roomType, roomNumber);
            bookings.add(booking);
            rooms[roomNumber - 1] = true;
            System.out.println("Бронирование создано: " + booking);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты. Попробуйте снова.");
        }
    }

    private void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("Нет текущих бронирований.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    private void editBooking() {
        System.out.print("Введите ID бронирования для изменения: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Booking booking = findBookingById(id);
        if (booking != null) {
            try {
                // Освободить старое помещение
                rooms[booking.getRoomNumber() - 1] = false;

                System.out.print("Введите новое имя гостя (текущие: " + booking.getGuestName() + "): ");
                String guestName = scanner.nextLine();
                System.out.print("Введите новую дату заезда (dd/MM/yyyy, текущие: " + dateFormat.format(booking.getCheckInDate()) + "): ");
                Date checkInDate = dateFormat.parse(scanner.nextLine());
                System.out.print("Введите новую дату выезда (dd/MM/yyyy, текущие: " + (booking.getCheckOutDate() != null ? dateFormat.format(booking.getCheckOutDate()) : "Неизвестна") + "): ");
                String checkOutDateString = scanner.nextLine();
                Date checkOutDate = null;
                if (!checkOutDateString.isEmpty()) {
                    checkOutDate = dateFormat.parse(checkOutDateString);
                }
                System.out.print("Введите новый тип номера (текущие: " + booking.getRoomType() + "): ");
                String roomType = scanner.nextLine();

                // Ввод и проверка нового номера помещения
                int roomNumber;
                do {
                    System.out.print("Введите новый номер помещения (1-50): ");
                    roomNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if (roomNumber < 1 || roomNumber > 50) {
                        System.out.println("Неверный номер помещения. Пожалуйста, выберите номер от 1 до 50.");
                    } else if (rooms[roomNumber - 1]) {
                        System.out.println("Помещение уже забронировано. Пожалуйста, выберите другой номер.");
                    } else {
                        break;
                    }
                } while (true);

                booking.setGuestName(guestName);
                booking.setCheckInDate(checkInDate);
                booking.setCheckOutDate(checkOutDate);
                booking.setRoomType(roomType);
                booking.setRoomNumber(roomNumber);

                // Забронировать новое помещение
                rooms[roomNumber - 1] = true;

                System.out.println("Бронирование изменено: " + booking);
            } catch (ParseException e) {
                System.out.println("Неверный формат даты. Попробуйте снова.");
            }
        } else {
            System.out.println("Бронирование с ID " + id + " не найдено.");
        }
    }

    private void cancelBooking() {
        System.out.print("Введите ID бронирования для отмены: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Booking booking = findBookingById(id);
        if (booking != null) {
            // Освободить помещение
            rooms[booking.getRoomNumber() - 1] = false;
            bookings.remove(booking);
            System.out.println("Бронирование отменено: " + booking);
        } else {
            System.out.println("Бронирование с ID " + id + " не найдено.");
        }
    }

    private void updateCheckOutDate() {
        System.out.print("Введите ID бронирования для обновления даты выезда: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Booking booking = findBookingById(id);
        if (booking != null) {
            try {
                System.out.print("Введите новую дату выезда (dd/MM/yyyy): ");
                Date checkOutDate = dateFormat.parse(scanner.nextLine());
                booking.setCheckOutDate(checkOutDate);
                System.out.println("Дата выезда обновлена: " + booking);
            } catch (ParseException e) {
                System.out.println("Неверный формат даты. Попробуйте снова.");
            }
        } else {
            System.out.println("Бронирование с ID " + id + " не найдено.");
        }
    }

    private void adminBookRoom() {
        System.out.print("Введите номер помещения для бронирования (1-50): ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (roomNumber < 1 || roomNumber > 50) {
            System.out.println("Неверный номер помещения. Пожалуйста, выберите номер от 1 до 50.");
            return;
        }

        if (rooms[roomNumber - 1]) {
            System.out.println("Помещение уже забронировано.");
        } else {
            rooms[roomNumber - 1] = true;
            System.out.println("Помещение " + roomNumber + " забронировано администратором.");
        }
    }

    private Booking findBookingById(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BookingManager manager = new BookingManager();
        manager.displayMenu();
    }
}
