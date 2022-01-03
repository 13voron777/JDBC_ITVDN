package Lesson1.HW1.Task5;

import java.sql.*;

public class Run {
    private static final String URL = "jdbc:mysql://localhost:3306/myjoinsdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static final String query1 = "SELECT e.Name, e.TelephoneNumber, ep.PlaceOfResidence " +
            "FROM Employees e JOIN EmployeesPrivate ep ON ep.EmployeeID = e.EmployeeID;";
    private static final String query2 = "SELECT e.Name, ep.BirthDate, e.TelephoneNumber " +
            "FROM EmployeesPrivate ep JOIN Employees e ON e.EmployeeID = ep.EmployeeID " +
            "WHERE ep.MaritalStatus = 'Холост';";
    private static final String query3 = "SELECT e.Name, ep.BirthDate, e.TelephoneNumber " +
            "FROM Employees e JOIN EmployeesPrivate ep ON ep.EmployeeID = e.EmployeeID " +
            "JOIN EmployeesSalaries es ON es.EmployeeID = e.EmployeeID " +
            "WHERE es.Position = 'Менеджер';";

    private static String[] queries = new String[]{query1, query2, query3};

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            for (int i = 0; i < queries.length; i++) {
                preparedStatement = connection.prepareStatement(queries[i]);
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("Query " + (i + 1) + ":");
                while (resultSet.next()) {
                    String data1 = resultSet.getString(1);
                    String data2 = resultSet.getString(2);
                    String data3 = resultSet.getString(3);
                    System.out.println(data1 + "\t\t\t" + data2 + "\t\t\t" + data3);
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
