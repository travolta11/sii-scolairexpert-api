package org.ssischoolbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AbsenceDto {
    private int studentId;
    private Date date;
    private String reason;
}

