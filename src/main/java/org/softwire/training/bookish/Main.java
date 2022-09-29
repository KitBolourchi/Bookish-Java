package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;

import java.sql.*;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {
        String hostname = "localhost";
        String database = "bookish";
        String user = "root";
        String password = "password";
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbcMethod(connectionString);
        jdbiMethod(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

        try (Connection connection = DriverManager.getConnection(connectionString)) {
            Statement st = connection.createStatement();
            String query = ("SELECT Title FROM Books;");
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String Book = rs.getString("Title");
                System.out.println(Book);
            }
        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
        }
    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        // TODO: print out the details of all the books (using JDBI)
        // See this page for details: http://jdbi.org
        // Use the "Book" class that we've created for you (in the models.database folder)

        Jdbi jdbi = Jdbi.create(connectionString);

        List<String> books = jdbi.withHandle(handle ->
                handle.createQuery("SELECT Title From Books;")
                        .mapTo(String.class)
                        .list());
        for (String book: books) {
            System.out.println(book);
        }

    }
}
