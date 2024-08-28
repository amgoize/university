package amgoize.university.DAO;

import amgoize.university.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeachersDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeachersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Teacher> teacherRowMapper = ((rs, rowNum) -> new Teacher(rs.getInt("id"),
            rs.getString("name"), rs.getString("surname"), rs.getString("patronymic"),
            rs.getInt("group_id")));

    public List<Teacher> showTeachers() {
        return jdbcTemplate.query("SELECT * FROM teacher", teacherRowMapper);
    }


    public Teacher showTeacher(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM teacher WHERE id = ?",
                new Object[]{id},
                teacherRowMapper
        );
    }

    public Teacher saveTeacher(Teacher teacher) {
        jdbcTemplate.update(
                "INSERT INTO teacher (name, surname, patronymic, group_id) VALUES (?, ?, ?, ?)",
                teacher.getName(), teacher.getSurname(), teacher.getPatronymic(), teacher.getGroup()
        );
        return teacher;
    }

    public void updateTeacher(Teacher teacher) {
        jdbcTemplate.update(
                "UPDATE teacher SET name = ?, surname = ?, patronymic = ?, group_id = ? WHERE id = ?",
                teacher.getName(), teacher.getSurname(), teacher.getPatronymic(), teacher.getGroup(), teacher.getId()
        );
    }

    public void deleteTeacher(int id) {
        jdbcTemplate.update("DELETE FROM teacher WHERE id = ?", id);
    }
}
