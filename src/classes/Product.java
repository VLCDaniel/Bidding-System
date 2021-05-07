package classes;

abstract public class Product {
    private static int contor = 0;
    private int productID;
    private String productName;
    private String description;
    private float startPrice;
    private float soldPrice;
    private int buyerID;
    private int sellerID;
    private boolean insurance;

    public Product(int id, String productName, String description, float startPrice, boolean insurance, int ID) {
        this.productID = contor;
        this.productName = productName;
        this.description = description;
        this.startPrice = startPrice;
        this.insurance = insurance;
        this.soldPrice = 0;
        this.buyerID = -1;
        this.sellerID = ID;
        if(id != -1){
            if(id >= contor)
                contor = id + 1;
            this.productID = id;
        }
        else{
            this.productID = contor;
            contor++;
        }
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

    public boolean getInsurance() {
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

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }


    @Override
    public String toString() {
        String result = "(" + productID + ") " + productName + ", start " + startPrice + "$\n       " + description;
        if(soldPrice != 0)
            result += "; selling/sold at " + soldPrice + "$";
        result += '\n';
        return result;
    }
}
