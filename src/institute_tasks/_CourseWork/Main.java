package institute_tasks._CourseWork;

import java.io.*;
import java.util.*;

//Booking process in the hospitality industry.
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TourismOffer offer = new TourismOffer("Hotel"); // Creating an offer
        List<TourismFacility> defaultHotels = new ArrayList<>(); // List to store default hotels

        loadDefaultHotels(offer, defaultHotels); // Loading default hotels

        while (true) {
            System.out.println("(function for entries from the administrator role) Enter 1 to add a hotel;");
            System.out.println("(function for entries from the guest role) Enter 2 to find a hotel;");
            System.out.println("Guest role Enter 3 to write a review about a hotel;");
            System.out.println("Guest role Enter 4 to display all hotels;");
            System.out.println("Enter 0 to exit.");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addHotel(offer, scanner, defaultHotels); // Passing defaultHotels to add a new hotel to it
                    break;
                case 2:
                    findHotel(offer, scanner);
                    break;
                case 3:
                    writeReview(offer, scanner);
                    break;
                case 4:
                    System.out.println("All hotels:");
                    System.out.println(offer.getAllHotelsAsString());
                    break;
                case 0:
                    System.out.println("Program terminated.");
                    saveDataToFile(defaultHotels); // Saving default hotels before exiting
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // Function to add a hotel
    private static void addHotel(TourismOffer offer, Scanner scanner, List<TourismFacility> defaultHotels) {
        System.out.print("Enter hotel name: ");
        String hotelName = scanner.nextLine();
        System.out.print("Enter hotel location: ");
        String hotelLocation = scanner.nextLine();
        System.out.print("Enter hotel rating: ");
        double hotelRating = scanner.nextDouble();
        System.out.print("Enter price per night: ");
        double hotelPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter owner's name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter administrator's phone number: ");
        String userPhone = scanner.nextLine();
        offer.addHotel(hotelName, hotelLocation, hotelRating, hotelPrice, userName, userPhone);
        // Adding the new hotel to the defaultHotels list
        defaultHotels.add(new TourismFacility(hotelName, hotelLocation, hotelRating, hotelPrice));
        System.out.println("Hotel successfully added.");
    }

    // Function to find a hotel
    private static void findHotel(TourismOffer offer, Scanner scanner) {
        System.out.println("Choose a parameter to find a hotel:");
        System.out.println("1. By name;");
        System.out.println("2. By region;");
        System.out.println("3. By price;");
        int searchOption = scanner.nextInt();
        scanner.nextLine(); // Clear buffer after reading the number

        switch (searchOption) {
            case 1:
                System.out.print("Enter hotel name: ");
                String hotelName = scanner.nextLine().toLowerCase();
                List<TourismFacility> hotelsByName = offer.findHotelByName(hotelName);
                printFoundHotels(hotelsByName);
                break;
            case 2:
                System.out.print("Enter region: ");
                String location = scanner.nextLine();
                List<TourismFacility> foundHotelsByLocation = offer.chooseFacilities(location);
                printFoundHotels(foundHotelsByLocation);
                break;
            case 3:
                System.out.print("Enter maximum price: ");
                double maxPrice = scanner.nextDouble();
                List<TourismFacility> foundHotelsByPrice = offer.findHotelByPrice(maxPrice);
                printFoundHotels(foundHotelsByPrice);
                break;
            default:
                System.out.println("Invalid input.");
        }
    }

    // Function to print found hotels
    private static void printFoundHotels(List<TourismFacility> hotels) {
        if (hotels.isEmpty()) {
            System.out.println("No hotels found for your query.");
        } else {
            System.out.println("Found hotels:");
            for (TourismFacility hotel : hotels) {
                System.out.println(hotel);
                if (hotel.getReviews().isEmpty()) {
                    System.out.println("No reviews yet.");
                } else {
                    System.out.println("Reviews:");
                    for (String review : hotel.getReviews()) {
                        System.out.println("- " + review);
                    }
                }
            }
        }
    }

    // Function to write a review about a hotel
    private static void writeReview(TourismOffer offer, Scanner scanner) {
        System.out.print("Enter the name of the hotel you want to review: ");
        String hotelToReview = scanner.nextLine();
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String userPhone = scanner.nextLine();
        System.out.print("Write your review: ");
        String review = scanner.nextLine();
        offer.writeReview(hotelToReview, userName, userPhone, review);
    }

    // Function to load default hotels
    private static void loadDefaultHotels(TourismOffer offer, List<TourismFacility> defaultHotels) {
        // In this function, you can load data about previously added hotels from a file or another data source
        // Here, for example, we simply add a few default hotels
        defaultHotels.add(new TourismFacility("Miami", "Osh", 4.0, 80));
        defaultHotels.add(new TourismFacility("Jannat", "Manasa 64", 4.5, 120));
        defaultHotels.add(new TourismFacility("Relax", "Bishkek", 4.2, 100));
        // Adding these hotels to the offer as well
        for (TourismFacility hotel : defaultHotels) {
            offer.addHotel(hotel.getName(), hotel.getLocation(), hotel.getRating(), hotel.getPrice(), "Admin", "0312 012 012");
        }
    }

    // Function to save default hotels to a file
    private static void saveDataToFile(List<TourismFacility> defaultHotels) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("defaultHotels.dat"))) {
            oos.writeObject(defaultHotels);
            System.out.println("Default hotels saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving default hotels.");
        }
    }
}

