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
public class Materiel {
    private long id;
    private String code;
    private String type;
    private String status;

    public static Materiel baseMapper(ResultSet resultSet,int rowNumber) throws SQLException {
        return Materiel.builder()
                .id(resultSet.getLong("id"))
                .code(resultSet.getString("code"))
                .type(resultSet.getString("type"))
                .status(resultSet.getString("status"))
                .build();
    }
}
