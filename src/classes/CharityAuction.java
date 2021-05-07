package classes;

import java.util.Date;

public class CharityAuction extends Auction{
    private String description; // description of the event

    public CharityAuction(int id, String status, Date date, String description) {
        super(id, status, date);
        this.description = description;
    }
}
