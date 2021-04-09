package classes;

import java.util.Date;

public class CollectionProduct extends Product{
    protected String firstOwner;
    protected String state; // how used is the product
    protected Date apparition;

    public CollectionProduct(String productName, String description, float startPrice, boolean insurance, int ID, String state, Date apparition, String firstOwner) {
        super(productName, description, startPrice, insurance, ID);
        this.state = state;
        this.apparition = apparition;
        this.firstOwner = firstOwner;
    }
}
