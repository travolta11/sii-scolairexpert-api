package org.ssischoolbackend.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.model.Etudiant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EtudiantDAO {
    private final JdbcTemplate jdbcTemplate;

    public EtudiantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class EtudiantRowMapper implements RowMapper<Etudiant> {
        @Override
        public Etudiant mapRow(ResultSet rs, int rowNum) throws SQLException {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(rs.getLong("id"));
            etudiant.setFirstName(rs.getString("firstName"));
            etudiant.setLastName(rs.getString("lastName"));
            etudiant.setEmail(rs.getString("email"));
            etudiant.setPhoneNumber(rs.getString("phoneNumber"));
            etudiant.setAdress(rs.getString("adress"));
            etudiant.setZipCode(rs.getString("zipCode"));
            etudiant.setGender(rs.getString("gender"));
            etudiant.setLevel(rs.getString("level"));
            etudiant.setClasse(rs.getString("classe"));
            return etudiant;
        }
    }

    public List<Etudiant> getAllEtudiants(int offset, int limit) {
        return jdbcTemplate.query("SELECT * FROM etudiants LIMIT ? OFFSET ?", new Object[]{limit, offset}, new EtudiantRowMapper());
    }

    public Etudiant getEtudiantById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM etudiants WHERE id = ?", new Object[]{id}, new EtudiantRowMapper());
    }

    public int save(Etudiant etudiant) {
        return jdbcTemplate.update("INSERT INTO etudiants (firstName, lastName, email, phoneNumber, adress, zipCode, gender, level, classe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                etudiant.getFirstName(), etudiant.getLastName(), etudiant.getEmail(), etudiant.getPhoneNumber(), etudiant.getAdress(), etudiant.getZipCode(), etudiant.getGender(), etudiant.getLevel(), etudiant.getClasse());
    }

    public int update(Etudiant etudiant) {
        return jdbcTemplate.update("UPDATE etudiants SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, adress = ?, zipCode = ?, gender = ?, level = ?, classe = ? WHERE id = ?",
                etudiant.getFirstName(), etudiant.getLastName(), etudiant.getEmail(), etudiant.getPhoneNumber(), etudiant.getAdress(), etudiant.getZipCode(), etudiant.getGender(), etudiant.getLevel(), etudiant.getClasse(), etudiant.getId());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM etudiants WHERE id = ?", id);
    }

    public int getTotalEtudiants() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM etudiants", Integer.class);
    }
}
