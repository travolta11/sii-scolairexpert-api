package org.ssischoolbackend.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.dto.AbsenceDto;
import org.ssischoolbackend.model.absence;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

@Repository
public class AbsenceDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String createAbsenceQuery;
    private final String getAbsencesByDateQuery;
    private final String getAbsencesByStudentIdQuery;
    private final String updateAbsenceReasonQuery;
    private final String getAbsencesByStudentIdAndDateQuery;

    public AbsenceDao(NamedParameterJdbcTemplate jdbcTemplate,
                      @Value("classpath:sql/absence.properties") Resource resource) throws IOException {
        this.jdbcTemplate = jdbcTemplate;

        Properties properties = new Properties();
        properties.load(resource.getInputStream());

        this.createAbsenceQuery = properties.getProperty("absence.create");
        this.getAbsencesByDateQuery = properties.getProperty("absence.get.byDate");
        this.getAbsencesByStudentIdQuery = properties.getProperty("absence.get.byStudentId");
        this.updateAbsenceReasonQuery = properties.getProperty("absence.update.reason");
        this.getAbsencesByStudentIdAndDateQuery = properties.getProperty("absence.get.byStudentIdAndDate");
    }

    public void addAbsence(AbsenceDto absence) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", absence.getStudentId());
        params.addValue("date", absence.getDate());
        params.addValue("reason", absence.getReason());

        jdbcTemplate.update(createAbsenceQuery, params);
    }

    public List<absence> getAbsenceByDate(Date date) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", date);

        return jdbcTemplate.query(getAbsencesByDateQuery, params, absence::baseMapper);
    }

    public List<absence> getAbsenceByStudentId(int studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId);

        return jdbcTemplate.query(getAbsencesByStudentIdQuery, params, absence::baseMapper);
    }

    public void updateAbsenceReason(AbsenceDto absence) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", absence.getStudentId());
        params.addValue("date", absence.getDate());
        params.addValue("reason", absence.getReason());

        jdbcTemplate.update(updateAbsenceReasonQuery, params);
    }

    public absence getAbsenceByStudentIdAndDate(int studentId, Date date) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId);
        params.addValue("date", date);

        return jdbcTemplate.queryForObject(getAbsencesByStudentIdAndDateQuery, params, absence::baseMapper);
    }

}
