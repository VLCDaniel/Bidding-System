package classes;

import java.util.ArrayList;

public class Seller extends User{
    private ArrayList<Product> products;
    private int productsSold; // number of products sold
    private float balance; // balance from the sold products

    public Seller(String lastName, String firstName, String email, String phoneNumber, String nickName, String password) {
        super(lastName, firstName, email, phoneNumber, nickName, password);
        this.productsSold = 0;
        this.balance = 0;
    }
}
