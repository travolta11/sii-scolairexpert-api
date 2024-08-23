package org.ssischoolbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@Getter
@Setter
public class absence {
    private Long absenceId;
    private Long studentId;
    private Date date;
    private String reason;

    public static absence baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        absence absence = new absence();
        absence.setAbsenceId(resultSet.getLong("absence_id"));
        absence.setStudentId(resultSet.getLong("student_id"));
        absence.setDate(resultSet.getDate("date"));
        absence.setReason(resultSet.getString("reason"));
        return absence;
    }
}

