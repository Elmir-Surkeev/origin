package LAST;

public class Infrastructure {
    private String name;
    private String location;
    private double price;

    public Infrastructure(String name, String location, double price) {
        this.name = name;
        this.location = location;
        this.price = price;
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

    @Override
    public String toString() {
        return "Name: " + name + ", Location: " + location + ", Price: " + price;
    }
}
