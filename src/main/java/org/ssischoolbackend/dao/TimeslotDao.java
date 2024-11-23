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
import org.ssischoolbackend.model.Timeslot;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
public class TimeslotDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    Properties sqlProperties;

    @PostConstruct
    public void init() {
        String testQuery = sqlProperties.getProperty("timeslot.get.all");
        log.info("Test query loaded: {}", testQuery);
    }
    
    

    public long createTimeslot(Timeslot timeslot) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("day_of_week", timeslot.getDayOfWeek().getValue())
                .addValue("start_time", timeslot.getStartTime())
                .addValue("end_time", timeslot.getEndTime());
        int insert = jdbcTemplate.update(sqlProperties.getProperty("timeslot.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.debug("New timeslot created: " + timeslot.getId());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Timeslot not created.");
            return 0;
        }
    }

    public Optional<Timeslot> getTimeslotById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        Timeslot timeslot = null;
        try {
            timeslot = jdbcTemplate.queryForObject(sqlProperties.getProperty("timeslot.get.one"), namedParameters, Timeslot::baseMapper);
        } catch (DataAccessException e) {
            log.error("Timeslot does not exist: " + id);
        }
        return Optional.ofNullable(timeslot);
    }

    public List<Timeslot> getAllTimeslots() {
        return jdbcTemplate.query(sqlProperties.getProperty("timeslot.get.all"), new MapSqlParameterSource(), Timeslot::baseMapper);
    }

    public int updateTimeslot(Timeslot timeslot) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", timeslot.getId())
                .addValue("day_of_week", timeslot.getDayOfWeek().getValue())
                .addValue("start_time", timeslot.getStartTime())
                .addValue("end_time", timeslot.getEndTime());
        return jdbcTemplate.update(sqlProperties.getProperty("timeslot.update"), sqlParameterSource);
    }

    public int deleteTimeslot(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update(sqlProperties.getProperty("timeslot.delete"), namedParameters);
    }
}
