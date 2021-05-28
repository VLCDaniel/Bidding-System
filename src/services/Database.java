package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Database {
    private static Database database = null;
    private Connection con;

    public static Database getDatabaseInstance(){
        if (database == null)
            database = new Database();
        return database;
    }

    private Database(){}

    public List<String []> readDataFromCsv(String csvFile){
        try {
            // List of lines / arrays with the data from the file
            List<String []> info = Files.lines(Paths.get("resources/" + csvFile))
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
            return info;
        } catch (IOException e) {
            System.out.println("The file does not exist.");
        }
        return null;
    }

    public void addToCsv(String fileName, ArrayList<String> info){
        try {
            File file = new File ("resources/" + fileName);
            if (!file.isFile())
                file.createNewFile();

            FileWriter csvWriter = new FileWriter(file, true);
            csvWriter.write(String.join(",", info));
            csvWriter.write("\n");
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCsv(String fileName, int id, ArrayList<String> updatedInfo){
        try {
            List<String []> info = Files.lines(Paths.get("resources/" + fileName)) // get info from file
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());

            FileWriter writer = new FileWriter("resources/" + fileName);

            info.forEach(line -> {
                if(line[0].equals(String.valueOf(id))) // line with id is modified
                    try{
                        writer.write(String.join(",", updatedInfo));
                        writer.write("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                else // the rest of the file is the same
                    try {
                        writer.write(String.join(",", line));
                        writer.write("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            });
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFromCsv(String fileName, int id){
        try {
            List<String []> info = Files.lines(Paths.get("resources/" + fileName))
                    .map(line -> line.split(","))
                    .filter(line -> !line[0].equals(String.valueOf(id)))
                    .collect(Collectors.toList());

            FileWriter writer = new FileWriter("resources/" + fileName);
            info.forEach(line -> {
                try {
                    writer.write(String.join(",", line));
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidding", "root", "SqLSerVer123456#");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Nu s-a putut realiza conectarea la baza de date.");
        }

        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "SELECT * FROM bidders";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("From database, usernames:");
        while(true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                System.out.println(rs.getString("lastName") + " \n");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
