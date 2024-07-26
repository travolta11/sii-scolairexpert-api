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
import org.ssischoolbackend.model.Teacher;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
public class TeacherDao {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    Properties sqlProperties;

    public long createNewTeacher(Teacher teacher) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("full_name", teacher.getFullName())
                .addValue("email", teacher.getEmail())
                .addValue("phone_no", teacher.getPhoneNo());
        int insert = jdbcTemplate.update(sqlProperties.getProperty("teacher.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.debug("New teacher Created :) " + teacher.getFullName());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Teacher not created :/ ");
            return 0;
        }
    }

    public Optional<Teacher> getTeacherById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("teacher_id", id);
        Teacher teacher = null;
        try {
            teacher = jdbcTemplate.queryForObject(sqlProperties.getProperty("teacher.get.one"), namedParameters, Teacher::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.error("Teacher does not exist: " + id);
        }
        return Optional.ofNullable(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return jdbcTemplate.query(sqlProperties.getProperty("teacher.get.all"), new MapSqlParameterSource(), Teacher::baseMapper);
    }
}
