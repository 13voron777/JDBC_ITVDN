package Lesson2.HW2.Task2.Dao;

import Lesson2.HW2.Task2.Entity.Auto;
import Lesson2.LAB2.simple_dao.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoJDBCDao implements AutoDAO {

    @Override
    public void add(Auto auto) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO taxopark_autos(id, Model, Price, Speed, Fuel_Spend) " +
                    "VALUES (?, ?, ?, ?, ?)");

            statement.setInt(1, auto.getId());
            statement.setString(2, auto.getModel());
            statement.setFloat(3, auto.getPrice());
            statement.setFloat(4, auto.getSpeed());
            statement.setFloat(5, auto.getFuelSpend());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {

                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public float getSum() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT SUM(Price) FROM taxopark_autos");

            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getFloat(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {

                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return 0;
    }

    @Override
    public List<Auto> getAllSortFuel() {
        List<Auto> allAutosSortFuel = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM taxopark_autos ORDER BY Fuel_Spend DESC");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String model = rs.getString(2);
                float price = rs.getFloat(3);
                float speed = rs.getFloat(4);
                float fuelSpend = rs.getFloat(5);
                Auto auto = new Auto();
                auto.setId(id);
                auto.setModel(model);
                auto.setPrice(price);
                auto.setSpeed(speed);
                auto.setFuelSpend(fuelSpend);
                allAutosSortFuel.add(auto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {

                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allAutosSortFuel;
    }

    @Override
    public List<Auto> getAllInRange(float num1, float num2) {
        List<Auto> allAutosInRange = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM taxopark_autos WHERE Speed " +
                    "BETWEEN ? AND ?");
            statement.setFloat(1, num1);
            statement.setFloat(2, num2);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String model = rs.getString(2);
                float price = rs.getFloat(3);
                float speed = rs.getFloat(4);
                float fuelSpend = rs.getFloat(5);
                Auto auto = new Auto();
                auto.setId(id);
                auto.setModel(model);
                auto.setPrice(price);
                auto.setSpeed(speed);
                auto.setFuelSpend(fuelSpend);
                allAutosInRange.add(auto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {

                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allAutosInRange;
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsshop", "root", "root");
            return connection;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
