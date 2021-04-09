package classes;

abstract public class Product {
    private static int contor = 0;
    private int productID;
    private String productName;
    private String description;
    private float startPrice;
    private float soldPrice;
    private int buyerID;
    private boolean insurance;

    public Product(String productName, String description, float startPrice, boolean insurance) {
        this.productID = contor;
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice;
        this.insurance = insurance;
        this.soldPrice = 0;
        this.buyerID = -1;
        contor++;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public float getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(float soldPrice) {
        this.soldPrice = soldPrice;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }
}
