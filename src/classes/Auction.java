package classes;

import java.util.Date;
import java.util.List;

public class Auction{
    private static int counter = 0;
    private int auctionID;
    private String status;
    private Date date;
    private List<Product> products;
    private List<User> users;

    public Auction(String status, Date date) {
        this.status = status;
        this.date = date;
        this.auctionID = counter;
        counter++;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Auction: " + auctionID + " " + status + ", " + date;
    }


}
