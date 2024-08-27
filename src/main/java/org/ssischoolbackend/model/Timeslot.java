package org.ssischoolbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;


import org.optaplanner.core.api.domain.lookup.PlanningId;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeslot {

    @PlanningId
    private Long id;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;


    public static Timeslot baseMapper(ResultSet resultSet, int rowNum) throws SQLException {
        return Timeslot.builder()
                .id(resultSet.getLong("id"))
                .dayOfWeek(DayOfWeek.of(resultSet.getInt("day_of_week")))
                .startTime(resultSet.getTime("start_time").toLocalTime())
                .endTime(resultSet.getTime("end_time").toLocalTime())
                .build();
    }
   

    

}