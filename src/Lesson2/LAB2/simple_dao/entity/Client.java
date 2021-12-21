package Lesson2.LAB2.simple_dao.entity;

/**
 * Created by Asus on 31.01.2018.
 */
public class Client {

    private long id;
    private String name;
    private int age;
    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", mark='" + name + '\'' +
                ", model='" + age + '\'' +
                ", price=" + phone;
    }
}
