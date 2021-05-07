package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void removeFromCsv(String fileName, int id){
        try {
            List<String []> info = Files.lines(Paths.get("resources/" + fileName))
                    .map(line -> line.split(","))
                    .filter(line -> !line[0].equals(String.valueOf(id)))
                    .collect(Collectors.toList());

            FileWriter writer = new FileWriter("resources/" + fileName);
            info.stream().forEach(line -> {
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
}
