package CourseWork;

import java.io.*;
import java.util.*;

public class BookingSystem {
    private static final String USERS_FILE = "users.ser";
    private List<User> users = new ArrayList<>();
    private List<InfrastructureElement> elements = new ArrayList<>();

    public BookingSystem() {
        loadUsers();
        if (users.isEmpty()) {
            // Add default admin if no users exist
            users.add(new User("admin", "admin123", true));
            saveUsers();
        }
    }

    public void registerUser(String username, String password) {
        users.add(new User(username, password, false));
        saveUsers();
        System.out.println("User successfully registered!");
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addElement(InfrastructureElement element) {
        elements.add(element);
        System.out.println("Element successfully added!");
    }

    public void deleteElement(String name) {
        elements.removeIf(element -> element.getName().equals(name));
        System.out.println("Element successfully deleted!");
    }

    public List<InfrastructureElement> searchElement(String query) {
        List<InfrastructureElement> results = new ArrayList<>();
        for (InfrastructureElement element : elements) {
            if (element.getName().contains(query)) {
                results.add(element);
            }
        }
        return results;
    }

    public void leaveReview(String elementName, Review review) {
        for (InfrastructureElement element : elements) {
            if (element.getName().equals(elementName)) {
                element.setRating(review.getRating());
                System.out.println("Review successfully added!");
                return;
            }
        }
        System.out.println("Element not found!");
    }

    public List<InfrastructureElement> filterElements(String criterion, String value) {
        List<InfrastructureElement> filtered = new ArrayList<>();
        switch (criterion) {
            case "price":
                for (InfrastructureElement element : elements) {
                    if (element.getPrice() <= Double.parseDouble(value)) {
                        filtered.add(element);
                    }
                }
                break;
            case "name":
                for (InfrastructureElement element : elements) {
                    if (element.getName().contains(value)) {
                        filtered.add(element);
                    }
                }
                break;
            case "rating":
                for (InfrastructureElement element : elements) {
                    if (element.getRating() >= Integer.parseInt(value)) {
                        filtered.add(element);
                    }
                }
                break;
            case "region":
                for (InfrastructureElement element : elements) {
                    if (element.getLocation().contains(value)) {
                        filtered.add(element);
                    }
                }
                break;
        }
        return filtered;
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, this is okay if it's the first run
            System.out.println("User file not found, creating a new one.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingSystem system = new BookingSystem();

        System.out.println("Welcome to the Booking.Booking System!");
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();
                system.registerUser(username, password);
            } else if (choice == 2) {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();
                User user = system.loginUser(username, password);
                if (user == null) {
                    System.out.println("Invalid credentials!");
                    continue;
                }
                if (user.isAdmin()) {
                    while (true) {
                        System.out.println("1. Add establishment");
                        System.out.println("2. Delete establishment");
                        System.out.println("3. Search establishment");
                        System.out.println("4. Leave a review for establishment");
                        System.out.println("5. Logout");
                        int adminChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (adminChoice == 1) {
                            System.out.println("Enter type (hotel/restaurant/cafe):");
                            String type = scanner.nextLine();
                            System.out.println("Enter name:");
                            String name = scanner.nextLine();
                            System.out.println("Enter location:");
                            String location = scanner.nextLine();
                            System.out.println("Enter price:");
                            double price = scanner.nextDouble();
                            System.out.println("Enter rating:");
                            int rating = scanner.nextInt();
                            scanner.nextLine(); // consume newline

                            switch (type) {
                                case "hotel":
                                    system.addElement(new Hotel(name, location, price, rating));
                                    break;
                                case "restaurant":
                                    system.addElement(new Restaurant(name, location, price, rating));
                                    break;
                                case "cafe":
                                    system.addElement(new Cafe(name, location, price, rating));
                                    break;
                                default:
                                    System.out.println("Invalid type!");
                            }
                        } else if (adminChoice == 2) {
                            System.out.println("Enter the name of the element to delete:");
                            String name = scanner.nextLine();
                            system.deleteElement(name);
                        } else if (adminChoice == 3) {
                            System.out.println("Enter search query:");
                            String query = scanner.nextLine();
                            List<InfrastructureElement> results = system.searchElement(query);
                            for (InfrastructureElement element : results) {
                                System.out.println(element.getName() + " - " + element.getLocation() + " - " + element.getPrice() + " - " + element.getRating());
                            }
                        } else if (adminChoice == 4) {
                            System.out.println("Enter the name of the element:");
                            String elementName = scanner.nextLine();
                            System.out.println("Enter your name:");
                            String name = scanner.nextLine();
                            System.out.println("Enter your phone number:");
                            String phone = scanner.nextLine();
                            System.out.println("Enter your review:");
                            String reviewText = scanner.nextLine();
                            System.out.println("Enter rating (1-5):");
                            int rating = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            system.leaveReview(elementName, new Review(name, phone, reviewText, rating));
                        } else if (adminChoice == 5) {
                            break;
                        }
                    }
                } else {
                    while (true) {
                        System.out.println("1. Search establishment");
                        System.out.println("2. Leave a review");
                        System.out.println("3. Logout");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (userChoice == 1) {
                            System.out.println("Enter search query:");
                            String query = scanner.nextLine();
                            List<InfrastructureElement> results = system.searchElement(query);
                            for (InfrastructureElement element : results) {
                                System.out.println(element.getName() + " - " + element.getLocation() + " - " + element.getPrice() + " - " + element.getRating());
                            }
                            System.out.println("Enter filter criterion (price/name/rating/region):");
                            String criterion = scanner.nextLine();
                            System.out.println("Enter filter value:");
                            String value = scanner.nextLine();
                            List<InfrastructureElement> filtered = system.filterElements(criterion, value);
                            for (InfrastructureElement element : filtered) {
                                System.out.println(element.getName() + " - " + element.getLocation() + " - " + element.getPrice() + " - " + element.getRating());
                            }
                        } else if (userChoice == 2) {
                            System.out.println("Enter the name of the element:");
                            String elementName = scanner.nextLine();
                            System.out.println("Enter your name:");
                            String name = scanner.nextLine();
                            System.out.println("Enter your phone number:");
                            String phone = scanner.nextLine();
                            System.out.println("Enter your review:");
                            String reviewText = scanner.nextLine();
                            System.out.println("Enter rating (1-5):");
                            int rating = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            system.leaveReview(elementName, new Review(name, phone, reviewText, rating));
                        } else if (userChoice == 3) {
                            break;
                        }
                    }
                }
            } else if (choice == 3) {
                break;
            }
        }
        scanner.close();
    }
}
