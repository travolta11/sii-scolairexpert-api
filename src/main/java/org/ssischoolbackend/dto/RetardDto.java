package org.ssischoolbackend.dto;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class RetardDto {
    private int studentId;
    private Date date;
    private String reason;
}

