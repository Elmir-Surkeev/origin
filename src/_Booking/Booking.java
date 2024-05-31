package _Booking;

import java.util.Date;

public class Booking {
    private static int nextId = 1;
    private int id;
    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomType;

    public Booking(String guestName, Date checkInDate, Date checkOutDate, String roomType) {
        this.id = nextId++;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
    }

    public int getId() {
        return id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Booking ID: " + id +
                ", Guest Name: " + guestName +
                ", Check-In Date: " + checkInDate +
                ", Check-Out Date: " + (checkOutDate != null ? checkOutDate : "Неизвестна") +
                ", Room Type: " + roomType;
    }
}
