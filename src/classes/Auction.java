package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction{
    private static int contor = 0;
    private int auctionID;
    private String status; // "available"/"closed"
    private Date date; // closing date
    private List<Product> products = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public Auction(int id, String status, Date date) {
        this.status = status;
        this.date = date;
        if(id != -1){
            if(id >= contor)
                contor = id + 1;
            this.auctionID = id;
        }
        else{
            this.auctionID = contor;
            contor++;
        }
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

    public boolean searchUser(User user){
        for(User u : users){
            if(u.getUserID() == user.getUserID())
                return true;
        }
        return false;
    }

    public void displayProducts(){
        for(Product p : products)
            System.out.println(p.toString());
    }

    public Product getProduct(int productID){
        Product product = null;
        for (Product p : products)
            if(p.getProductID() == productID)
                product = p;

        return product;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
