package Lesson2.HW2.Extra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Run {
    private static final String URL = "jdbc:mysql://localhost:3306/carsshop";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static final String query2 = "SELECT SUM(Price) AS Sum_autos FROM taxopark_autos";
    private static final String query3 = "SELECT * FROM taxopark_autos ORDER BY Fuel_Spend DESC";
    private static final String query4 = "SELECT * FROM taxopark_autos WHERE Speed " +
            "BETWEEN 245 AND 265";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            // 1. Creating taxopark (task 10 from the book, taxopark)
            statement.execute("CREATE TABLE taxopark_autos(Id int, Model varchar(255)," +
                    "Price float, Speed float, Fuel_Spend float)");
            statement.execute("INSERT INTO taxopark_autos(Id, Model, Price, Speed, Fuel_Spend) " +
                    "VALUES (1, 'Volkswagen', 5000.5, 240, 1.4)," +
                    "(2, 'Skoda', 7010.3, 260, 1.6)," +
                    "(3, 'Toyota', 6100.7, 250, 1.6)," +
                    "(4, 'Hyundai', 4500.6, 240, 1.4)," +
                    "(5, 'Mazda', 6500.8, 260, 1.5)");

            // 2. Sum of cars's cost
            preparedStatement = connection.prepareStatement(query2);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println("Sum price: " + resultSet.getFloat("Sum_autos"));
            System.out.println();

            // 3. Sorting autos by fuel's spending
            preparedStatement = connection.prepareStatement(query3);
            ResultSet resultSet3 = preparedStatement.executeQuery();

            List<Auto> autos3 = new ArrayList<>();
            while (resultSet3.next()) {
                Auto auto = new Auto();
                auto.setId(resultSet3.getInt("Id"));
                auto.setModel(resultSet3.getString("Model"));
                auto.setPrice(resultSet3.getFloat("Price"));
                auto.setSpeed(resultSet3.getFloat("Speed"));
                auto.setFuelSpend(resultSet3.getFloat("Fuel_Spend"));
                autos3.add(auto);
            }

            System.out.println("Sorting autos by fuel's spending:");
            for (Auto auto : autos3) {
                System.out.println(auto.getId() + "\t" + auto.getModel()
                        + "\t" + auto.getPrice() + "\t" + auto.getSpeed()
                + "\t" + auto.getFuelSpend());
            }
            System.out.println();

            // 4. Selecting autos which are in user's range
            preparedStatement = connection.prepareStatement(query4);
            ResultSet resultSet4 = preparedStatement.executeQuery();

            List<Auto> autos4 = new ArrayList<>();
            while (resultSet4.next()) {
                Auto auto = new Auto();
                auto.setId(resultSet4.getInt("Id"));
                auto.setModel(resultSet4.getString("Model"));
                auto.setPrice(resultSet4.getFloat("Price"));
                auto.setSpeed(resultSet4.getFloat("Speed"));
                auto.setFuelSpend(resultSet4.getFloat("Fuel_Spend"));
                autos4.add(auto);
            }

            System.out.println("Selecting autos which are in user's range:");
            for (Auto auto : autos4) {
                System.out.println(auto.getId() + "\t" + auto.getModel()
                        + "\t" + auto.getPrice() + "\t" + auto.getSpeed()
                        + "\t" + auto.getFuelSpend());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                preparedStatement.close();
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
