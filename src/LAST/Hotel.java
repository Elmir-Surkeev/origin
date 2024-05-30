package LAST;

public class Hotel extends Infrastructure {
    private int numberOfRooms;

    public Hotel(String name, String location, double price, int numberOfRooms) {
        super(name, location, price);
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    @Override
    public String toString() {
        return "Hotel: " + super.toString() + ", Number of Rooms: " + numberOfRooms;
    }
}



