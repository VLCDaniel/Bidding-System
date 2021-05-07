package classes;

import java.util.ArrayList;

public class Seller extends User{
    private ArrayList<Product> products = new ArrayList<>();
    private int productsSold; // number of products sold
    private float balance; // balance from the sold products

    public Seller(int id, String lastName, String firstName, String email, String phoneNumber, String nickName, String password) {
        super(id, lastName, firstName, email, phoneNumber, nickName, password);
        this.productsSold = 0;
        this.balance = 0;
    }

    public void addProduct(Product product){products.add(product);}
    public void displayProducts(){
        if(products.size() == 0){
            System.out.println("You have no added products");
            return;
        }
        for(Product product : products)
            System.out.println(product.toString());
    }

    public Product getProductByID(int ID){
        for(Product product : products){
            if(product.getProductID() == ID)
                return product;
        }
        return null;
    }
}
