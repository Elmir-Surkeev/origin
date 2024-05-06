package institute_tasks._CourseWork;

import java.util.*;

public class TourismBookingSystem {
    public static void main(String[] args) {
        Booking booking = new Booking();

        // Добавление гостиниц
        Hotel hotel1 = new Hotel("Отель Мурас", "Центр", 100, 5000);
        Hotel hotel2 = new Hotel("Отель Солнце", "Побережье", 150, 6000);
        booking.addHotel(hotel1);
        booking.addHotel(hotel2);

        // Добавление кафе
        Cafe cafe1 = new Cafe("Мамин Пирог", "Парк", 50, 1000);
        Cafe cafe2 = new Cafe("Домашний угол", "Побережье", 30, 800);
        booking.addCafe(cafe1);
        booking.addCafe(cafe2);

        // Добавление ресторанов
        Restaurant restaurant1 = new Restaurant("Итальянский дворик", "Центр", 80, 2000);
        Restaurant restaurant2 = new Restaurant("Французский уголок", "Побережье", 60, 2500);
        booking.addRestaurant(restaurant1);
        booking.addRestaurant(restaurant2);

        // Сортировка гостиниц по цене за ночь
        booking.sortHotelsByPrice();

        // Выбор гостиницы
        Hotel chosenHotel = booking.chooseHotel("Центр", 100, 5500);
        if (chosenHotel != null) {
            System.out.println("Выбрана гостиница: " + chosenHotel);
        } else {
            System.out.println("Подходящей гостиницы не найдено.");
        }
    }
}

class Hotel {
    private String name;
    private String location;
    private int capacity;
    private double pricePerNight;

    public Hotel(String name, String location, int capacity, double pricePerNight) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Гостиница: " + name + ", Месторасположение: " + location + ", Вместимость: " + capacity + " человек, Цена за ночь: " + pricePerNight + " руб.";
    }
}

class Cafe {
    private String name;
    private String location;
    private int capacity;
    private double averagePrice;

    public Cafe(String name, String location, int capacity, double averagePrice) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.averagePrice = averagePrice;
    }

    @Override
    public String toString() {
        return "Семейное кафе: " + name + ", Месторасположение: " + location + ", Вместимость: " + capacity + " человек, Средняя цена: " + averagePrice + " руб.";
    }
}

class Restaurant {
    private String name;
    private String location;
    private int capacity;
    private double averagePrice;

    public Restaurant(String name, String location, int capacity, double averagePrice) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.averagePrice = averagePrice;
    }

    @Override
    public String toString() {
        return "Ресторан: " + name + ", Месторасположение: " + location + ", Вместимость: " + capacity + " человек, Средняя цена: " + averagePrice + " руб.";
    }
}

class Booking {
    private List<Hotel> hotels;
    private List<Cafe> cafes;
    private List<Restaurant> restaurants;

    public Booking() {
        hotels = new ArrayList<>();
        cafes = new ArrayList<>();
        restaurants = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void addCafe(Cafe cafe) {
        cafes.add(cafe);
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void sortHotelsByPrice() {
        hotels.sort(Comparator.comparingDouble(Hotel::getPricePerNight));
    }

    public Hotel chooseHotel(String location, int capacity, double maxPrice) {
        for (Hotel hotel : hotels) {
            if (hotel.getLocation().equals(location) && hotel.getCapacity() >= capacity && hotel.getPricePerNight() <= maxPrice) {
                return hotel;
            }
        }
        return null;
    }

}
