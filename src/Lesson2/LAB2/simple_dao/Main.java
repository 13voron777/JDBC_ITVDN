package Lesson2.LAB2.simple_dao;

import Lesson2.LAB2.simple_dao.dao.ClientDAO;
import Lesson2.LAB2.simple_dao.dao.DAOFactory;
import Lesson2.LAB2.simple_dao.dao.IDAOFactory;
import Lesson2.LAB2.simple_dao.entity.Client;

import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public class Main {

    public static void main(String[] args) {
        IDAOFactory factory = DAOFactory.getInstance();
        //CarDAO carDAO = factory.getCarDAO();
        ClientDAO clientDAO = factory.getClientDAO();

// ============== add(Car car) ============

        /*Car car1 = new Car();
        car1.setMark("Audi");
        car1.setModel("A8");
        car1.setPrice(15_000);

        carDAO.add(car1);


          Car car2 = new Car();
//        car2.setMark("Chevrolet");
//        car2.setModel("E2");
//        car2.setPrice(45_000);
//
//        carDAO.add(car2);

        */

// =============== add (Client client) =========

        Client client1 = new Client();
        client1.setName("Viktor");
        client1.setAge(25);
        client1.setPhone("+48 888 777 555");
        clientDAO.add(client1);

        Client client2 = new Client();
        client2.setName("Mateusz");
        client2.setAge(27);
        client2.setPhone("+48 999 777 444");
        clientDAO.add(client2);


// =========== getAll() ============

//        List<Car> cars = carDAO.getAll();
//        for (Car car : cars) {
//            System.out.println(car);
//        }
//

        List<Client> clients = clientDAO.getAll();
        for (Client client : clients) {
            System.out.println(client);
        }

// =========== getById(int id)  ============

//        Car car3 = carDAO.getById(2);
//        System.out.println(car3);

        Client client3 = clientDAO.getById(6);
        System.out.println(client3);

// ===========  updatePrice(int price, int carId) ============

//        carDAO.updatePrice(100_000, 1);
        clientDAO.updatePhone("+48 888 888 888", 5);
        clientDAO.updatePhone("+48 999 999 999", 6);


// ===========  remove(String mark) ============
//        carDAO.remove("chevrolet");
        clientDAO.remove("Viktor");

    }

}
