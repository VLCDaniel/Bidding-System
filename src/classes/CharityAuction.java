package classes;

import java.util.Date;

public class CharityAuction extends Auction{
    private String description; // description of the event

    public CharityAuction(String status, Date date, String description) {
        super(status, date);
        this.description = description;
    }
}
