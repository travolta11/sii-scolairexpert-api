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
public class Subject {
    private Long id;
    private String name;
    private int coefficient;
    private Long teacherId;

    
    public static Subject baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        return Subject.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .coefficient(resultSet.getInt("coefficient"))
                .teacherId(resultSet.getLong("teacher_id"))
                .build();
    }
}