class TourismFacility implements Serializable {
    private String name;
    private String location;
    private double rating;
    private double price;
    private List<String> reviews;

    // Constructor
    public TourismFacility(String name, String location, double rating, double price) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.price = price;
        this.reviews = new ArrayList<>();
    }

    // Getters and setters
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

    // Method to add a review
    public void addReview(String review) {
        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Hotel: " + name + ", Location: " + location + ", Rating: " + rating + ", Price per night: " + price;
    }
}

class TourismOffer {
    private String type;
    private List<TourismFacility> facilities;

    // Constructor
    public TourismOffer(String type) {
        this.type = type;
        this.facilities = new ArrayList<>();
    }

    // Function to add a hotel
    public void addHotel(String name, String location, double rating, double price, String userName, String userPhone) {
        TourismFacility hotel = new TourismFacility(name, location, rating, price);
        hotel.addReview("Added by administrator: " + userName + ", Phone: " + userPhone);
        facilities.add(hotel);
    }

    // Function to find a hotel by name
    public List<TourismFacility> findHotelByName(String name) {
        List<TourismFacility> foundHotels = new ArrayList<>();
        for (TourismFacility facility : facilities) {
            if (facility.getName().toLowerCase().contains(name.toLowerCase())) {
                foundHotels.add(facility);
            }
        }
        return foundHotels;
    }

    // Function to choose facilities by location
    public List<TourismFacility> chooseFacilities(String location) {
        List<TourismFacility> foundFacilities = new ArrayList<>();
        for (TourismFacility facility : facilities) {
            if (facility.getLocation().equalsIgnoreCase(location)) {
                foundFacilities.add(facility);
            }
        }
        return foundFacilities;
    }

    // Function to find a hotel by price
    public List<TourismFacility> findHotelByPrice(double maxPrice) {
        List<TourismFacility> foundHotels = new ArrayList<>();
        for (TourismFacility facility : facilities) {
            if (facility.getPrice() <= maxPrice) {
                foundHotels.add(facility);
            }
        }
        return foundHotels;
    }

    // Function to write a review about a hotel
    public void writeReview(String hotelName, String userName, String userPhone, String review) {
        for (TourismFacility facility : facilities) {
            if (facility.getName().equalsIgnoreCase(hotelName)) {
                facility.addReview("Review by user " + userName + ", Phone: " + userPhone + ": " + review);
                System.out.println("Review added successfully.");
                return;
            }
        }
        System.out.println("Hotel not found.");
    }

    // Function to get all hotels as a string
    public String getAllHotelsAsString() {
        StringBuilder result = new StringBuilder();
        for (TourismFacility facility : facilities) {
            result.append(facility).append("\n");
        }
        return result.toString();
    }
}

