package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction{
    private static int counter = 0;
    private int auctionID;
    private String status; // "available"/"closed"
    private Date date; // closing date
    private List<Product> products = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public Auction(String status, Date date) {
        this.status = status;
        this.date = date;
        this.auctionID = counter;
        counter++;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void addUser(User user){
        users.add(user);
    }

    public int getAuctionID() {
        return auctionID;
    }

    @Override
    public String toString() {
        String result = "Auction ID: (" + auctionID + ") -> " + status + " | " + date + " |\n";
        if(users.size() != 0){
            result = result + "Users:\n";
            for(int i = 0; i < users.size(); i++)
                result += "   " + users.get(i).toString();
        }

        if(products.size() != 0){
            result += "Products:\n";
            for(int i = 0; i < products.size(); i++)
                result += "   " + products.get(i).toString();
        }
        return result;
    }


}
