package _CourseWork;

import java.io.*;
import java.util.*;

// Процесс бронирования в индустрии гостеприимства.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TourismOffer offer = new TourismOffer("Hotel"); // Создание предложения
        List<TourismFacility> defaultHotels = new ArrayList<>(); // Список для хранения стандартных отелей

        loadDefaultHotels(offer, defaultHotels); // Загрузка стандартных отелей

        System.out.println("Выберите режим работы:");
        System.out.println("1. гость");
        System.out.println("2. админ");
        int mode = scanner.nextInt();
        scanner.nextLine(); // Считываем символ новой строки

        switch (mode) {
            case 1:
                viewMode(offer, defaultHotels, scanner);
                break;
            case 2:
                editMode(offer, defaultHotels, scanner);
                break;
            default:
                System.out.println("Неверный ввод. Повторите попытку.");
        }
    }

    private static void viewMode(TourismOffer offer, List<TourismFacility> defaultHotels, Scanner scanner) {
        while (true) {
            System.out.println("0. Просмотреть лучшие предложения;");
            System.out.println("1. Найти отель;");
            System.out.println("2. Оставить отзыв;");
            System.out.println("3. Просмотреть все отели;");
            System.out.println("4. Выйти.");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки

            switch (choice) {
                case 0:
                    viewBestOffers(offer);
                    break;
                case 1:
                    findHotel(offer, scanner);
                    break;
                case 2:
                    writeReview(offer, scanner);
                    break;
                case 3:
                    System.out.println("Все отели:");
                    printAllHotels(offer);
                    break;
                case 4:
                    System.out.println("Программа завершена.");
                    saveDataToFile(defaultHotels); // Сохранение стандартных отелей перед выходом
                    System.exit(0);
                default:
                    System.out.println("Неверный ввод. Повторите попытку.");
            }
        }
    }

    private static void viewBestOffers(TourismOffer offer) {
        List<TourismFacility> bestOffers = offer.getBestOffers();
        if (bestOffers.isEmpty()) {
            System.out.println("Нет лучших предложений на данный момент.");
        } else {
            System.out.println("Лучшие предложения:");
            for (TourismFacility hotel : bestOffers) {
                System.out.println(hotel);
            }
        }
    }

    private static void editMode(TourismOffer offer, List<TourismFacility> defaultHotels, Scanner scanner) {
        while (true) {
            System.out.println("1. Добавить отель;");
            System.out.println("2. Редактировать отель;");
            System.out.println("3. Удалить отель;");
            System.out.println("4. Просмотреть все отели;");
            System.out.println("5. Выйти.");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки

            switch (choice) {
                case 1:
                    addHotel(offer, scanner, defaultHotels);
                    break;
                case 2:
                    editHotel(offer, scanner);
                    break;
                case 3:
                    removeHotel(offer, scanner);
                    break;
                case 4:
                    printAllHotels(offer);
                    break;
                case 5:
                    System.out.println("Программа завершена.");
                    saveDataToFile(defaultHotels); // Сохранение стандартных отелей перед выходом
                    System.exit(0);
                default:
                    System.out.println("Неверный ввод. Повторите попытку.");
            }
        }
    }

    // Функция для добавления отеля
    private static void addHotel(TourismOffer offer, Scanner scanner, List<TourismFacility> defaultHotels) {
        System.out.print("Введите название отеля: ");
        String hotelName = scanner.nextLine();
        System.out.print("Введите местоположение отеля: ");
        String hotelLocation = scanner.nextLine();
        System.out.print("Введите рейтинг отеля: ");
        double hotelRating = scanner.nextDouble();
        System.out.print("Введите цену за ночь: ");
        double hotelPrice = scanner.nextDouble();
        scanner.nextLine(); // Считываем символ новой строки
        System.out.print("Введите сторону от солнца: ");
        String sunSide = scanner.nextLine();
        System.out.print("Есть ли бассейн (true/false): ");
        boolean hasPool = scanner.nextBoolean();
        System.out.print("Есть ли хранение багажа (true/false): ");
        boolean hasLuggageStorage = scanner.nextBoolean();
        System.out.print("Есть ли каршеринг (true/false): ");
        boolean hasCarSharing = scanner.nextBoolean();
        System.out.print("Есть ли детская зона (true/false): ");
        boolean hasKidsZone = scanner.nextBoolean();
        scanner.nextLine(); // Считываем символ новой строки
        System.out.print("Введите имя владельца: ");
        String userName = scanner.nextLine();
        System.out.print("Введите телефон администратора: ");
        String userPhone = scanner.nextLine();
        offer.addHotel(hotelName, hotelLocation, hotelRating, hotelPrice, sunSide, hasPool, hasLuggageStorage, hasCarSharing, hasKidsZone, userName, userPhone);
        // Добавление нового отеля в список стандартных отелей
        defaultHotels.add(new TourismFacility(offer.getNextId(), hotelName, hotelLocation, hotelRating, hotelPrice, sunSide, hasPool, hasLuggageStorage, hasCarSharing, hasKidsZone));
        System.out.println("Отель успешно добавлен.");
    }

    // Функция для поиска отеля
    private static void findHotel(TourismOffer offer, Scanner scanner) {
        System.out.println("Выберите параметр для поиска отеля:");
        System.out.println("1. По имени;");
        System.out.println("2. По региону;");
        System.out.println("3. По цене;");
        System.out.println("4. По наличию детской зоны;");
        int searchOption = scanner.nextInt();
        scanner.nextLine(); // Считываем символ новой строки

        switch (searchOption) {
            case 1:
                System.out.print("Введите имя отеля: ");
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
            case 4:
                System.out.print("Есть ли детская зона (true/false): ");
                boolean hasKidsZone = scanner.nextBoolean();
                List<TourismFacility> foundHotelsByKidsZone = offer.findHotelByKidsZone(hasKidsZone);
                printFoundHotels(foundHotelsByKidsZone);
                break;
            default:
                System.out.println("Неверный ввод.");
        }
    }

    // Функция для вывода найденных отелей
    private static void printFoundHotels(List<TourismFacility> hotels) {
        if (hotels.isEmpty()) {
            System.out.println("По вашему запросу отели не найдены.");
        } else {
            System.out.println("Найденные отели:");
            for (TourismFacility hotel : hotels) {
                System.out.println(hotel);
                if (hotel.getReviews().isEmpty()) {
                    System.out.println("Отзывы отсутствуют.");
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
        System.out.print("Введите название отеля, о котором хотите оставить отзыв: ");
        String hotelToReview = scanner.nextLine();
        System.out.print("Введите ваше имя: ");
        String userName = scanner.nextLine();
        System.out.print("Введите ваш номер телефона: ");
        String userPhone = scanner.nextLine();
        System.out.print("Напишите ваш отзыв: ");
        String review = scanner.nextLine();
        offer.writeReview(hotelToReview, userName, userPhone, review);
    }

    // Функция для загрузки стандартных отелей
    private static void loadDefaultHotels(TourismOffer offer, List<TourismFacility> defaultHotels) {
        Random rand = new Random();
        String[] sunSides = {"северная", "южная", "восточная", "западная"};
        defaultHotels.add(new TourismFacility(1, "ES", "Osh", 4.0, 80, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), true));
        defaultHotels.add(new TourismFacility(2, "Jannat", "Osh", 4.4, 120, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), false));
        defaultHotels.add(new TourismFacility(3, "INAI", "Bishkek", 4.6, 200, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), false));
        defaultHotels.add(new TourismFacility(4, "Relax", "Bishkek", 4.1, 150, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), true));

        for (TourismFacility hotel : defaultHotels) {
            offer.addHotel(hotel.getName(), hotel.getLocation(), hotel.getRating(), hotel.getPrice(), hotel.getSunSide(), hotel.isHasPool(), hotel.isHasLuggageStorage(), hotel.isHasCarSharing(), hotel.isHasKidsZone(), "Default Owner", "000-000-0000");
        }
    }

    // Функция для редактирования отеля
    private static void editHotel(TourismOffer offer, Scanner scanner) {
        System.out.println("Все отели:");
        printAllHotels(offer);
        System.out.print("Введите номер отеля, который вы хотите редактировать: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Считываем символ новой строки
        TourismFacility hotel = offer.getHotelById(hotelId);
        if (hotel == null) {
            System.out.println("Отель с таким номером не найден.");
            return;
        }
        System.out.println("Редактирование отеля: " + hotel.getName());
        System.out.print("Введите новое название (оставьте пустым для сохранения текущего значения): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            hotel.setName(newName);
        }
        System.out.print("Введите новое местоположение (оставьте пустым для сохранения текущего значения): ");
        String newLocation = scanner.nextLine();
        if (!newLocation.isEmpty()) {
            hotel.setLocation(newLocation);
        }
        System.out.print("Введите новый рейтинг (или -1 для сохранения текущего значения): ");
        double newRating = scanner.nextDouble();
        if (newRating != -1) {
            hotel.setRating(newRating);
        }
        System.out.print("Введите новую цену (или -1 для сохранения текущего значения): ");
        double newPrice = scanner.nextDouble();
        if (newPrice != -1) {
            hotel.setPrice(newPrice);
        }
        scanner.nextLine(); // Считываем символ новой строки
        System.out.print("Введите новую сторону от солнца (оставьте пустым для сохранения текущего значения): ");
        String newSunSide = scanner.nextLine();
        if (!newSunSide.isEmpty()) {
            hotel.setSunSide(newSunSide);
        }
        System.out.print("Есть ли бассейн (true/false, или оставьте пустым для сохранения текущего значения): ");
        String newHasPool = scanner.nextLine();
        if (!newHasPool.isEmpty()) {
            hotel.setHasPool(Boolean.parseBoolean(newHasPool));
        }
        System.out.print("Есть ли хранение багажа (true/false, или оставьте пустым для сохранения текущего значения): ");
        String newHasLuggageStorage = scanner.nextLine();
        if (!newHasLuggageStorage.isEmpty()) {
            hotel.setHasLuggageStorage(Boolean.parseBoolean(newHasLuggageStorage));
        }
        System.out.print("Есть ли каршеринг (true/false, или оставьте пустым для сохранения текущего значения): ");
        String newHasCarSharing = scanner.nextLine();
        if (!newHasCarSharing.isEmpty()) {
            hotel.setHasCarSharing(Boolean.parseBoolean(newHasCarSharing));
        }
        System.out.print("Есть ли детская зона (true/false, или оставьте пустым для сохранения текущего значения): ");
        String newHasKidsZone = scanner.nextLine();
        if (!newHasKidsZone.isEmpty()) {
            hotel.setHasKidsZone(Boolean.parseBoolean(newHasKidsZone));
        }
        System.out.println("Отель успешно отредактирован.");
    }

    // Функция для удаления отеля
    private static void removeHotel(TourismOffer offer, Scanner scanner) {
        System.out.println("Все отели:");
        printAllHotels(offer);
        System.out.print("Введите номер отеля, который вы хотите удалить: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Считываем символ новой строки
        if (offer.removeHotel(hotelId)) {
            System.out.println("Отель успешно удален.");
        } else {
            System.out.println("Отель с таким номером не найден.");
        }
    }

    // Функция для вывода всех отелей
    private static void printAllHotels(TourismOffer offer) {
        List<TourismFacility> allHotels = offer.getAllFacilities();
        if (allHotels.isEmpty()) {
            System.out.println("Отели отсутствуют.");
        } else {
            for (TourismFacility hotel : allHotels) {
                System.out.println(hotel);
            }
        }
    }

    // Функция для сохранения данных в файл
    private static void saveDataToFile(List<TourismFacility> defaultHotels) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hotels.dat"))) {
            oos.writeObject(defaultHotels);
            System.out.println("Данные успешно сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    // Класс, представляющий туристическое предложение
    static class TourismOffer {
        private String name;
        private List<TourismFacility> facilities;
        private int nextId;

        public TourismOffer(String name) {
            this.name = name;
            this.facilities = new ArrayList<>();
            this.nextId = 1; // Начинаем с ID 1
        }

        public void addHotel(String name, String location, double rating, double price, String sunSide, boolean hasPool, boolean hasLuggageStorage, boolean hasCarSharing, boolean hasKidsZone, String userName, String userPhone) {
            facilities.add(new TourismFacility(nextId++, name, location, rating, price, sunSide, hasPool, hasLuggageStorage, hasCarSharing, hasKidsZone));
        }

        public List<TourismFacility> findHotelByName(String name) {
            List<TourismFacility> foundHotels = new ArrayList<>();
            for (TourismFacility facility : facilities) {
                if (facility.getName().equalsIgnoreCase(name)) {
                    foundHotels.add(facility);
                }
            }
            return foundHotels;
        }

        public List<TourismFacility> chooseFacilities(String location) {
            List<TourismFacility> foundFacilities = new ArrayList<>();
            for (TourismFacility facility : facilities) {
                if (facility.getLocation().equalsIgnoreCase(location)) {
                    foundFacilities.add(facility);
                }
            }
            return foundFacilities;
        }

        public List<TourismFacility> findHotelByPrice(double maxPrice) {
            List<TourismFacility> foundFacilities = new ArrayList<>();
            for (TourismFacility facility : facilities) {
                if (facility.getPrice() <= maxPrice) {
                    foundFacilities.add(facility);
                }
            }
            return foundFacilities;
        }

        public List<TourismFacility> findHotelByKidsZone(boolean hasKidsZone) {
            List<TourismFacility> foundFacilities = new ArrayList<>();
            for (TourismFacility facility : facilities) {
                if (facility.isHasKidsZone() == hasKidsZone) {
                    foundFacilities.add(facility);
                }
            }
            return foundFacilities;
        }

        public void writeReview(String hotelName, String userName, String userPhone, String review) {
            for (TourismFacility facility : facilities) {
                if (facility.getName().equalsIgnoreCase(hotelName)) {
                    facility.addReview(userName + " (" + userPhone + "): " + review);
                    System.out.println("Ваш отзыв был успешно добавлен.");
                    return;
                }
            }
            System.out.println("Отель с таким именем не найден.");
        }

        public TourismFacility getHotelById(int id) {
            for (TourismFacility facility : facilities) {
                if (facility.getId() == id) {
                    return facility;
                }
            }
            return null;
        }

        public boolean removeHotel(int id) {
            Iterator<TourismFacility> iterator = facilities.iterator();
            while (iterator.hasNext()) {
                TourismFacility facility = iterator.next();
                if (facility.getId() == id) {
                    iterator.remove();
                    return true;
                }
            }
            return false;
        }

        public List<TourismFacility> getAllFacilities() {
            return new ArrayList<>(facilities);
        }

        public List<TourismFacility> getBestOffers() {
            List<TourismFacility> bestOffers = new ArrayList<>();
            for (TourismFacility facility : facilities) {
                if (facility.getRating() > 4) {
                    bestOffers.add(facility);
                }
            }
            return bestOffers;
        }

        public int getNextId() {
            return nextId;
        }
    }

    // Класс, представляющий туристическое заведение (отель)
    static class TourismFacility implements Serializable {
        private int id;
        private String name;
        private String location;
        private double rating;
        private double price;
        private String sunSide;
        private boolean hasPool;
        private boolean hasLuggageStorage;
        private boolean hasCarSharing;
        private boolean hasKidsZone;
        private List<String> reviews;

        public TourismFacility(int id, String name, String location, double rating, double price, String sunSide, boolean hasPool, boolean hasLuggageStorage, boolean hasCarSharing, boolean hasKidsZone) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.rating = rating;
            this.price = price;
            this.sunSide = sunSide;
            this.hasPool = hasPool;
            this.hasLuggageStorage = hasLuggageStorage;
            this.hasCarSharing = hasCarSharing;
            this.hasKidsZone = hasKidsZone;
            this.reviews = new ArrayList<>();
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getSunSide() {
            return sunSide;
        }

        public void setSunSide(String sunSide) {
            this.sunSide = sunSide;
        }

        public boolean isHasPool() {
            return hasPool;
        }

        public void setHasPool(boolean hasPool) {
            this.hasPool = hasPool;
        }

        public boolean isHasLuggageStorage() {
            return hasLuggageStorage;
        }

        public void setHasLuggageStorage(boolean hasLuggageStorage) {
            this.hasLuggageStorage = hasLuggageStorage;
        }

        public boolean isHasCarSharing() {
            return hasCarSharing;
        }

        public void setHasCarSharing(boolean hasCarSharing) {
            this.hasCarSharing = hasCarSharing;
        }

        public boolean isHasKidsZone() {
            return hasKidsZone;
        }

        public void setHasKidsZone(boolean hasKidsZone) {
            this.hasKidsZone = hasKidsZone;
        }

        public List<String> getReviews() {
            return reviews;
        }

        public void addReview(String review) {
            reviews.add(review);
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Имя: " + name + ", Местоположение: " + location + ", Рейтинг: " + rating + ", Цена: " + price + ", Сторона от солнца: " + sunSide + ", Бассейн: " + hasPool + ", Хранение багажа: " + hasLuggageStorage + ", Каршеринг: " + hasCarSharing + ", Детская зона: " + hasKidsZone;
        }
    }
}
