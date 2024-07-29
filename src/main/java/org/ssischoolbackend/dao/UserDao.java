package org.ssischoolbackend.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
public class UserDao {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    Properties sqlProperties;

    public long createUser(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("role", user.getRole());
        int insert = jdbcTemplate.update(sqlProperties.getProperty("user.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.debug("New user created: " + user.getUsername());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("User not created");
            return 0;
        }
    }

    public Optional<User> getUserById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sqlProperties.getProperty("user.get.one"), namedParameters, User::baseMapper);
        } catch (DataAccessException e) {
            log.error("User does not exist: " + id);
        }
        return Optional.ofNullable(user);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query(sqlProperties.getProperty("user.get.all"), new MapSqlParameterSource(), User::baseMapper);
    }

    public void updateUser(Long id, User user) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("role", user.getRole());
        jdbcTemplate.update(sqlProperties.getProperty("user.update"), sqlParameterSource);
    }

    public void deleteUser(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sqlProperties.getProperty("user.delete"), namedParameters);
    }
}
