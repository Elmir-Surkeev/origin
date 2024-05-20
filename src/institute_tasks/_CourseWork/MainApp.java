package institute_tasks._CourseWork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<User> users;
    private List<Hotel> hotels;
    private List<Restaurant> restaurants;
    private List<Cafe> cafes;
    private User loggedInUser;

    public MainApp() {
        setTitle("Hospitality Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        users = new ArrayList<>();
        hotels = new ArrayList<>();
        restaurants = new ArrayList<>();
        cafes = new ArrayList<>();

        // Adding a default admin user
        users.add(new User("admin", "admin", true));

        JPanel loginPanel = createLoginPanel();
        JPanel adminPanel = createAdminPanel();
        JPanel userPanel = createUserPanel();

        mainPanel.add(loginPanel, "login");
        mainPanel.add(adminPanel, "admin");
        mainPanel.add(userPanel, "user");

        add(mainPanel);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                for (User user : users) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        loggedInUser = user;
                        if (user.isAdmin()) {
                            cardLayout.show(mainPanel, "admin");
                        } else {
                            cardLayout.show(mainPanel, "user");
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(MainApp.this, "Invalid username or password");
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel();

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton editButton = new JButton("Edit");
        JButton viewAllButton = new JButton("View All");

        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(viewAllButton);

        panel.add(buttonsPanel, BorderLayout.NORTH);

        // Add listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to add a new item (hotel, restaurant, or cafe)
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to delete an item
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

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to edit an item
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
        JTextField priceField = new JTextField();
        JLabel regionLabel = new JLabel("Region:");
        JTextField regionField = new JTextField();
        JLabel ratingLabel = new JLabel("Min Rating:");
        JTextField ratingField = new JTextField();
        JButton filterButton = new JButton("Filter");

        filtersPanel.add(priceLabel);
        filtersPanel.add(priceField);
        filtersPanel.add(regionLabel);
        filtersPanel.add(regionField);
        filtersPanel.add(ratingLabel);
        filtersPanel.add(ratingField);
        filtersPanel.add(filterButton);

        panel.add(filtersPanel, BorderLayout.NORTH);

        // Add table to display results
        JTable resultsTable = new JTable();
        panel.add(new JScrollPane(resultsTable), BorderLayout.CENTER);

        JPanel reviewPanel = new JPanel();
        JLabel reviewLabel = new JLabel("Review:");
        JTextField reviewField = new JTextField();
        JLabel ratingReviewLabel = new JLabel("Rating:");
        JTextField ratingReviewField = new JTextField();
        JButton submitReviewButton = new JButton("Submit Review");

        reviewPanel.add(reviewLabel);
        reviewPanel.add(reviewField);
        reviewPanel.add(ratingReviewLabel);
        reviewPanel.add(ratingReviewField);
        reviewPanel.add(submitReviewButton);

        panel.add(reviewPanel, BorderLayout.SOUTH);

        // Add filter button listener
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to filter data
                double maxPrice = Double.parseDouble(priceField.getText());
                String region = regionField.getText();
                double minRating = Double.parseDouble(ratingField.getText());
                filterResults(resultsTable, maxPrice, region, minRating);
            }
        });

        // Add submit review button listener
        submitReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to submit review
                String reviewText = reviewField.getText();
                int rating = Integer.parseInt(ratingReviewField.getText());
                submitReview(reviewText, rating);
            }
        });

        return panel;
    }

    private void addHotel() {
        String name = JOptionPane.showInputDialog("Enter hotel name:");
        String location = JOptionPane.showInputDialog("Enter hotel location:");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter hotel price per night:"));
        double rating = Double.parseDouble(JOptionPane.showInputDialog("Enter hotel rating:"));
        hotels.add(new Hotel(name, location, price, rating));
    }

    private void deleteHotel() {
        String name = JOptionPane.showInputDialog("Enter hotel name to delete:");
        hotels.removeIf(hotel -> hotel.getName().equals(name));
    }

    private void editHotel() {
        String name = JOptionPane.showInputDialog("Enter hotel name to edit:");
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(name)) {
                String newName = JOptionPane.showInputDialog("Enter new hotel name:", hotel.getName());
                String newLocation = JOptionPane.showInputDialog("Enter new hotel location:", hotel.getLocation());
                double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new hotel price per night:", hotel.getPrice()));
                double newRating = Double.parseDouble(JOptionPane.showInputDialog("Enter new hotel rating:", hotel.getRating()));
                hotel = new Hotel(newName, newLocation, newPrice, newRating);
                break;
            }
        }
    }

    private void addRestaurant() {
        String name = JOptionPane.showInputDialog("Enter restaurant name:");
        String location = JOptionPane.showInputDialog("Enter restaurant location:");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter restaurant price per meal:"));
        double rating = Double.parseDouble(JOptionPane.showInputDialog("Enter restaurant rating:"));
        restaurants.add(new Restaurant(name, location, price, rating));
    }

    private void deleteRestaurant() {
        String name = JOptionPane.showInputDialog("Enter restaurant name to delete:");
        restaurants.removeIf(restaurant -> restaurant.getName().equals(name));
    }

    private void editRestaurant() {
        String name = JOptionPane.showInputDialog("Enter restaurant name to edit:");
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(name)) {
                String newName = JOptionPane.showInputDialog("Enter new restaurant name:", restaurant.getName());
                String newLocation = JOptionPane.showInputDialog("Enter new restaurant location:", restaurant.getLocation());
                double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new restaurant price per meal:", restaurant.getPrice()));
                double newRating = Double.parseDouble(JOptionPane.showInputDialog("Enter new restaurant rating:", restaurant.getRating()));
                restaurant = new Restaurant(newName, newLocation, newPrice, newRating);
                break;
            }
        }
    }

    private void addCafe() {
        String name = JOptionPane.showInputDialog("Enter cafe name:");
        String location = JOptionPane.showInputDialog("Enter cafe location:");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter cafe price per item:"));
        double rating = Double.parseDouble(JOptionPane.showInputDialog("Enter cafe rating:"));
        cafes.add(new Cafe(name, location, price, rating));
    }

    private void deleteCafe() {
        String name = JOptionPane.showInputDialog("Enter cafe name to delete:");
        cafes.removeIf(cafe -> cafe.getName().equals(name));
    }

    private void editCafe() {
        String name = JOptionPane.showInputDialog("Enter cafe name to edit:");
        for (Cafe cafe : cafes) {
            if (cafe.getName().equals(name)) {
                String newName = JOptionPane.showInputDialog("Enter new cafe name:", cafe.getName());
                String newLocation = JOptionPane.showInputDialog("Enter new cafe location:", cafe.getLocation());
                double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new cafe price per item:", cafe.getPrice()));
                double newRating = Double.parseDouble(JOptionPane.showInputDialog("Enter new cafe rating:", cafe.getRating()));
                cafe = new Cafe(newName, newLocation, newPrice, newRating);
                break;
            }
        }
    }

    private void viewAll() {
        StringBuilder allData = new StringBuilder();
        allData.append("Hotels:\n");
        for (Hotel hotel : hotels) {
            allData.append(hotel.getName()).append(" - ").append(hotel.getLocation()).append(" - $")
                    .append(hotel.getPrice()).append(" - Rating: ").append(hotel.getRating()).append("\n");
        }
        allData.append("\nRestaurants:\n");
        for (Restaurant restaurant : restaurants) {
            allData.append(restaurant.getName()).append(" - ").append(restaurant.getLocation()).append(" - $")
                    .append(restaurant.getPrice()).append(" - Rating: ").append(restaurant.getRating()).append("\n");
        }
        allData.append("\nCafes:\n");
        for (Cafe cafe : cafes) {
            allData.append(cafe.getName()).append(" - ").append(cafe.getLocation()).append(" - $")
                    .append(cafe.getPrice()).append(" - Rating: ").append(cafe.getRating()).append("\n");
        }
        JOptionPane.showMessageDialog(this, allData.toString());
    }

    private void filterResults(JTable resultsTable, double maxPrice, String region, double minRating) {
        String[] columns = {"Type", "Name", "Location", "Price", "Rating"};
        List<Object[]> rows = new ArrayList<>();

        for (Hotel hotel : hotels) {
            if (hotel.getPrice() <= maxPrice && hotel.getLocation().equalsIgnoreCase(region) && hotel.getRating() >= minRating) {
                rows.add(new Object[]{"Hotel", hotel.getName(), hotel.getLocation(), hotel.getPrice(), hotel.getRating()});
            }
        }
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getPrice() <= maxPrice && restaurant.getLocation().equalsIgnoreCase(region) && restaurant.getRating() >= minRating) {
                rows.add(new Object[]{"Restaurant", restaurant.getName(), restaurant.getLocation(), restaurant.getPrice(), restaurant.getRating()});
            }
        }
        for (Cafe cafe : cafes) {
            if (cafe.getPrice() <= maxPrice && cafe.getLocation().equalsIgnoreCase(region) && cafe.getRating() >= minRating) {
                rows.add(new Object[]{"Cafe", cafe.getName(), cafe.getLocation(), cafe.getPrice(), cafe.getRating()});
            }
        }

        Object[][] data = rows.toArray(new Object[0][]);
        resultsTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    private void submitReview(String reviewText, int rating) {
        String[] options = {"Hotel", "Restaurant", "Cafe"};
        int choice = JOptionPane.showOptionDialog(this, "Select type to review", "Review",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        String name = JOptionPane.showInputDialog("Enter name of the place:");
        Review review = new Review(loggedInUser.getUsername(), reviewText, rating);

        if (choice == 0) {
            for (Hotel hotel : hotels) {
                if (hotel.getName().equalsIgnoreCase(name)) {
                    hotel.addReview(review);
                    return;
                }
            }
        } else if (choice == 1) {
            for (Restaurant restaurant : restaurants) {
                if (restaurant.getName().equalsIgnoreCase(name)) {
                    restaurant.addReview(review);
                    return;
                }
            }
        } else if (choice == 2) {
            for (Cafe cafe : cafes) {
                if (cafe.getName().equalsIgnoreCase(name)) {
                    cafe.addReview(review);
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Item not found");
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
