package classes;

import java.util.Date;

public class AncientProduct extends CollectionProduct {
    private String previousOwner;
    private String creator;
    private boolean reasembled;
    private String storagePlace;


    public AncientProduct(int id, String productName, String description, float startPrice, boolean insurance, int ID, String state,
                          Date apparition, String firstOwner, String previousOwner, String creator, boolean reasembled, String storagePlace) {
        super(id, productName, description, startPrice, insurance, ID, state, apparition, firstOwner);
        this.previousOwner = previousOwner;
        this.creator = creator;
        this.reasembled = reasembled;
        this.storagePlace = storagePlace;
    }

    public String getPreviousOwner() {
        return previousOwner;
    }

    public void setPreviousOwner(String previousOwner) {
        this.previousOwner = previousOwner;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isReasembled() {
        return reasembled;
    }

    public void setReasembled(boolean reasembled) {
        this.reasembled = reasembled;
    }

    public String getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(String storagePlace) {
        this.storagePlace = storagePlace;
    }
}
