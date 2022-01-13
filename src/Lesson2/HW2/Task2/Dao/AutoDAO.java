package Lesson2.HW2.Task2.Dao;

import Lesson2.HW2.Task2.Entity.Auto;

import java.util.List;

public interface AutoDAO {
    void add(Auto auto);

    float getSum();

    List<Auto> getAllSortFuel();

    List<Auto> getAllInRange(float num1, float num2);
}
