package Lesson2.LAB2.simple_dao.dao;

import Lesson2.LAB2.simple_dao.entity.Client;

import java.sql.*;
import java.util.List;

public class ClientJDBCDao implements ClientDAO {
    @Override
    public void add(Client client) {
        Connection connection = null;

        connection = getConnection();
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("INSERT INTO clients(name, age, phone) VALUES (?, ?, ?)");

            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.setString(3, client.getPhone());

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
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public void updatePhone(String phone, int clientId) {

    }

    @Override
    public void remove(String name) {

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
