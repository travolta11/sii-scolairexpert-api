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
    private String phoneNumber;
    private String email;

    public static Parent baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Parent parent = new Parent();
        parent.setId(resultSet.getLong("id"));
        parent.setFirstName(resultSet.getString("first_name"));
        parent.setLastName(resultSet.getString("last_name"));
        parent.setEmail(resultSet.getString("email"));
        parent.setPhoneNumber(resultSet.getString("phone_number"));
        return parent;
    }
}
