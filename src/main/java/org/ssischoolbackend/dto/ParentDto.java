package org.ssischoolbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String cin;
    private boolean hasAssociatedStudents;
    private Long userId;
}

