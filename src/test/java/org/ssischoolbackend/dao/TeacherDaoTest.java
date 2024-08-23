package org.ssischoolbackend.dao;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.ssischoolbackend.model.Teacher;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:test-data/teacher-test.sql"},
        config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
public class TeacherDaoTest {
    @Autowired
    TeacherDao teacherDao;

    @Test
    public void should_get_all_teachers() {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        assertThat(teachers).hasSize(3);
    }

    @Test
    public void should_not_get_teacher() {
        Optional<Teacher> teacher = teacherDao.getTeacherById(6L);
        assertThat(teacher.isPresent()).isFalse();
    }

    @Test
    public void should_get_teacher() {
        Optional<Teacher> teacher = teacherDao.getTeacherById(2L);
        assertThat(teacher.isPresent()).isTrue();
        assertThat(teacher.get().getFullName()).isEqualTo("Bob");
    }

    @Test
    public void should_create_new_teacher() {
        Teacher newTeacher = Teacher.builder()
                .fullName("Jane Smith")
                .email("jane.smith@example.com")
                .phoneNo("123-456-7890")
                .build();
        long idTeacher = teacherDao.createNewTeacher(newTeacher);
        assertThat(teacherDao.getAllTeachers()).hasSize(4);
        assertThat(teacherDao.getTeacherById(idTeacher).get().getFullName()).isEqualTo("Jane Smith");
    }

    @Test
    public void should_update_teacher() {
        Teacher existingTeacher = Teacher.builder()
                .id(2L)
                .fullName("John Doe Updated")
                .email("john.doe.updated@example.com")
                .phoneNo("987-654-3210")
                .build();
        int updateCount = teacherDao.updateTeacher(existingTeacher);
        assertThat(updateCount).isEqualTo(1);
        assertThat(teacherDao.getTeacherById(2L).get().getFullName()).isEqualTo("John Doe Updated");
    }

    @Test
    public void should_delete_teacher() {
        int deleteCount = teacherDao.deleteTeacher(3L);
        assertThat(deleteCount).isEqualTo(1);
        assertThat(teacherDao.getTeacherById(3L).isPresent()).isFalse();
    }
  

}
