package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Services s = Services.getServicesInstance();
        s.Welcome();

//        try {
//            DriverManager.getConnection("jdbc:mysql://localhost:3306/bidding", "root", "SqLSerVer123456#");
//        } catch (SQLException e) {
//            System.out.println(e);
//            throw new RuntimeException("Nu s-a putut realiza conectarea la baza de date.");
//        }

    }
}