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
import org.ssischoolbackend.model.Staff;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
public class StaffDao {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    Properties sqlProperties;


    public long createStaffMember(Staff staff) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("first_name", staff.getFirstName())
                .addValue("last_name", staff.getLastName())
                .addValue("email", staff.getEmail())
                .addValue("address", staff.getAddress())
                .addValue("phone", staff.getPhone())
                .addValue("cin",staff.getCin())
                .addValue("birth_date", staff.getDate_of_birth())
                .addValue("start_date",staff.getDate_of_start())
                .addValue("gender",staff.getGender().toString())
                .addValue("position",staff.getPosition().toString())
                .addValue("department",staff.getDepartment().toString());

        int insert = jdbcTemplate.update(sqlProperties.getProperty("staff.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.debug("New staff member Created :) " + staff.getFirstName()+" " +staff.getLastName());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("staff member not created :/ ");
            return 0;
        }
    }

    public Optional<Staff> getStaffById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        Staff staff = null;
        try {
            staff = jdbcTemplate.queryForObject(sqlProperties.getProperty("staff.get.one"), namedParameters, Staff::staffMapper);
        } catch (DataAccessException dataAccessException) {
            log.error("staff member does not exist" + id);
        }
        return Optional.ofNullable(staff);
    }


    public List<Staff> getAllStaffMembers() {
        return jdbcTemplate.query(sqlProperties.getProperty("staff.get.all"),new MapSqlParameterSource(), Staff::staffMapper);
    }

    public boolean deleteStaffMember(Long id) {
        String sql = sqlProperties.getProperty("staff.delete");
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        int rowsAffected = jdbcTemplate.update(sql, params);
        return rowsAffected > 0;
    }

    public boolean updateStaffMember(Staff staff) {
        String sql = sqlProperties.getProperty("staff.update");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", staff.getId());
        params.addValue("first_name", staff.getFirstName());
        params.addValue("last_name", staff.getLastName());
        params.addValue("email", staff.getEmail());
        params.addValue("address", staff.getAddress());
        params.addValue("phone", staff.getPhone());
        params.addValue("cin", staff.getCin());
        params.addValue("birth_date", staff.getDate_of_birth());
        params.addValue("start_date", staff.getDate_of_start());
        params.addValue("gender", staff.getGender().toString());
        params.addValue("position", staff.getPosition().toString());
        params.addValue("department", staff.getDepartment().toString());

        int rowsAffected = jdbcTemplate.update(sql, params);
        return rowsAffected > 0;
    }



}
