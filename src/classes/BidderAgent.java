package classes;

public class BidderAgent extends User {
    private String status; // "available"/"bidding"
    private int maxSum; // maximum sum for bidding
    private double commission;
    private int clientID;
    private int auctionID;
    private int productID;

    public BidderAgent(int id, String lastName, String firstName, String email, String phoneNumber, String nickName, String password, double commission) {
        super(id, lastName, firstName, email, phoneNumber, nickName, password);
        this.status = "available";
        this.maxSum = 0;
        this.commission = commission;
        this.clientID = -1;
        this.auctionID = -1;
        this.productID = -1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getCommission() {
        return commission;
    }

    @Override
    public String toString() {
        return "(" + userID + ") " + nickName + ", " + email + ", " + status + ", commission: " + commission + '\n';
    }
}
