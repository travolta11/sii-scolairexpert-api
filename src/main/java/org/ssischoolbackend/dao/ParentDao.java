package org.ssischoolbackend.dao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.model.Parent;

import java.util.List;

@Repository
public class ParentDao {
    private final JdbcTemplate jdbcTemplate;

    public ParentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Parent> getAllParents(int offset, int size) {
        String sql = "SELECT * FROM parent LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[]{size, offset}, new BeanPropertyRowMapper<>(Parent.class));
    }

    public int getTotalParents() {
        String sql = "SELECT COUNT(*) FROM parent";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Parent getParentById(Long id) {
        String sql = "SELECT * FROM parent WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Parent.class));
    }

    public void save(Parent parent) {
        String sql = "INSERT INTO parent (first_name, last_name, phone_number, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, parent.getFirstName(), parent.getLastName(), parent.getPhoneNumber(), parent.getEmail());
    }

    public void update(Parent parent) {
        String sql = "UPDATE parent SET first_name = ?, last_name = ?, phone_number = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, parent.getFirstName(), parent.getLastName(), parent.getPhoneNumber(), parent.getEmail(), parent.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM parent WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
