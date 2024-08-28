package amgoize.university.model;

import jakarta.validation.constraints.*;

public class Teacher {
    private int id;
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Z]\\w+", message = "Name should be in this format 'Name'")
    private String name;
    @NotEmpty(message = "Surname should be not empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Z]\\w+", message = "Surname should be in this format 'Surname'")
    private String surname;
    @NotEmpty(message = "Patronymic should be not empty")
    @Pattern(regexp = "([A-Z]\\w+|-)", message = "Patronymic should be in this format 'Patronymic' or " +
            "if you don't have a patronymic, then put '-'")
    private String patronymic;
    @Min(value = 1, message = "Group should be greater than 1")
    @Max(value = 5, message = "Group should be less than 5")
    private int group;

    public Teacher() {
    }

    public Teacher(int id, String name, String surname, String patronymic, int group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.group = group;
    }

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
    }

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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", group='" + group + '\'' +
                '}';
    }


}
