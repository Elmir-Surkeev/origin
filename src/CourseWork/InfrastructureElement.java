package CourseWork;

public abstract class InfrastructureElement {
    private String name;
    private String type;
    private String location;
    private double price;
    private int rating;

    public InfrastructureElement(String name, String location, double price, int rating) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

