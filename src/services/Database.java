package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class Database {
    private static Database database = null;

    public static Database getDatabaseInstance(){
        if (database == null)
            database = new Database();
        return database;
    }

    private Database(){}

    protected List<String []> readDataFromCsv(String csvFile){
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
}
