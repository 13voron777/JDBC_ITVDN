package Lesson1.HW1.Extra;

import java.sql.*;

public class Run {
    private static final String URL = "jdbc:mysql://localhost:3306/HW1";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            statement.execute("INSERT INTO house(HouseID, Number, Street) " +
                    "VALUES (1, 30, 'Nowy Swiat'), (2, 50, 'Solec')");
            System.out.println("Data inserted!");

            statement.executeUpdate("UPDATE house SET Number = 7 WHERE HouseID = 1");
            System.out.println("Data updated!");

            statement.execute("TRUNCATE TABLE house");
            System.out.println("Data deleted!");

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
