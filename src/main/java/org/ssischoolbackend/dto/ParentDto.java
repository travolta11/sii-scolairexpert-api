package org.ssischoolbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private boolean hasAssociatedStudents;
}
