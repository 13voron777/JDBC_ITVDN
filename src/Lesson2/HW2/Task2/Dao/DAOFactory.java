package Lesson2.HW2.Task2.Dao;

public class DAOFactory implements IDAOFactory {
    private static IDAOFactory factory;

    private DAOFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized IDAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }

    @Override
    public AutoDAO getAutoDAO() {
        return new AutoJDBCDao();
    }
}
