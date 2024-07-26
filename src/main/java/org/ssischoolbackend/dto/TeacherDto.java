package org.ssischoolbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.ssischoolbackend.model.Teacher;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNo;
    private final static ModelMapper modelMapper = new ModelMapper();

    public static TeacherDto fromEntity(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDto.class);
    }

    public static Teacher toEntity(TeacherDto teacherDto) {
        return modelMapper.map(teacherDto, Teacher.class);
    }
}
