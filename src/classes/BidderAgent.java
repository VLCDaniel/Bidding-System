package classes;

public class BidderAgent extends User {
    private String status; // "available"/"bidding"
    private int maxSum; // maximum sum for bidding
    private int clientID;
    private int auctionID;

    public BidderAgent(String lastName, String firstName, String email, String phoneNumber, String nickName, String password) {
        super(lastName, firstName, email, phoneNumber, nickName, password);
    }
}
