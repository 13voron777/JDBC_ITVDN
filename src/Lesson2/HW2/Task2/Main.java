package Lesson2.HW2.Task2;

import Lesson2.HW2.Task2.Dao.AutoDAO;
import Lesson2.HW2.Task2.Dao.DAOFactory;
import Lesson2.HW2.Task2.Dao.IDAOFactory;
import Lesson2.HW2.Task2.Entity.Auto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IDAOFactory idaoFactory = DAOFactory.getInstance();
        AutoDAO autoDAO = idaoFactory.getAutoDAO();

        Auto auto1 = new Auto();
        auto1.setId(1);
        auto1.setModel("Volkswagen");
        auto1.setPrice(5000.5f);
        auto1.setSpeed(240f);
        auto1.setFuelSpend(1.4f);
        autoDAO.add(auto1);

        Auto auto2 = new Auto();
        auto2.setId(2);
        auto2.setModel("Skoda");
        auto2.setPrice(7010.3f);
        auto2.setSpeed(260f);
        auto2.setFuelSpend(1.6f);
        autoDAO.add(auto2);

        Auto auto3 = new Auto();
        auto3.setId(3);
        auto3.setModel("Toyota");
        auto3.setPrice(6100.7f);
        auto3.setSpeed(250f);
        auto3.setFuelSpend(1.6f);
        autoDAO.add(auto3);

        Auto auto4 = new Auto();
        auto4.setId(4);
        auto4.setModel("Hyundai");
        auto4.setPrice(4500.6f);
        auto4.setSpeed(240f);
        auto4.setFuelSpend(1.4f);
        autoDAO.add(auto4);

        Auto auto5 = new Auto();
        auto5.setId(5);
        auto5.setModel("Mazda");
        auto5.setPrice(6500.8f);
        auto5.setSpeed(260f);
        auto5.setFuelSpend(1.5f);
        autoDAO.add(auto5);

        float sum = autoDAO.getSum();
        System.out.println("Sum price: " + sum);
        System.out.println();

        List<Auto> autosSortFuel = autoDAO.getAllSortFuel();
        System.out.println("Sorting autos by fuel's spending:");
        for (Auto auto : autosSortFuel) {
            System.out.println(auto);
        }
        System.out.println();

        List<Auto> autosInRange = autoDAO.getAllInRange(245, 265);
        System.out.println("Selecting autos which are in user's range:");
        for (Auto auto : autosInRange) {
            System.out.println(auto);
        }
    }
}
