package amgoize.university.DAO;

import amgoize.university.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GroupDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Group> groupRowMapper = ((rs, rowNum) ->
            new Group(rs.getInt("id"), rs.getString("name")));


    // Получить все группы
    public List<Group> getAllGroups() {
        return jdbcTemplate.query("SELECT * FROM groups", groupRowMapper);
    }

    // Получить группу по ID
    public Group getGroup(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM groups WHERE id = ?", new Object[]{id}, groupRowMapper);
    }

    public Group createGroup(Group group){
        jdbcTemplate.update("INSERT INTO groups (id, name) VALUES (?, ?)", group.getId(), group.getName());
        return group;
    }
    // Обновить информацию о группе
    public void updateGroup(Group group) {
        jdbcTemplate.update("UPDATE groups SET name = ? WHERE id = ?", group.getName(), group.getId());
    }

    // Удалить группу по ID
    public void deleteGroup(int id) {
        jdbcTemplate.update("DELETE FROM groups WHERE id = ?", id);
    }
}
