package LAST;
public class Cafe extends Infrastructure {
    private String cuisineType;

    public Cafe(String name, String location, double price, String cuisineType) {
        super(name, location, price);
        this.cuisineType = cuisineType;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    @Override
    public String toString() {
        return "Cafe: " + super.toString() + ", Cuisine Type: " + cuisineType;
    }
}

