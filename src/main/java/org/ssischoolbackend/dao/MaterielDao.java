package org.ssischoolbackend.dao;

import org.ssischoolbackend.model.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Repository
public class MaterielDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private Properties sqlProperties;

    public void createMateriel(Materiel materiel) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("code", materiel.getCode())
                .addValue("type", materiel.getType())
                .addValue("status", materiel.getStatus());
        jdbcTemplate.update(sqlProperties.getProperty("materiel.create"), sqlParameterSource);
    }

    public List<Materiel> getAllMateriel() {
        return jdbcTemplate.query(
                sqlProperties.getProperty("materiel.get.all"),
                (resultSet, rowNum) -> Materiel.baseMapper(resultSet, rowNum)
        );
    }

    public Optional<Materiel> getMaterielById(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);
        List<Materiel> materielList = jdbcTemplate.query(
                sqlProperties.getProperty("materiel.get.one"),
                sqlParameterSource,
                (resultSet, rowNum) -> Materiel.baseMapper(resultSet, rowNum)
        );
        return materielList.stream().findFirst();
    }

    public void updateMateriel(Long id, Materiel materiel) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("code", materiel.getCode())
                .addValue("type", materiel.getType())
                .addValue("status", materiel.getStatus());
        jdbcTemplate.update(sqlProperties.getProperty("materiel.update"), sqlParameterSource);
    }

    public void deleteMateriel(Long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(sqlProperties.getProperty("materiel.delete"), sqlParameterSource);
    }
}
