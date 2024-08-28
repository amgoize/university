package amgoize.university.DAO;

import amgoize.university.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Student> studentRowMapper = ((rs, rowNum) -> new Student(rs.getInt("id"),
            rs.getString("name"), rs.getString("surname"), rs.getString("patronymic"),
            rs.getInt("group_id")));
    public List<Student> showStudents() {
        return jdbcTemplate.query("SELECT * FROM student", studentRowMapper);
    }

    public Student showStudent(int id) {
        return jdbcTemplate.query("SELECT * FROM student WHERE id = ?", new Object[]{id},
                studentRowMapper).stream().findFirst().orElse(null);
    }

    public Student saveStudent(Student student) {
        jdbcTemplate.update("INSERT INTO student (name, surname, patronymic, group_id) VALUES (?, ?, ?, ?)",
                student.getName(), student.getSurname(), student.getPatronymic(), student.getGroup());
        return student;
    }

    public void updateStudent(Student student) {
        jdbcTemplate.update(
                "UPDATE student SET name = ?, surname = ?, patronymic = ?, group_id = ? WHERE id = ?",
                student.getName(), student.getSurname(), student.getPatronymic(), student.getGroup(), student.getId()
        );
    }

    public void deleteStudent(int id) {
        jdbcTemplate.update("DELETE FROM student WHERE id = ?", id);
    }

}
