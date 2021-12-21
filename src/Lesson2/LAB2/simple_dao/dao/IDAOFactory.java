package Lesson2.LAB2.simple_dao.dao;

/**
 * Created by Asus on 31.01.2018.
 */
public interface IDAOFactory {

    CarDAO getCarDAO();

    ClientDAO getClientDAO();


}
