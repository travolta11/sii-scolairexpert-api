package org.ssischoolbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtudiantDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String gender;
    private String level;
    private String dateOfBirth;
    private Long classId;
    private Long parentId;
}
