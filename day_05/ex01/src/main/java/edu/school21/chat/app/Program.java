package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Program {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "ereginia";
    private static final String DB_PWD = "12345";
    private static final String DB_SCHEMA = "src/main/resources/schema.sql";
    private static final String DB_DATA = "src/main/resources/data.sql";

    public static void main(String[] args) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(DB_URL);
        ds.setUsername(DB_USER);
        ds.setPassword(DB_PWD);

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(ds);
        runQueriesFromFile(ds, DB_SCHEMA);
        runQueriesFromFile(ds, DB_DATA);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message ID");
        System.out.print("-> ");
        Long id = sc.nextLong();
        try {
            if (repository.findById(id).isPresent())
                System.out.println(repository.findById(id).get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void runQueriesFromFile(HikariDataSource ds, String filename) {
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename)).useDelimiter(";");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (scanner.hasNext()) {
                connection.createStatement().execute(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

}
