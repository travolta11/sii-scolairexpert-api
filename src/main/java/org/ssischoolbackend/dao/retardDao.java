package org.ssischoolbackend.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.dto.RetardDto;
import org.ssischoolbackend.model.retard;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

@Repository
public class retardDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String createRetardQuery;
    private final String getRetardsByDateQuery;
    private final String getRetardsByStudentIdQuery;
    private final String updateRetardReasonQuery;
    private final String getRetardsByStudentIdAndDateQuery;

    public retardDao(NamedParameterJdbcTemplate jdbcTemplate,
                     @Value("classpath:sql/retard.properties") Resource resource) throws IOException {
        this.jdbcTemplate = jdbcTemplate;

        Properties properties = new Properties();
        properties.load(resource.getInputStream());
        this.createRetardQuery = properties.getProperty("retard.create");
        this.getRetardsByDateQuery = properties.getProperty("retard.get.byDate");
        this.getRetardsByStudentIdQuery = properties.getProperty("retard.get.byStudentId");
        this.updateRetardReasonQuery = properties.getProperty("retard.update.reason");
        this.getRetardsByStudentIdAndDateQuery = properties.getProperty("retard.get.byStudentIdAndDate");
    }

    public void addRetard(RetardDto retard) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", retard.getStudentId());
        params.addValue("date", retard.getDate());
        params.addValue("reason", retard.getReason());

        jdbcTemplate.update(createRetardQuery, params);
    }

    public List<retard> getRetardByDate(Date date) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", date);

        return jdbcTemplate.query(getRetardsByDateQuery, params, retard::baseMapper);
    }

    public List<retard> getRetardByStudentId(Long studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId);

        return jdbcTemplate.query(getRetardsByStudentIdQuery, params, retard::baseMapper);
    }

    public void updateRetardReason(RetardDto retard) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", retard.getStudentId());
        params.addValue("date", retard.getDate());
        params.addValue("reason", retard.getReason());

        jdbcTemplate.update(updateRetardReasonQuery, params);
    }

    public retard getRetardByStudentIdAndDate(Long studentId, Date date) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId);
        params.addValue("date", date);

        return jdbcTemplate.queryForObject(getRetardsByStudentIdAndDateQuery, params, retard::baseMapper);
    }

}
