package application;

import entities.LogEntry;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter with full file path: ");
        String path = sc.nextLine();

        try (BufferedReader bw = new BufferedReader(new FileReader(path))){
            Set< LogEntry> logEntrySet = new HashSet<>();

            String line = bw.readLine();

            while (line != null){

                String[] fields = line.split(", ");
                String username = fields[0];
                Date moment = Date.from(Instant.parse(fields[1]));

                logEntrySet.add(new LogEntry(username, moment));
                line = bw.readLine();
            }
            System.out.println("TOTAL USERS: " + logEntrySet.size());
        } catch (IIOException e){
            System.out.println("Error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo not found. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while running the app." +e.getMessage());
        }

        sc.close();
    }
}