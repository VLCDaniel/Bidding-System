package classes;

import utils.AuctionComparator;

import java.util.Set;
import java.util.TreeSet;

public class Bidder extends User{
    private Set<Auction> auctions = new TreeSet<>(new AuctionComparator());

    public Bidder(int id, String lastName, String firstName, String email, String phoneNumber, String nickName, String password) {
        super(id, lastName, firstName, email, phoneNumber, nickName, password);
    }

    public void addAuction(Auction auction){
        this.auctions.add(auction);
    }

    public void displayAuctions(){
        for(Auction auction: auctions)
            System.out.println(auction);
    }
}