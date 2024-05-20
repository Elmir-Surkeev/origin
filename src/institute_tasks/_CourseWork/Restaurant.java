package institute_tasks._CourseWork;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    private double price;
    private double rating;
    private List<Review> reviews;

    public Restaurant(String name, String location, double price, double rating) {
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

