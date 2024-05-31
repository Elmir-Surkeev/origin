package _CourseWork;

//import institute_tasks._Course_Work2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

// Booking.Booking process in the hospitality industry.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TourismOffer offer = new TourismOffer("Hotel"); // Creating an offer
        List<TourismFacility> defaultHotels = new ArrayList<>(); // List to store default hotels

        loadDefaultHotels(offer, defaultHotels); // Loading default hotels

        System.out.println("Choose the working mode:");
        System.out.println("1. Guest");
        System.out.println("2. Admin");
        int mode = scanner.nextInt();
        scanner.nextLine(); // Read the newline character

        switch (mode) {
            case 1:
                viewMode(offer, defaultHotels, scanner);
                break;
            case 2:
                editMode(offer, defaultHotels, scanner);
                break;
            default:
                System.out.println("Invalid input. Please try again.");
        }
    }
//\\
//    private JPanel createLoginPanel() {
//        JPanel panel = new JPanel(new GridLayout(3, 2));
//        JLabel usernameLabel = new JLabel("Username:");
//        JTextField usernameField = new JTextField();
//        JLabel passwordLabel = new JLabel("Password:");
//        JPasswordField passwordField = new JPasswordField();
//        JButton loginButton = new JButton("Login");
//        JButton registerButton = new JButton("Register");
//
//        panel.add(usernameLabel);
//        panel.add(usernameField);
//        panel.add(passwordLabel);
//        panel.add(passwordField);
//        panel.add(loginButton);
//        panel.add(registerButton);
//
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                String password = new String(passwordField.getPassword());
//                login(username, password);
//            }
//        });
//
//        registerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                String password = new String(passwordField.getPassword());
//                register(username, password);
//            }
//        });
//
//        return panel;
//    }
//
//    private void login(String username, String password) {
//        for (User user : users) {
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//                loggedInUser = user;
//                break;
//            }
//        }
//
//        if (loggedInUser == null) {
//            JOptionPane.showMessageDialog(this, "Invalid login. Please try again.");
//            return;
//        }
//
//        if (loggedInUser.isAdmin()) {
//            JPanel adminPanel = createAdminPanel();
//            setContentPane(adminPanel);
//        } else {
//            JPanel userPanel = createUserPanel();
//            setContentPane(userPanel);
//        }
//        revalidate();
//    }
//
//    private void register(String username, String password) {
//        for (User user : users) {
//            if (user.getUsername().equals(username)) {
//                JOptionPane.showMessageDialog(this, "Username already exists. Please choose another one.");
//                return;
//            }
//        }
//
//        users.add(new User(username, password, false));
//        JOptionPane.showMessageDialog(this, "User registered successfully.");
//    }
//    \\
    private static void viewMode(TourismOffer offer, List<TourismFacility> defaultHotels, Scanner scanner) {
        while (true) {
            System.out.println("0. View the best offers;");
            System.out.println("1. Find a hotel;");
            System.out.println("2. Leave a review;");
            System.out.println("3. View all hotels;");
            System.out.println("4. Exit.");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Read the newline character

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
                    System.out.println("All hotels:");
                    printAllHotels(offer);
                    break;
                case 4:
                    System.out.println("Program terminated.");
                    saveDataToFile(defaultHotels); // Saving default hotels before exiting
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void viewBestOffers(TourismOffer offer) {
        List<TourismFacility> bestOffers = offer.getBestOffers();
        if (bestOffers.isEmpty()) {
            System.out.println("No best offers at the moment.");
        } else {
            System.out.println("Best offers this moment:");
            for (TourismFacility hotel : bestOffers) {
                System.out.println(hotel);
            }
        }
    }

    private static void editMode(TourismOffer offer, List<TourismFacility> defaultHotels, Scanner scanner) {
        while (true) {
            System.out.println("1. Add a hotel;");
            System.out.println("2. Edit a hotel;");
            System.out.println("3. Remove a hotel;");
            System.out.println("4. View all hotels;");
            System.out.println("5. Exit.");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Read the newline character

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
        System.out.print("Enter the hotel name: ");
        String hotelName = scanner.nextLine();
        System.out.print("Enter the hotel location: ");
        String hotelLocation = scanner.nextLine();
        System.out.print("Enter the hotel rating: ");
        double hotelRating = scanner.nextDouble();
        System.out.print("Enter the price per night: ");
        double hotelPrice = scanner.nextDouble();
        scanner.nextLine(); // Read the newline character
        System.out.print("Enter the sun side: ");
        String sunSide = scanner.nextLine();
        System.out.print("Does it have a pool (true/false): ");
        boolean hasPool = scanner.nextBoolean();
        System.out.print("Does it have luggage storage (true/false): ");
        boolean hasLuggageStorage = scanner.nextBoolean();
        System.out.print("Does it have car sharing (true/false): ");
        boolean hasCarSharing = scanner.nextBoolean();
        System.out.print("Does it have a kids zone (true/false): ");
        boolean hasKidsZone = scanner.nextBoolean();
        scanner.nextLine(); // Read the newline character
        System.out.print("Enter the owner's name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter the administrator's phone number: ");
        String userPhone = scanner.nextLine();
        offer.addHotel(hotelName, hotelLocation, hotelRating, hotelPrice, sunSide, hasPool, hasLuggageStorage, hasCarSharing, hasKidsZone, userName, userPhone);
        // Adding a new hotel to the list of default hotels
        defaultHotels.add(new TourismFacility(offer.getNextId(), hotelName, hotelLocation, hotelRating, hotelPrice, sunSide, hasPool, hasLuggageStorage, hasCarSharing, hasKidsZone));
        System.out.println("Hotel successfully added.");
    }



    // Function to find a hotel
    private static void findHotel(TourismOffer offer, Scanner scanner) {
        System.out.println("Choose the parameter to search for a hotel:");
        System.out.println("1. By name;");
        System.out.println("2. By region;");
        System.out.println("3. By price;");
        System.out.println("4. By have free an apartments;");
        int searchOption = scanner.nextInt();
        scanner.nextLine(); // Read the newline character

        switch (searchOption) {
            case 1:
                System.out.print("Enter the hotel name: ");
                String hotelName = scanner.nextLine().toLowerCase();
                List<TourismFacility> hotelsByName = offer.findHotelByName(hotelName);
                printFoundHotels(hotelsByName);
                break;
            case 2:
                System.out.print("Enter the region: ");
                String location = scanner.nextLine();
                List<TourismFacility> foundHotelsByLocation = offer.chooseFacilities(location);
                printFoundHotels(foundHotelsByLocation);
                break;
            case 3:
                System.out.print("Enter the maximum price: ");
                double maxPrice = scanner.nextDouble();
                List<TourismFacility> foundHotelsByPrice = offer.findHotelByPrice(maxPrice);
                printFoundHotels(foundHotelsByPrice);
                break;
            case 4:
                System.out.print("Does it have a kids zone (true/false): ");
                boolean hasKidsZone = scanner.nextBoolean();
                List<TourismFacility> foundHotelsByKidsZone = offer.findHotelByKidsZone(hasKidsZone);
                printFoundHotels(foundHotelsByKidsZone);
                break;


            default:
                System.out.println("false enter");
        }
    }

   ////////////////////////////////////////// // Функция для вывода найденных отелей
   private static void printFoundHotels(List<TourismFacility> hotels) {
       if (hotels.isEmpty()) {
           System.out.println("No hotels found for your query.");
       } else {
           System.out.println("Found hotels:");
           for (TourismFacility hotel : hotels) {
               System.out.println(hotel);
               if (hotel.getReviews().isEmpty()) {
                   System.out.println("No reviews available.");
               } else {
                   System.out.println("Reviews:");
                   for (String review : hotel.getReviews()) {
                       System.out.println("- " + review);
                   }
               }
           }
       }
   }

    // Function for writing a review about a hotel
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

    // Function for loading default hotels
    private static void loadDefaultHotels(TourismOffer offer, List<TourismFacility> defaultHotels) {
        Random rand = new Random();
        String[] sunSides = {"north", "south", "east", "west"};
        defaultHotels.add(new TourismFacility(1, "ES", "Osh", 4.0, 80, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), true));
        defaultHotels.add(new TourismFacility(2, "Jannat", "Osh", 4.4, 120, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), false));
        defaultHotels.add(new TourismFacility(3, "INAI", "Bishkek", 4.6, 200, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), false));
        defaultHotels.add(new TourismFacility(5, "Rixos", "Bishkek", 4.5, 180, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), true));
        defaultHotels.add(new TourismFacility(6, "Hyatt Regency", "Bishkek", 4.7, 220, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), true));
        defaultHotels.add(new TourismFacility(7, "Plaza", "Bishkek", 4.3, 160, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), false));
        defaultHotels.add(new TourismFacility(8, "Soluxe", "Bishkek", 4.2, 140, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), false));
        defaultHotels.add(new TourismFacility(9, "Navat", "Osh", 2.6, 140, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), true));
        defaultHotels.add(new TourismFacility(10, "Nur", "Osh", 4.3, 150, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), false));
        defaultHotels.add(new TourismFacility(11, "Sultan", "Jalal-Abad", 4.0, 100, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), false));
        defaultHotels.add(new TourismFacility(12, "Uluu", "Issyk-Kul", 4.4, 170, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), true));
        defaultHotels.add(new TourismFacility(13, "Dostuk", "Talas", 4.1, 130, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), false));
        defaultHotels.add(new TourismFacility(14, "Altyn-Kol", "Naryn", 3.2, 160, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), true));
        defaultHotels.add(new TourismFacility(4, "Relax", "Bishkek", 4.1, 150, sunSides[rand.nextInt(sunSides.length)], rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), true));

        for (TourismFacility hotel : defaultHotels) {
            offer.addHotel(hotel.getName(), hotel.getLocation(), hotel.getRating(), hotel.getPrice(), hotel.getSunSide(), hotel.isHasPool(), hotel.isHasLuggageStorage(), hotel.isHasCarSharing(), hotel.isHasKidsZone(), "Default Owner", "000-000-0000");
        }
    }

    // Function for editing a hotel
    private static void editHotel(TourismOffer offer, Scanner scanner) {
        System.out.println("All hotels:");
        printAllHotels(offer);
        System.out.print("Enter the number of the hotel you want to edit: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        TourismFacility hotel = offer.getHotelById(hotelId);
        if (hotel == null) {
            System.out.println("Hotel with this number not found.");
            return;
        }
        System.out.println("Editing hotel: " + hotel.getName());
        System.out.print("Enter the new name (leave blank to keep current value): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            hotel.setName(newName);
        }
        System.out.print("Enter the new location (leave blank to keep current value): ");
        String newLocation = scanner.nextLine();
        if (!newLocation.isEmpty()) {
            hotel.setLocation(newLocation);
        }
        System.out.print("Enter the new rating (or -1 to keep current value): ");
        double newRating = scanner.nextDouble();
        if (newRating != -1) {
            hotel.setRating(newRating);
        }
        System.out.print("Enter the new price (or -1 to keep current value): ");
        double newPrice = scanner.nextDouble();
        if (newPrice != -1) {
            hotel.setPrice(newPrice);
        }
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the new sun side (leave blank to keep current value): ");
        String newSunSide = scanner.nextLine();
        if (!newSunSide.isEmpty()) {
            hotel.setSunSide(newSunSide);
        }
        System.out.print("Does it have a pool (true/false, or leave blank to keep current value): ");
        String newHasPool = scanner.nextLine();
        if (!newHasPool.isEmpty()) {
            hotel.setHasPool(Boolean.parseBoolean(newHasPool));
        }
        System.out.print("Does it have luggage storage (true/false, or leave blank to keep current value): ");
        String newHasLuggageStorage = scanner.nextLine();
        if (!newHasLuggageStorage.isEmpty()) {
            hotel.setHasLuggageStorage(Boolean.parseBoolean(newHasLuggageStorage));
        }
        System.out.print("Does it have car sharing (true/false, or leave blank to keep current value): ");
        String newHasCarSharing = scanner.nextLine();
        if (!newHasCarSharing.isEmpty()) {
            hotel.setHasCarSharing(Boolean.parseBoolean(newHasCarSharing));
        }
        System.out.print("Does it have a free an avartments (true/false, or leave blank to keep current value): ");
        String newHasKidsZone = scanner.nextLine();
        if (!newHasKidsZone.isEmpty()) {
            hotel.setHasKidsZone(Boolean.parseBoolean(newHasKidsZone));
        }
        System.out.println("Hotel successfully edited.");
    }

    // Function for removing a hotel
    private static void removeHotel(TourismOffer offer, Scanner scanner) {
        System.out.println("All hotels:");
        printAllHotels(offer);
        System.out.print("Enter the number of the hotel you want to remove: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (offer.removeHotel(hotelId)) {
            System.out.println("Hotel successfully removed.");
        } else {
            System.out.println("Hotel with this number not found.");
        }
    }

    // Function for printing all hotels
    private static void printAllHotels(TourismOffer offer) {
        List<TourismFacility> allHotels = offer.getAllFacilities();
        if (allHotels.isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            for (TourismFacility hotel : allHotels) {
                System.out.println(hotel);
            }
        }
    }

    // Function for saving data to a file
    private static void saveDataToFile(List<TourismFacility> defaultHotels) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hotels.dat"))) {
            oos.writeObject(defaultHotels);
            System.out.println("Data successfully saved.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    static class TourismOffer {
        private String name;
        private List<TourismFacility> facilities;
        private int nextId;

        public TourismOffer(String name) {
            this.name = name;
            this.facilities = new ArrayList<>();
            this.nextId = 1; // Start with ID 1
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
                    System.out.println("Your review has been successfully added.");
                    return;
                }
            }
            System.out.println("Hotel with this name not found.");
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
                if (facility.getRating() > 4.5) {
                    bestOffers.add(facility);
                }
            }
            return bestOffers;
        }

        public int getNextId() {
            return nextId;
        }
    }

    // Class representing a tourism facility (hotel)
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
            return "ID: " + id + ", Name: " + name + ", Location: " + location + ", Rating: " + rating + ", Price: " + price + ", Sun Side: " + sunSide + ", Pool: " + hasPool + ", Luggage Storage: " + hasLuggageStorage + ", Car Sharing: " + hasCarSharing + ", FREE PLACES > 3 apartment " + hasKidsZone;
        }
    }
}

    ////////////////////////////////////////////////////////////////
