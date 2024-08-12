package org.ssischoolbackend.service;
import org.ssischoolbackend.dao.TeacherDao;
import org.ssischoolbackend.dto.TeacherDto;
import org.ssischoolbackend.model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class TeacherServiceTest {
     @Mock
    private TeacherDao teacherDao;

    @InjectMocks
    private TeacherService teacherService;

    private Teacher teacher;
    private TeacherDto teacherDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        teacher = Teacher.builder()
                .id(1L)
                .fullName("John Doe")
                .email("johndoe@example.com")
                .phoneNo("123456789")
                .build();
        
        teacherDto = TeacherDto.builder()
                .id(1L)
                .fullName("John Doe")
                .email("johndoe@example.com")
                .phoneNo("123456789")
                .build();
    }

    @Test
    public void should_create_teacher() {
        when(teacherDao.createNewTeacher(teacher)).thenReturn(1L);
        teacherService.createTeacher(teacherDto);
        verify(teacherDao, times(1)).createNewTeacher(teacher);
    }

    @Test
    public void should_get_teacher_by_id() {
        when(teacherDao.getTeacherById(anyLong())).thenReturn(Optional.of(teacher));
        Optional<TeacherDto> retrievedTeacher = teacherService.getTeacherByID(1L);
        verify(teacherDao, times(1)).getTeacherById(anyLong());
        assertThat(retrievedTeacher).isNotEmpty();
    }

    @Test
    public void should_not_get_teacher_by_id() {
        when(teacherDao.getTeacherById(anyLong())).thenReturn(Optional.empty());
        Optional<TeacherDto> retrievedTeacher = teacherService.getTeacherByID(1L);
        verify(teacherDao, times(1)).getTeacherById(anyLong());
        assertThat(retrievedTeacher).isEmpty();
    }

    @Test
    public void should_get_all_teachers() {
        when(teacherDao.getAllTeachers()).thenReturn(Arrays.asList(teacher));
        teacherService.getAllTeachers();
        verify(teacherDao, times(1)).getAllTeachers();
    }

    @Test
    public void should_update_teacher() {
        when(teacherDao.updateTeacher(teacher)).thenReturn(1);
        int result = teacherService.updateTeacher(teacherDto);
        verify(teacherDao, times(1)).updateTeacher(teacher);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void should_delete_teacher() {
        when(teacherDao.deleteTeacher(1L)).thenReturn(1);
        int result = teacherService.deleteTeacher(1L);
        verify(teacherDao, times(1)).deleteTeacher(1L);
        assertThat(result).isEqualTo(1);
    }
    
}
