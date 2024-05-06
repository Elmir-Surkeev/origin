package institute_tasks._CourseWork;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TourismOffer offer = new TourismOffer("Hotel"); // Создание предложения
        List<TourismFacility> defaultHotels = new ArrayList<>(); // Список для хранения отелей по умолчанию

        loadDefaultHotels(offer, defaultHotels); // Загрузка отелей по умолчанию

        while (true) {
            System.out.println("Введите 1, чтобы добавить отель;");
            System.out.println("Введите 2, чтобы найти отель;");
            System.out.println("Введите 3, чтобы написать отзыв об отеле;");
            System.out.println("Введите 4, чтобы вывести все отели;");
            System.out.println("Введите 0, чтобы выйти.");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Чтобы съесть символ новой строки

            switch (choice) {
                case 1:
                    addHotel(offer, scanner, defaultHotels); // Передаем defaultHotels для добавления в него нового отеля
                    break;
                case 2:
                    findHotel(offer, scanner);
                    break;
                case 3:
                    writeReview(offer, scanner);
                    break;
                case 4:
                    System.out.println("Все отели:");
                    System.out.println(offer.getAllHotelsAsString());
                    break;
                case 0:
                    System.out.println("Программа завершена.");
                    saveDataToFile(defaultHotels); // Сохранение отелей по умолчанию перед выходом
                    System.exit(0);
                default:
                    System.out.println("Неверный ввод. Пожалуйста, попробуйте еще раз.");
            }
        }
    }

    // Функция для добавления отеля
    private static void addHotel(TourismOffer offer, Scanner scanner, List<TourismFacility> defaultHotels) {
        System.out.print("Введите название отеля: ");
        String hotelName = scanner.nextLine();
        System.out.print("Введите расположение отеля: ");
        String hotelLocation = scanner.nextLine();
        System.out.print("Введите рейтинг отеля: ");
        double hotelRating = scanner.nextDouble();
        System.out.print("Введите цену за проживание: ");
        double hotelPrice = scanner.nextDouble();
        scanner.nextLine(); // Чтобы съесть символ новой строки
        System.out.print("Введите имя владельца: ");
        String userName = scanner.nextLine();
        System.out.print("Введите номер администратора: ");
        String userPhone = scanner.nextLine();
        offer.addHotel(hotelName, hotelLocation, hotelRating, hotelPrice, userName, userPhone);
        // Добавляем новый отель в список defaultHotels
        defaultHotels.add(new TourismFacility(hotelName, hotelLocation, hotelRating, hotelPrice));
        System.out.println("Отель успешно добавлен.");
    }

    // Функция для поиска отеля
    private static void findHotel(TourismOffer offer, Scanner scanner) {
        System.out.println("Выберите параметр для поиска отеля:");
        System.out.println("1. По названию;");
        System.out.println("2. По региону;");
        System.out.println("3. По цене;");
        int searchOption = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после считывания числа

        switch (searchOption) {
            case 1:
                System.out.print("Введите название отеля: ");
                String hotelName = scanner.nextLine().toLowerCase();
                List<TourismFacility> hotelsByName = offer.findHotelByName(hotelName);
                printFoundHotels(hotelsByName);
                break;
            case 2:
                System.out.print("Введите регион: ");
                String location = scanner.nextLine();
                List<TourismFacility> foundHotelsByLocation = offer.chooseFacilities(location);
                printFoundHotels(foundHotelsByLocation);
                break;
            case 3:
                System.out.print("Введите максимальную цену: ");
                double maxPrice = scanner.nextDouble();
                List<TourismFacility> foundHotelsByPrice = offer.findHotelByPrice(maxPrice);
                printFoundHotels(foundHotelsByPrice);
                break;
            default:
                System.out.println("Неверный ввод.");
        }
    }

    // Функция для вывода найденных отелей
    private static void printFoundHotels(List<TourismFacility> hotels) {
        if (hotels.isEmpty()) {
            System.out.println("Отелей по вашему запросу не найдено.");
        } else {
            System.out.println("Найденные отели:");
            for (TourismFacility hotel : hotels) {
                System.out.println(hotel);
                if (hotel.getReviews().isEmpty()) {
                    System.out.println("Отзывов пока что нет.");
                } else {
                    System.out.println("Отзывы:");
                    for (String review : hotel.getReviews()) {
                        System.out.println("- " + review);
                    }
                }
            }
        }
    }

    // Функция для написания отзыва об отеле
    private static void writeReview(TourismOffer offer, Scanner scanner) {
        System.out.print("Введите название отеля, о котором хотите написать отзыв: ");
        String hotelToReview = scanner.nextLine();
        System.out.print("Введите ваше имя: ");
        String userName = scanner.nextLine();
        System.out.print("Введите ваш номер телефона: ");
        String userPhone = scanner.nextLine();
        System.out.print("Напишите ваш отзыв: ");
        String review = scanner.nextLine();
        offer.writeReview(hotelToReview, userName, userPhone, review);
    }

    // Функция для загрузки отелей по умолчанию
    private static void loadDefaultHotels(TourismOffer offer, List<TourismFacility> defaultHotels) {
        // В этой функции можно загрузить данные о ранее добавленных отелях из файла или другого источника данных
        // Здесь для примера просто добавляем несколько отелей по умолчанию
        defaultHotels.add(new TourismFacility("Default Hotel", "City Center", 4.0, 80));
        defaultHotels.add(new TourismFacility("Beach Hotel", "Beachfront", 4.5, 120));
        defaultHotels.add(new TourismFacility("Mountain Lodge", "Mountain Area", 4.2, 100));
        // Добавляем эти отели также в предложение
        for (TourismFacility hotel : defaultHotels) {
            offer.addHotel(hotel.getName(), hotel.getLocation(), hotel.getRating(), hotel.getPrice(), "Admin", "");
        }
    }

    // Функция для сохранения отелей по умолчанию в файл
    private static void saveDataToFile(List<TourismFacility> defaultHotels) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("defaultHotels.dat"))) {
            oos.writeObject(defaultHotels);
            System.out.println("Отели по умолчанию успешно сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении отелей по умолчанию.");
        }
    }
}

