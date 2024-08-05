package org.ssischoolbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class Etudiant {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String gender;
    private String level;
    private String classe;
    private Long parentId;

    public static Etudiant baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(resultSet.getLong("id"));
        etudiant.setFirstName(resultSet.getString("first_name"));
        etudiant.setLastName(resultSet.getString("last_name"));
        etudiant.setEmail(resultSet.getString("email"));
        etudiant.setPhoneNumber(resultSet.getString("phone_number"));
        etudiant.setAddress(resultSet.getString("address"));
        etudiant.setZipCode(resultSet.getString("zip_code"));
        etudiant.setGender(resultSet.getString("gender"));
        etudiant.setLevel(resultSet.getString("level"));
        etudiant.setClasse(resultSet.getString("classe"));
        etudiant.setParentId(resultSet.getLong("parent_id"));
        return etudiant;
    }
}
