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
import org.ssischoolbackend.model.Etudiant;
import org.ssischoolbackend.model.Staff;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
public class EtudiantDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private Properties sqlProperties;

    public long createNewEtudiant(Etudiant etudiant) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", etudiant.getFirstName())
                .addValue("lastName", etudiant.getLastName())
                .addValue("email", etudiant.getEmail())
                .addValue("phoneNumber", etudiant.getPhoneNumber())
                .addValue("address", etudiant.getAddress())
                .addValue("zipCode", etudiant.getZipCode())
                .addValue("gender", etudiant.getGender())
                .addValue("level", etudiant.getLevel())
                .addValue("dateOfBirth", etudiant.getDateOfBirth())
                .addValue("parentId", etudiant.getParentId())
                .addValue("classId", etudiant.getClassId());

        int insert = jdbcTemplate.update(sqlProperties.getProperty("etudiant.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.debug("New etudiant Created :) " + etudiant.getFirstName() + " " + etudiant.getLastName());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Etudiant not created :/ ");
            return 0;
        }
    }

    public Optional<Etudiant> getEtudiantById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        Etudiant etudiant = null;
        try {
            etudiant = jdbcTemplate.queryForObject(sqlProperties.getProperty("etudiant.get.one"), namedParameters, Etudiant::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.error("Etudiant does not exist with id: " + id);
        }
        return Optional.ofNullable(etudiant);
    }
    public List<Etudiant> getAllStudents() {
        return jdbcTemplate.query(sqlProperties.getProperty("etudiant.get"),new MapSqlParameterSource(), Etudiant::baseMapper);
    }

    public List<Etudiant> getAllEtudiants(int offset, int size) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("limit", size)
                .addValue("offset", offset);
        return jdbcTemplate.query(sqlProperties.getProperty("etudiant.get.all"), sqlParameterSource, Etudiant::baseMapper);
    }

    public int getTotalEtudiants() {
        return jdbcTemplate.queryForObject(sqlProperties.getProperty("etudiant.count"), new MapSqlParameterSource(), Integer.class);
    }

    public void updateEtudiant(Etudiant etudiant) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", etudiant.getId())
                .addValue("firstName", etudiant.getFirstName())
                .addValue("lastName", etudiant.getLastName())
                .addValue("email", etudiant.getEmail())
                .addValue("phoneNumber", etudiant.getPhoneNumber())
                .addValue("address", etudiant.getAddress())
                .addValue("zipCode", etudiant.getZipCode())
                .addValue("gender", etudiant.getGender())
                .addValue("level", etudiant.getLevel())
                .addValue("dateOfBirth", etudiant.getDateOfBirth())
                .addValue("parentId", etudiant.getParentId())
                .addValue("classId", etudiant.getClassId());

        int update = jdbcTemplate.update(sqlProperties.getProperty("etudiant.update"), sqlParameterSource);
        if (update == 1) {
            log.debug("Etudiant Updated :) " + etudiant.getFirstName() + " " + etudiant.getLastName());
        }
    }

    public void deleteEtudiantById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        int delete = jdbcTemplate.update(sqlProperties.getProperty("etudiant.delete"), namedParameters);
        if (delete == 1) {
            log.debug("Etudiant Deleted with id: " + id);
        }
    }
    public List<Etudiant> getEtudiantsByClass(Long id){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbcTemplate.query(sqlProperties.getProperty("etudiant.get.by.class"), sqlParameterSource, Etudiant::baseMapper);
    }
}
