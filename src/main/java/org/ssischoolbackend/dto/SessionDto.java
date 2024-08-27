package org.ssischoolbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    private Long id;
    private Long teacherId;
    private Long classId;
    private Long roomId;
}
