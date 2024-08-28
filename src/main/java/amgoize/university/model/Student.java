package amgoize.university.model;
import jakarta.validation.constraints.*;

public class Student {
    private int id;
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2")
    @Pattern(regexp = "[A-Z]\\w+", message = "Name should be in this format 'Name'")
    private String name;
    @NotEmpty(message = "Surname should be not empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2")
    @Pattern(regexp = "[A-Z]\\w+", message = "Surname should be in this format 'Surname'")
    private String surname;
    @NotEmpty(message = "Patronymic should be not empty")
    @Pattern(regexp = "([A-Z]\\w+|-)", message = "Patronymic should be in this format 'Patronymic' or " +
            "if you don't have a patronymic, then put '-'")
    private String patronymic;

    private int group;

    public Student() {
    }

    public Student(int id, String name, String surname, String patronymic, int group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.group = group;
    }

    // Геттеры и сеттеры

    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    // Переопределение метода toString
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
