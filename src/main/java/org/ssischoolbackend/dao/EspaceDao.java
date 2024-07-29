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

import org.ssischoolbackend.model.Espace;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
public class EspaceDao {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    Properties sqlProperties;


    public long createNewEspace(Espace espace) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("espace_name", espace.getName());
        int insert = jdbcTemplate.update(sqlProperties.getProperty("espace.create"), sqlParameterSource, holder);
		if (insert == 1) {
            log.debug("New espace Created :) " + espace.getName());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("espace not created :/ ");
            return 0;
        }
        
    }

    public Optional<Espace> getEspaceById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("espace_id", id);
        Espace espace = null;
        try {
            espace = jdbcTemplate.queryForObject(sqlProperties.getProperty("espace.get.one"), namedParameters, Espace::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.error("espace does not exist" + id);
        }
        return Optional.ofNullable(espace);
    }


    public List<Espace> getAllEspaces() {
        return jdbcTemplate.query(sqlProperties.getProperty("espace.get.all"),new MapSqlParameterSource(), Espace::baseMapper);
    }


}
