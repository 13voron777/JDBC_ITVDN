package Lesson1.HW1.Task2;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Run {
    private static final String URL = "jdbc:mysql://localhost:3306/HW1";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static final String query1 = "INSERT INTO house(HouseID, Number, Street) " +
            "VALUES (1, 30, 'Nowy Swiat'), (2, 50, 'Solec');";
    private static final String query2 = "UPDATE house SET Number = 7 WHERE HouseID = 1;";

    private static String[] queries = new String[]{query1, query2};

    public static void main(String[] args) throws Exception {
        registerDriver();

        Connection connection = null;
        Statement statement = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            File file = new File("src\\Lesson1\\HW1\\Task2\\file.txt");

            writer = new BufferedWriter(new FileWriter(file));
            for (String query : queries) {
                writer.write(query);
                if (!query.equals(queries[queries.length - 1])) {
                    writer.write("\n");
                }
            }
            System.out.println("File created!");
            writer.close();

            reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                statement.execute(reader.readLine());
            }
            reader.close();
            System.out.println("Queries completed!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
