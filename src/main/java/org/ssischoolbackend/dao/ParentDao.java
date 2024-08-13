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
import org.ssischoolbackend.model.Parent;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
public class ParentDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private Properties sqlProperties;

    public long createNewParent(Parent parent) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("first_name", parent.getFirstName())
                .addValue("last_name", parent.getLastName())
                .addValue("email", parent.getEmail())
                .addValue("phone", parent.getPhone())
                .addValue("cin", parent.getCin());

        int insert = jdbcTemplate.update(sqlProperties.getProperty("parent.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.debug("New parent Created :) " + parent.getFirstName() + " " + parent.getLastName());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Parent not created :/ ");
            return 0;
        }
    }

    public Optional<Parent> getParentById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        Parent parent = null;
        try {
            parent = jdbcTemplate.queryForObject(sqlProperties.getProperty("parent.get.one"), namedParameters, Parent::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.error("Parent does not exist with id: " + id);
        }
        return Optional.ofNullable(parent);
    }

    public List<Parent> getAllParents(int offset, int size) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return jdbcTemplate.query(sqlProperties.getProperty("parent.get.all"), sqlParameterSource, Parent::baseMapper);
    }

    public int getTotalParents() {
        return jdbcTemplate.queryForObject(sqlProperties.getProperty("parent.count"), new MapSqlParameterSource(), Integer.class);
    }

    public void updateParent(Parent parent) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", parent.getId())
                .addValue("first_name", parent.getFirstName())
                .addValue("last_name", parent.getLastName())
                .addValue("email", parent.getEmail())
                .addValue("phone", parent.getPhone())
                .addValue("cin", parent.getCin());

        int update = jdbcTemplate.update(sqlProperties.getProperty("parent.update"), sqlParameterSource);
        if (update == 1) {
            log.debug("Parent updated successfully: " + parent.getId());
        } else {
            log.error("Failed to update Parent: " + parent.getId());
        }
    }

    public void deleteParentById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        int delete = jdbcTemplate.update(sqlProperties.getProperty("parent.delete"), namedParameters);
        if (delete == 1) {
            log.debug("Parent deleted successfully: " + id);
        } else {
            log.error("Failed to delete Parent: " + id);
        }
    }

    public boolean hasAssociatedStudents(Long parentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("parentId", parentId);
        Integer count = jdbcTemplate.queryForObject(sqlProperties.getProperty("parent.hasAssociatedStudents"), namedParameters, Integer.class);
        return count != null && count > 0;
    }

    public Optional<Parent> getParentByCin(String cin) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("cin", cin);
        Parent parent = null;
        try {
            parent = jdbcTemplate.queryForObject(sqlProperties.getProperty("parent.get.by.cin"), namedParameters, Parent::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.error("Parent does not exist with cin: " + cin);
        }
        return Optional.ofNullable(parent);
    }

    public Optional<Parent> getParentByEmail(String email) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("email",email.trim().toLowerCase());
        Parent parent = null;
        try{
            parent = jdbcTemplate.queryForObject(sqlProperties.getProperty("parent.get.by.email"), namedParameters, Parent::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.error("Parent does not exist with email: " + email);
        }
        return Optional.ofNullable(parent);
    }

    public Optional<Parent> getParentByPhone(String phone) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("phone",phone.trim());
        Parent parent = null;
        try{
            parent = jdbcTemplate.queryForObject(sqlProperties.getProperty("parent.get.by.phone"), namedParameters, Parent::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.error("Parent does not exist with phone: " + phone);
        }
        return Optional.ofNullable(parent);
    }
}
