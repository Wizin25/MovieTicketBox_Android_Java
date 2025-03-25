package com.example.movieticketbox.ui.Ticket;


public class Ticket {
    private int id;
    private int seatId;
    private String seatName;
    private String movieName;
    private String showDateTime;
    private int price;
    private int status;

    // Constructor
    public Ticket(int id, int seatId, String seatName, String movieName, String showDateTime, int price, int status) {
        this.id = id;
        this.seatId = seatId;
        this.seatName = seatName;
        this.movieName = movieName;
        this.showDateTime = showDateTime;
        this.price = price;
        this.status = status;
    }

    // Getters
    public String getMovieName() {
        return movieName;
    }

    public String getShowDateTime() {
        return showDateTime;
    }

    public String getSeatName() {
        return seatName;
    }

    public int getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    // Setters (Nếu bạn cần thay đổi giá trị của các thuộc tính)
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setShowDateTime(String showDateTime) {
        this.showDateTime = showDateTime;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Override phương thức toString() để dễ dàng debug
    @Override
    public String toString() {
        return "Ticket{id=" + id + ", seatName='" + seatName + "', movieName='" + movieName + "', showDateTime='" + showDateTime + "', price=" + price + "}";
    }

    // Override equals và hashCode nếu bạn cần so sánh các đối tượng Ticket
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ticket ticket = (Ticket) obj;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);


    }
}