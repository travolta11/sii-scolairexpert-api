package org.ssischoolbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.model.Etudiant;

import java.util.List;

@Repository
public class EtudiantDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Etudiant etudiant) {
        String sql = "INSERT INTO etudiant (first_name, last_name, email, phone_number, address, zip_code, gender, level, groupe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, etudiant.getFirstName(), etudiant.getLastName(), etudiant.getEmail(), etudiant.getPhoneNumber(), etudiant.getAddress(), etudiant.getZipCode(), etudiant.getGender(), etudiant.getLevel(), etudiant.getGroup());
    }

    public int update(Etudiant etudiant) {
        String sql = "UPDATE etudiant SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ?, zip_code = ?, gender = ?, level = ?, groupe = ? WHERE etudiant_id = ?";
        return jdbcTemplate.update(sql, etudiant.getFirstName(), etudiant.getLastName(), etudiant.getEmail(), etudiant.getPhoneNumber(), etudiant.getAddress(), etudiant.getZipCode(), etudiant.getGender(), etudiant.getLevel(), etudiant.getGroup(), etudiant.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM etudiant WHERE etudiant_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Etudiant findById(Long id) {
        String sql = "SELECT * FROM etudiant WHERE etudiant_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, Etudiant::mapRow);
    }

    public List<Etudiant> findAll() {
        String sql = "SELECT * FROM etudiant";
        return jdbcTemplate.query(sql, Etudiant::mapRow);
    }
}

