package amgoize.university.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class Group {
    private int id;
    @NotEmpty
    @Pattern(regexp = "[A-Z]\\w+", message = "Name should be in this format 'Name'")
    private String name;

    public Group() {
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
