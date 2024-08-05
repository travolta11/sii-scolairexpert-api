package org.ssischoolbackend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssischoolbackend.dao.TeacherDao;
import org.ssischoolbackend.dto.TeacherDto;
import org.ssischoolbackend.model.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;


    public Long createTeacher(TeacherDto teacherDto) {
        return this.teacherDao.createNewTeacher(TeacherDto.toEntity(teacherDto));
    }

    public Optional<TeacherDto> getTeacherByID(Long id) {
        Optional<Teacher> teacherOptional = this.teacherDao.getTeacherById(id);
        return teacherOptional.map(TeacherDto::fromEntity);
    }

    public List<TeacherDto> getAllTeachers() {
        return this.teacherDao.getAllTeachers().stream()
                .map(TeacherDto::fromEntity)
                .collect(Collectors.toList());
    }
}