class TourismFacility implements Serializable {
    private String name;
    private String location;
    private double rating;
    private double price;
    private List<String> reviews;

    // Конструктор
    public TourismFacility(String name, String location, double rating, double price) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.price = price;
        this.reviews = new ArrayList<>();
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getReviews() {
        return reviews;
    }

    // Метод для добавления отзыва
    public void addReview(String review) {
        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Отель: " + name + ", Расположение: " + location + ", Рейтинг: " + rating + ", Цена за проживание: " + price;
    }
}

class TourismOffer {
    private String type;
    private List<TourismFacility> facilities;

    // Конструктор
    public TourismOffer(String type) {
        this.type = type;
        this.facilities = new ArrayList<>();
    }

    // Функция для добавления отеля
    public void addHotel(String name, String location, double rating, double price, String userName, String userPhone) {
        TourismFacility hotel = new TourismFacility(name, location, rating, price);
        hotel.addReview("Добавлено администратором: " + userName + ", Телефон: " + userPhone);
        facilities.add(hotel);
    }

    // Функция для поиска отеля по названию
    public List<TourismFacility> findHotelByName(String name) {
        List<TourismFacility> foundHotels = new ArrayList<>();
        for (TourismFacility facility : facilities) {
            if (facility.getName().toLowerCase().contains(name.toLowerCase())) {
                foundHotels.add(facility);
            }
        }
        return foundHotels;
    }

    // Функция для поиска отеля по региону
    public List<TourismFacility> chooseFacilities(String location) {
        List<TourismFacility> foundFacilities = new ArrayList<>();
        for (TourismFacility facility : facilities) {
            if (facility.getLocation().equalsIgnoreCase(location)) {
                foundFacilities.add(facility);
            }
        }
        return foundFacilities;
    }

    // Функция для поиска отеля по цене
    public List<TourismFacility> findHotelByPrice(double maxPrice) {
        List<TourismFacility> foundHotels = new ArrayList<>();
        for (TourismFacility facility : facilities) {
            if (facility.getPrice() <= maxPrice) {
                foundHotels.add(facility);
            }
        }
        return foundHotels;
    }

    // Функция для написания отзыва об отеле
    public void writeReview(String hotelName, String userName, String userPhone, String review) {
        for (TourismFacility facility : facilities) {
            if (facility.getName().equalsIgnoreCase(hotelName)) {
                facility.addReview("Отзыв пользователя " + userName + ", Телефон: " + userPhone + ": " + review);
                System.out.println("Отзыв успешно добавлен.");
                return;
            }
        }
        System.out.println("Отель не найден.");
    }

    // Функция для получения всех отелей в виде строки
    public String getAllHotelsAsString() {
        StringBuilder result = new StringBuilder();
        for (TourismFacility facility : facilities) {
            result.append(facility).append("\n");
        }
        return result.toString();
    }
}
