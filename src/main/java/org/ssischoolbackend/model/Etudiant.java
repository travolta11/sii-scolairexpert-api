package org.ssischoolbackend.model;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Etudiant {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String adress;
    private String zipCode;
    private String gender;
    private String level;
    private String classe;

    public static Etudiant mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(resultSet.getLong("etudiant_id"));
        etudiant.setFirstName(resultSet.getString("first_name"));
        etudiant.setLastName(resultSet.getString("last_name"));
        etudiant.setEmail(resultSet.getString("email"));
        etudiant.setPhoneNumber(resultSet.getString("phone_number"));
        etudiant.setAdress(resultSet.getString("address"));
        etudiant.setZipCode(resultSet.getString("zip_code"));
        etudiant.setGender(resultSet.getString("gender"));
        etudiant.setLevel(resultSet.getString("level"));
        etudiant.setClasse(resultSet.getString("group"));
        return etudiant;
    }
}
