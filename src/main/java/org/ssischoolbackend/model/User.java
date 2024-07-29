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
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

    public static User baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .email(resultSet.getString("email"))
                .role(resultSet.getString("role"))
                .build();
    }
}
