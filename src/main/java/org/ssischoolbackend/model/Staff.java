package org.ssischoolbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String cin;
    private Date date_of_birth;
    private Date date_of_start;
    private Gender gender;
    private Position position;
    private Department department;

    public static Staff staffMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Staff staff = new Staff();
        staff.setId(resultSet.getLong("id"));
        staff.setFirstName(resultSet.getString("first_name"));
        staff.setLastName(resultSet.getString("last_name"));
        staff.setEmail(resultSet.getString("email"));
        staff.setAddress(resultSet.getString("address"));
        staff.setPhone(resultSet.getString("phone"));
        staff.setCin(resultSet.getString("cin"));
        staff.setDate_of_birth(resultSet.getDate("birth_date"));
        staff.setDate_of_start(resultSet.getDate("start_date"));
        staff.setGender(Gender.valueOf(resultSet.getString("gender")));
        staff.setPosition(Position.valueOf(resultSet.getString("position")));
        staff.setDepartment(Department.valueOf(resultSet.getString("department")));
        return staff;
    }

}
