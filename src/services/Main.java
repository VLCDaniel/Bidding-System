package services;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date d1 = new Date(120, 0, 29);
        System.out.println(d1);
        Services s = Services.getServicesInstance();
        s.Welcome();
    }
}