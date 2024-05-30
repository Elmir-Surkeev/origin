package LAST;

public class Restaurant extends Infrastructure {
    private String cuisineType;

    public Restaurant(String name, String location, double price, String cuisineType) {
        super(name, location, price);
        this.cuisineType = cuisineType;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    @Override
    public String toString() {
        return "Restaurant: " + super.toString() + ", Cuisine Type: " + cuisineType;
    }
}

