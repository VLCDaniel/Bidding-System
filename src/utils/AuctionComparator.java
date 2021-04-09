package utils;

import classes.Auction;

import java.util.Comparator;

public class AuctionComparator implements Comparator<Auction> {
    @Override
    public int compare(Auction a1, Auction a2) {
        return -a1.getDate().compareTo(a2.getDate());
    }
}
