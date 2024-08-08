package org.ssischoolbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssischoolbackend.model.Department;
import org.ssischoolbackend.model.Gender;
import org.ssischoolbackend.model.Position;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String cin;
    private Date date_of_birth;
    private Date date_of_start;
    private Gender gender;
    private Position position;
    private Department department;
}
