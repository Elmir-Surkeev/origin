package institute_tasks._Course_Work2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// User class
class User {
    private String username;
    private String password;
    private boolean isAdmin;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

// Review class
class Review {
    private String username;
    private String comment;
    private int rating;

    public Review(String username, String comment, int rating) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }
}

// Place abstract class
abstract class Place {
    private String name;
    private String location;
    private double price;
    private double rating;
    private List<Review> reviews;

    public Place(String name, String location, double price, double rating) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.rating = rating;
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
        updateRating();
    }

    private void updateRating() {
        double total = 0;
        for (Review review : reviews) {
            total += review.getRating();
        }
        this.rating = total / reviews.size();
    }
}

// Hotel class
class Hotel extends Place {
    public Hotel(String name, String location, double price, double rating) {
        super(name, location, price, rating);
    }
}

// Restaurant class
class Restaurant extends Place {
    public Restaurant(String name, String location, double price, double rating) {
        super(name, location, price, rating);
    }
}

// Cafe class
class Cafe extends Place {
    public Cafe(String name, String location, double price, double rating) {
        super(name, location, price, rating);
    }
}

// MainApp class
public class MainApp extends JFrame {
    private List<User> users;
    private List<Hotel> hotels;
    private List<Restaurant> restaurants;
    private List<Cafe> cafes;
    private User loggedInUser;

    public MainApp() {
        this.users = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.restaurants = new ArrayList<>();
        this.cafes = new ArrayList<>();
        this.loggedInUser = null;

        // Adding default admin
        users.add(new User("admin", "admin123", true));

        setTitle("Hospitality Booking.Booking System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel loginPanel = createLoginPanel();
        add(loginPanel);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                login(username, password);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                register(username, password);
            }
        });

        return panel;
    }

    private void login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser == null) {
            JOptionPane.showMessageDialog(this, "Invalid login. Please try again.");
            return;
        }

        if (loggedInUser.isAdmin()) {
            JPanel adminPanel = createAdminPanel();
            setContentPane(adminPanel);
        } else {
            JPanel userPanel = createUserPanel();
            setContentPane(userPanel);
        }
        revalidate();
    }

    private void register(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose another one.");
                return;
            }
        }

        users.add(new User(username, password, false));
        JOptionPane.showMessageDialog(this, "User registered successfully.");
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JButton addPlaceButton = new JButton("Add Place");
        JButton deletePlaceButton = new JButton("Delete Place");
        JButton editPlaceButton = new JButton("Edit Place");
        JButton viewAllButton = new JButton("View All");

        panel.add(addPlaceButton);
        panel.add(deletePlaceButton);
        panel.add(editPlaceButton);
        panel.add(viewAllButton);

        addPlaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Hotel", "Restaurant", "Cafe"};
                int choice = JOptionPane.showOptionDialog(MainApp.this, "Select type to add", "Add",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (choice == 0) {
                    addHotel();
                } else if (choice == 1) {
                    addRestaurant();
                } else if (choice == 2) {
                    addCafe();
                }
            }
        });

        deletePlaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Hotel", "Restaurant", "Cafe"};
                int choice = JOptionPane.showOptionDialog(MainApp.this, "Select type to delete", "Delete",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (choice == 0) {
                    deleteHotel();
                } else if (choice == 1) {
                    deleteRestaurant();
                } else if (choice == 2) {
                    deleteCafe();
                }
            }
        });

        editPlaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Hotel", "Restaurant", "Cafe"};
                int choice = JOptionPane.showOptionDialog(MainApp.this, "Select type to edit", "Edit",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (choice == 0) {
                    editHotel();
                } else if (choice == 1) {
                    editRestaurant();
                } else if (choice == 2) {
                    editCafe();
                }
            }
        });

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAll();
            }
        });

        return panel;
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel filtersPanel = new JPanel();
        JLabel priceLabel = new JLabel("Max Price:");
        JTextField priceField = new JTextField(10);
        JLabel regionLabel = new JLabel("Region:");
        JTextField regionField = new JTextField(10);
        JLabel ratingLabel = new JLabel("Min Rating:");
        JTextField ratingField = new JTextField(10);
        JButton filterButton = new JButton("Filter");

        filtersPanel.add(priceLabel);
        filtersPanel.add(priceField);
        filtersPanel.add(regionLabel);
        filtersPanel.add(regionField);
        filtersPanel.add(ratingLabel);
        filtersPanel.add(ratingField);
        filtersPanel.add(filterButton);

        panel.add(filtersPanel, BorderLayout.NORTH);

        JTable resultsTable = new JTable();
        panel.add(new JScrollPane(resultsTable), BorderLayout.CENTER);

        JPanel reviewPanel = new JPanel();
        JLabel reviewLabel = new JLabel("Review:");
        JTextField reviewField = new JTextField(30);
        JLabel ratingReviewLabel = new JLabel("Rating:");
        JTextField ratingReviewField = new JTextField(2);
        JButton submitReviewButton = new JButton("Submit Review");

        reviewPanel.add(reviewLabel);
        reviewPanel.add(reviewField);
        reviewPanel.add(ratingReviewLabel);
        reviewPanel.add(ratingReviewField);
        reviewPanel.add(submitReviewButton);

        panel.add(reviewPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void addHotel() {
        // Логика добавления отеля
        JOptionPane.showMessageDialog(this, "Feature not implemented yet.");
    }

    private void addRestaurant() {
        // Логика добавления ресторана
        JOptionPane.showMessageDialog(this, "Feature not implemented yet.");
    }

    private void addCafe() {
        // Логика добавления кафе
        JOptionPane.showMessageDialog(this, "Feature not implemented yet.");
    }

    private void deleteHotel() {
        // Логика удаления отеля
        JOptionPane.showMessageDialog(this, "Feature not implemented yet.");
    }

    private void deleteRestaurant() {
        // Логика удаления ресторана
        JOptionPane.showMessageDialog(this, "Feature not implemented yet.");
    }

    private void deleteCafe() {
        // Логика удаления кафе
        JOptionPane.showMessageDialog(this, "Feature not implemented yet.");
    }

    private void editHotel() {
        // Логика редактирования отеля
        JOptionPane.showMessageDialog(this, "Feature not implemented yet.");
    }

    private void editRestaurant() {
        // Логика редактирования ресторана
        JOptionPane.showMessageDialog(this, "Feature not implemented yet.");
    }

    private void editCafe() {
        // Логика редактирования кафе
        JOptionPane.showMessageDialog(this, "Feature not implemented yet.");
    }

    private void viewAll() {
        // Логика просмотра всех объектов
        StringBuilder allData = new StringBuilder();
        allData.append("Hotels:\n");
        for (Hotel hotel : hotels) {
            allData.append(hotel.getName()).append(", ").append(hotel.getLocation()).append(", ").append(hotel.getPrice()).append(", ").append(hotel.getRating()).append("\n");
        }
        allData.append("\nRestaurants:\n");
        for (Restaurant restaurant : restaurants) {
            allData.append(restaurant.getName()).append(", ").append(restaurant.getLocation()).append(", ").append(restaurant.getPrice()).append(", ").append(restaurant.getRating()).append("\n");
        }
        allData.append("\nCafes:\n");
        for (Cafe cafe : cafes) {
            allData.append(cafe.getName()).append(", ").append(cafe.getLocation()).append(", ").append(cafe.getPrice()).append(", ").append(cafe.getRating()).append("\n");
        }
        JOptionPane.showMessageDialog(this, allData.toString(), "All Data", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }
}
