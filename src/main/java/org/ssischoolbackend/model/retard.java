package org.ssischoolbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@Getter
@Setter
public class retard {
    private Long retardId;
    private Long studentId;
    private Date date;
    private String reason;

    public static retard baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        retard retard = new retard();
        retard.setRetardId(resultSet.getLong("retard_id"));
        retard.setStudentId(resultSet.getLong("student_id"));
        retard.setDate(resultSet.getDate("date"));
        retard.setReason(resultSet.getString("reason"));
        return retard;
    }
}

