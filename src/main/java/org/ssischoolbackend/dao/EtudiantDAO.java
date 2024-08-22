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
import org.ssischoolbackend.model.Parent;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                .addValue("classId", etudiant.getClassId())
                .addValue("parentId", etudiant.getParentId());
        log.info("Creating new Etudiant with Class ID: {}", etudiant.getClassId());

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

    public Optional<Etudiant> getEtudiantByEmail(String email) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("email",email.trim().toLowerCase());
        Etudiant etudiant = null;
        try {
            etudiant = jdbcTemplate.queryForObject(sqlProperties.getProperty("etudiant.get.by.email"), namedParameters, Etudiant::baseMapper);
        } catch (DataAccessException dataAccessException) {
        log.error("Student does not exist with email: " + email);
    }
            return Optional.ofNullable(etudiant);
    }

    public Optional<Etudiant> getEtudiantByPhone(String phoneNumber) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("phoneNumber",phoneNumber.trim());
        Etudiant etudiant = null;
        try {
            etudiant = jdbcTemplate.queryForObject(sqlProperties.getProperty("etudiant.get.by.phoneNumber"), namedParameters, Etudiant::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.error("Student does not exist with phoneNumber: " + phoneNumber);
        }
            return Optional.ofNullable(etudiant);
    }

    public List<Etudiant> getEtudiantsByClassId(Long classId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("classId", classId);
        return jdbcTemplate.query(sqlProperties.getProperty("etudiant.get.by.classId"), namedParameters, Etudiant::baseMapper);
    }

    public long getParentIdByStudentId(int studentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", studentId);
        Long parentId = null;
        try {
            parentId = jdbcTemplate.queryForObject(sqlProperties.getProperty("etudiant.get.parentId.by.studentId"), namedParameters, Long.class);
        } catch (DataAccessException dataAccessException) {
            log.error("Parent ID does not exist for student with id: " + studentId);
        }
        if (parentId == null) {
            throw new IllegalStateException("Parent ID does not exist for student with id: " + studentId);
        }
        return parentId;
    }
    public Optional<String> getFullNameByStudentId(int studentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", studentId);
        String fullName = null;
        try {
            fullName = jdbcTemplate.queryForObject(sqlProperties.getProperty("etudiant.get.fullname.by.studentId"), namedParameters, String.class);
        } catch (DataAccessException dataAccessException) {
            log.error("Full name does not exist for student with id: " + studentId);
        }
        return Optional.ofNullable(fullName);
    }

}
