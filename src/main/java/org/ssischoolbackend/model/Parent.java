package org.ssischoolbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class Parent {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public static Parent baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Parent parent = new Parent();
        parent.setId(resultSet.getLong("id"));
        parent.setFirstName(resultSet.getString("first_name"));
        parent.setLastName(resultSet.getString("last_name"));
        parent.setEmail(resultSet.getString("email"));
        parent.setPhone(resultSet.getString("phone"));
        return parent;
    }
}
