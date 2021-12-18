package Lesson1.LAB1;

import java.sql.*;

public class Run {
    private static final String URL = "jdbc:mysql://localhost:3306/cofeehouse";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static final String GET_ALL = "SELECT * FROM personnels WHERE Position_id = 3";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            System.out.println("LAB 1:");
            statement.execute("INSERT INTO menu(Id, category_id, Name, Price) " +
                    "VALUES (15, 2, 'Раф с солёной карамелью', 60)");
            statement.execute("INSERT INTO personnels(Id, Position_id, Fullname, Phone, Email) " +
                    "VALUES (9, 1, 'Захаров Захар Захарович', '0679032456', 'zaharych@gmail.com')");
            System.out.println("Data inserted!");

            System.out.println("LAB 2:");
            statement.executeUpdate("UPDATE menu SET Price = 30 WHERE Id = 11");
            statement.executeUpdate("UPDATE personnels SET Phone = '7777777777' WHERE Id = 9");
            System.out.println("Data updated!");

            System.out.println("LAB 3:");
            statement.execute("DELETE FROM clients WHERE Id = 10");
            System.out.println("Data deleted!");

            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("LAB 4:");
            while (resultSet.next()) {
                String fn = resultSet.getString("Fullname");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");

                System.out.println(fn + "\t\t\t" + phone + "\t\t\t" + email);
            }

            statement.close();
            preparedStatement.close();
            boolean stClosed = statement.isClosed();
            boolean closed = preparedStatement.isClosed();
            System.out.println("\n" + stClosed + " " + closed);

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
