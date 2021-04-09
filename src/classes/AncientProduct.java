package classes;

import java.util.Date;

public class AncientProduct extends CollectionProduct{
    private String previousOwner;
    private String creator;
    private boolean reasembled;
    private String storagePlace;


    public AncientProduct(String productName, String description, float startPrice, boolean insurance, String state, Date apparition, String firstOwner) {
        super(productName, description, startPrice, insurance, state, apparition, firstOwner);
    }
}
