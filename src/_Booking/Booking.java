package _Booking;

import java.util.Date;

public class Booking {
    private static int counter = 1;
    private int id;
    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomType;
    private int roomNumber;

    public Booking(String guestName, Date checkInDate, Date checkOutDate, String roomType, int roomNumber) {
        this.id = counter++;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
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

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", guestName='" + guestName + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", roomType='" + roomType + '\'' +
                ", roomNumber=" + roomNumber +
                '}';
    }
}