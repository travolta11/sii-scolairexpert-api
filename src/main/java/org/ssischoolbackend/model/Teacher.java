package org.ssischoolbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNo;

    public static Teacher baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id"));
        teacher.setFullName(resultSet.getString("full_name"));
        teacher.setEmail(resultSet.getString("email"));
        teacher.setPhoneNo(resultSet.getString("phone"));
        return teacher;
    }
}

