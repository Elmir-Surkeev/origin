package CourseWork;
public class Review {
    private String name;
    private String phone;
    private String reviewText;
    private int rating;

    public Review(String name, String phone, String reviewText, int rating) {
        this.name = name;
        this.phone = phone;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }
}

