package org.ssischoolbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {
    private Long id;
    private String name;
    private int coefficient;
    private Long teacherId;
}
